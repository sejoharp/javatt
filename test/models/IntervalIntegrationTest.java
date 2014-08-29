package models;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import play.Play;
import play.test.WithApplication;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntervalIntegrationTest extends WithApplication {

    @Before
    public void setup() {

    }

    @Test
    public void savesAnInterval() {
        Interval interval = new Interval(ObjectId.get(), ObjectId.get(), new DateTime(), Optional.empty());
        Interval.save(interval);
        Interval actual = Interval.findById(interval.id);
        assertThat(actual, is(interval));
    }
}
