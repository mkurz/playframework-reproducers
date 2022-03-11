package controllers;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.WebSocket;

import java.util.concurrent.CompletableFuture;

public class HomeController extends Controller {

    public WebSocket socket(Http.Request req) {
        return WebSocket.Text.acceptOrResult(request -> CompletableFuture.completedFuture(F.Either.Left(forbidden())));
    }
}
