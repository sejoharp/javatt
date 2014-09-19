package controllers;

import models.IntervalDao;
import org.bson.types.ObjectId;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class IntervalController extends Controller {

    private IntervalDao intervalDao;

    public IntervalController() {
        this.intervalDao = new IntervalDao();
    }

    public Result start() {
        ObjectId userid = (ObjectId) ctx().args.get("user");
        if (intervalDao.isUserWorking(userid)) {

        } else {
            return forbidden(Json.newObject().put("returnCode", ReturnCode.USER_IS_ALREADY_WORKING.name()));
        }
        return ok(Json.toJson("Moin"));
    }
}
