package controllers;

import com.typesafe.config.Config;
import play.mvc.*;

import javax.inject.Inject;

public class HomeController extends Controller {

    final private Config config;

    @Inject
    public HomeController(final Config config) {
        this.config = config;
    }

    public Result index() {
        return ok("Config value: " + config.getString("foo-var"));
    }

}
