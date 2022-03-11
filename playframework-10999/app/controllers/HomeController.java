package controllers;

import play.mvc.*;

public class HomeController extends Controller {

    public Result index(String path) {
        return ok(path);
    }

}
