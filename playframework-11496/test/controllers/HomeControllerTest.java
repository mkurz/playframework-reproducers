package controllers;

import java.util.*;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
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
    public void testIndex() {
        final Map<String, String[]> postParams = new HashMap<>();
        postParams.put("value", new String[]{"value"});
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyMultipart(postParams, Collections.emptyList())
                .uri("/test");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}