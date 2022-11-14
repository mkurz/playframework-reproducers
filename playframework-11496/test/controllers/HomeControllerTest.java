package controllers;

import java.io.IOException;
import java.util.*;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Files;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testEmpty() {
        final Map<String, String[]> postParams = new HashMap<>();
        postParams.put("value", new String[]{"value"});
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(postParams, Collections.emptyList())
                .uri("/test");

        Result result = route(app, request);
        result.body().consumeData(mat).thenAccept(bs -> System.out.println(bs.utf8String())); // yeah... debugging...
        assertEquals(OK, result.status());
    }

    @Test
    public void testRealJavaTempFile() throws IOException {
        Files.TemporaryFile tempFile = Files.singletonTemporaryFileCreator().create("temp", "txt");
        java.nio.file.Files.write(tempFile.path(), "Twas brillig and the slithy Toves...".getBytes());
        final List<Http.MultipartFormData.FilePart> files = List.of(
                new Http.MultipartFormData.FilePart<>(
                        "document", "jabberwocky.txt", "text/plain", tempFile));

        final Map<String, String[]> postParams = new HashMap<>();
        postParams.put("value", new String[]{"value"});
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(postParams, files)
                .uri("/test");

        Result result = route(app, request);
        result.body().consumeData(mat).thenAccept(bs -> System.out.println(bs.utf8String()));
        System.out.println();
        assertEquals(OK, result.status());
    }

    @Test
    public void testRealScalaTempFile() throws IOException {
        play.api.libs.Files.TemporaryFile tempFile = play.api.libs.Files.SingletonTemporaryFileCreator$.MODULE$.create("temp", "txt");
        java.nio.file.Files.write(tempFile.path(), "Twas brillig and the slithy Toves...".getBytes());
        final List<Http.MultipartFormData.FilePart> files = List.of(
                new Http.MultipartFormData.FilePart<>(
                        "document", "jabberwocky.txt", "text/plain", tempFile));

        final Map<String, String[]> postParams = new HashMap<>();
        postParams.put("value", new String[]{"value"});
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(postParams, files)
                .uri("/test");

        Result result = route(app, request);
        result.body().consumeData(mat).thenAccept(bs -> System.out.println(bs.utf8String()));
        System.out.println();
        assertEquals(OK, result.status());
    }
}
