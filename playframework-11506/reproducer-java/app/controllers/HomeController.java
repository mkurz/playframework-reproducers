package controllers;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import play.cache.Cached;
import play.libs.ws.WSClient;
import play.mvc.*;

import javax.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletionStage;

public class HomeController extends Controller {

    private final WSClient wsClient;

    private int _controllerNumber = 0;
    private int _requestNumber = 0;

    final PolicyFactory policy = Sanitizers.FORMATTING
            .and(Sanitizers.LINKS)
            .and(Sanitizers.TABLES)
            .and(Sanitizers.BLOCKS)
            .and(Sanitizers.IMAGES)
            .and(Sanitizers.STYLES);

    @Inject
    public HomeController(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    @Cached(key="website-sanitized", duration = 20)
    public CompletionStage<Result> websiteSanitized(String address) {
        _controllerNumber += 1;
        System.out.println(_controllerNumber + " controller invocation");
        return this.wsClient.url(address).setRequestTimeout(Duration.of(1000, ChronoUnit.SECONDS)).get()
                .thenApplyAsync(wsResponse -> ok(policy.sanitize(wsResponse.getBody())));
    }

}
