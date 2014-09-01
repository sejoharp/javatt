package models;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.util.Optional;

import static models.UserTestData.*;

public class IntervalTestData {
    public static final Interval USER1_COMPLETED_2014_01_01_FROM_8_TO_9 = new Interval(ObjectId.get(),
            USER1,
            new DateTime(2014, 1, 1, 8, 0),
            Optional.of(new DateTime(2014, 1, 1, 9, 0)));

    public static final Interval USER1_COMPLETED_2014_01_01_FROM_14_TO_18 = new Interval(ObjectId.get(),
            USER1,
            new DateTime(2014, 1, 1, 14, 0),
            Optional.of(new DateTime(2014, 1, 1, 18, 0)));

    public static final Interval USER1_COMPLETED_2014_01_02_FROM_18_TO_20 = new Interval(ObjectId.get(),
            USER1,
            new DateTime(2014, 1, 2, 18, 0),
            Optional.of(new DateTime(2014, 1, 2, 20, 0)));

    public static final Interval USER2_COMPLETED_2014_01_02_FROM_11_TO_12 = new Interval(ObjectId.get(),
            USER2,
            new DateTime(2014, 1, 2, 11, 0),
            Optional.of(new DateTime(2014, 1, 2, 12, 0)));

    public static final Interval USER1_COMPLETED_2014_01_03_FROM_13_TO_14 = new Interval(ObjectId.get(),
            USER1,
            new DateTime(2014, 1, 3, 13, 0),
            Optional.of(new DateTime(2014, 1, 3, 14, 0)));

    public static final Interval USER1_COMPLETED_2014_01_04_FROM_15_TO_19 = new Interval(ObjectId.get(),
            USER1,
            new DateTime(2014, 1, 4, 15, 0),
            Optional.of(new DateTime(2014, 1, 4, 19, 0)));
}
