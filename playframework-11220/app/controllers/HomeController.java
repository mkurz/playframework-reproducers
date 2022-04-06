package controllers;

import play.data.FormFactory;
import play.i18n.Lang;
import play.libs.typedmap.TypedMap;
import play.mvc.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class HomeController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public HomeController(final FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result index() {
        this.formFactory.form().bind(Lang.defaultLang(), TypedMap.create(), new HashMap<>());
        return ok("hello world");
    }

}
