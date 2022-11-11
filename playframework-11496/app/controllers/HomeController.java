package controllers;

import play.libs.Files;
import play.mvc.*;

public class HomeController extends Controller {

    public Result index() {
        return ok("hello world");
    }

    public Result test(Http.Request request) {
        Http.MultipartFormData<Files.TemporaryFile> body=request.body().asMultipartFormData();
        return ok("test");
    }

}
