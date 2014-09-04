package models;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Arrays;
import java.util.List;

import static models.IntervalTestData.*;
import static models.UserTestData.USER1;
import static models.UserTestData.USER2;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntervalIntegrationTest extends WithApplication {

    private IntervalDao intervalDao;

    @Before
    public void setup() {
        intervalDao = new IntervalDao();
        intervalDao.drop();
    }

    @Test
    public void savesAnInterval() {
        intervalDao.save(USER1_COMPLETED_2014_01_01_FROM_8_TO_9);

        Interval actual = intervalDao.findById(USER1_COMPLETED_2014_01_01_FROM_8_TO_9.id);

        assertThat(actual, is(USER1_COMPLETED_2014_01_01_FROM_8_TO_9));
    }

    @Test
    public void findsAllIntervalsInRange() {
        intervalDao.save(USER1_COMPLETED_2014_01_01_FROM_8_TO_9);
        intervalDao.save(USER1_COMPLETED_2014_01_01_FROM_14_TO_18);
        intervalDao.save(USER1_COMPLETED_2014_01_02_FROM_18_TO_20);
        intervalDao.save(USER2_OPEN_2014_09_02_FROM_11);
        intervalDao.save(USER1_COMPLETED_2014_01_03_FROM_13_TO_14);
        intervalDao.save(USER1_COMPLETED_2014_01_04_FROM_15_TO_19);

        List<Interval> intervals = intervalDao.findAllRange(
                USER1,
                new DateTime(2014, 1, 2, 0, 0),
                new DateTime(2014, 1, 3, 23, 59));

        assertThat(intervals, is(Arrays.asList(
                USER1_COMPLETED_2014_01_02_FROM_18_TO_20,
                USER1_COMPLETED_2014_01_03_FROM_13_TO_14)));
    }

    @Test
    public void user2IsNotWorking() {
        intervalDao.save(USER2_COMPLETED_2014_01_02_FROM_11_TO_12);

        assertThat(intervalDao.isUserWorking(USER2),is(false));
    }

    @Test
    public void user2IsWorking() {
        intervalDao.save(USER2_OPEN_2014_09_02_FROM_11);

        assertThat(intervalDao.isUserWorking(USER2),is(true));
    }
}
