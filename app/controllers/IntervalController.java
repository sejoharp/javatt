package controllers;

import models.Interval;
import models.IntervalDao;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Optional;

public class IntervalController extends Controller {

    private IntervalDao intervalDao;

    public IntervalController() {
        this.intervalDao = new IntervalDao();
    }

    public Result start() {
        ObjectId userid = (ObjectId) ctx().args.get("user");
        if (intervalDao.isUserWorking(userid)) {
            return forbidden(Json.newObject().put("returnCode", ReturnCode.USER_IS_ALREADY_WORKING.name()));
        } else {
            intervalDao.save(new Interval(userid, DateTime.now(), Optional.empty()));
        }
        return ok(Json.toJson("Moin"));
    }
}
