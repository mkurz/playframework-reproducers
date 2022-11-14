package controllers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import play.libs.Files;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Test
    public void testOnlyFormDataNoFiles() throws ExecutionException, InterruptedException, TimeoutException {
        final Map<String, String[]> postParams = new HashMap<>();
        postParams.put("key1", new String[]{"value1"});
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(postParams, Collections.emptyList())
                .uri("/multipart-form-data-no-files");

        Result result = route(app, request);
        String content = result.body().consumeData(mat).thenApply(bs -> bs.utf8String()).toCompletableFuture().get(5, TimeUnit.SECONDS);
        assertEquals(OK, result.status());
        assertEquals("Files: 0, Data: 1 [value1]", content);
    }

    @Test
    public void testJavaTemporaryFile() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Files.TemporaryFile tempFile = Files.singletonTemporaryFileCreator().create("temp", "txt");
        java.nio.file.Files.write(tempFile.path(), "Twas brillig and the slithy Toves...".getBytes());
        testTemporaryFile(List.of(new Http.MultipartFormData.FilePart<>("document", "jabberwocky.txt", "text/plain", tempFile)));
    }

    @Test
    public void testScalaTemporaryFile() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        play.api.libs.Files.TemporaryFile tempFile = play.api.libs.Files.SingletonTemporaryFileCreator$.MODULE$.create("temp", "txt");
        java.nio.file.Files.write(tempFile.path(), "Twas brillig and the slithy Toves...".getBytes());
        testTemporaryFile(List.of(new Http.MultipartFormData.FilePart<>("document", "jabberwocky.txt", "text/plain", tempFile)));
    }

    private void testTemporaryFile(final List<Http.MultipartFormData.FilePart> files) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        final Map<String, String[]> data = new HashMap<>();
        data.put("author", new String[]{"Lewis Carrol"});

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(data, files)
                .uri("/multipart-form-data");

        Result result = route(app, request);
        String content = result.body().consumeData(mat).thenApply(bs -> bs.utf8String()).toCompletableFuture().get(5, TimeUnit.SECONDS);
        assertEquals(OK, result.status());
        assertEquals("author: Lewis Carrol\n"
                        + "filename: jabberwocky.txt\n"
                        + "contentType: text/plain\n"
                        + "contents: Twas brillig and the slithy Toves...\n",
                content);
    }
}
