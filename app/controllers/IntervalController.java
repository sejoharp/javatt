package controllers;

import models.IntervalDao;
import play.mvc.Controller;
import play.mvc.Result;

public class IntervalController extends Controller {

    private IntervalDao intervalDao;

    public IntervalController() {
        this.intervalDao = new IntervalDao();
    }

    public Result start() {
        return ok();
    }
}
