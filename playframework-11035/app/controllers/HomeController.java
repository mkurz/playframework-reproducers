package controllers;

import play.http.HttpEntity;
import play.http.HttpEntity.Streamed;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.*;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class HomeController extends Controller {

    private final WSClient ws;

    @Inject
    public HomeController(final WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> first() {
        WSRequest wsRequest = ws.url("https://example.com");
        return wsRequest.get().thenCompose(response -> {
            return ws.url("https://example.com").setMethod("GET").stream();
        }).thenApply(response -> {
            return redirect(routes.HomeController.second("Bla bla"));
//            Optional<Long> length = Optional.of(Long.parseLong(response.getHeaders().get(CONTENT_LENGTH).get(0)));
//            Optional<String> contentType = Optional.of(response.getHeaders().get(CONTENT_TYPE).get(0));
//            return ok().sendEntity(new Streamed(response.getBodyAsSource(), length, contentType));
        });
    }

    public Result second(final String foo) {
        return ok(views.html.index.render());
    }
}
