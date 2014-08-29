package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.jongo.MongoCollection;
import uk.co.panaxiom.playjongo.PlayJongo;

import java.util.Optional;

public class Interval {

    public Optional<DateTime> stop;
    @JsonProperty("_id")
    public ObjectId id;
    public ObjectId userId;
    public DateTime start;

    public Interval(ObjectId id, ObjectId userId, DateTime start, Optional<DateTime> stop) {
        this.id = id;
        this.userId = userId;
        this.start = start;
        this.stop = stop;
    }

    public Interval() {
    }

    public static void save(Interval interval) {
        intervals().save(interval);
    }

    public static Interval findById(ObjectId id) {
        return intervals().findOne(id).as(Interval.class);
    }

    public static MongoCollection intervals() {
        return PlayJongo.getCollection("intervals");
    }


    @Override
    public String toString() {
        return "Interval{" +
                "stop=" + stop +
                ", id=" + id +
                ", userId=" + userId +
                ", start=" + start +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (id != null ? !id.equals(interval.id) : interval.id != null) return false;
        if (start != null ? start.getMillis() != interval.start.getMillis() : interval.start != null) return false;
        if (stop != null ? !compareDateTimes(stop, interval.stop) : interval.stop != null) return false;
        if (userId != null ? !userId.equals(interval.userId) : interval.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stop != null ? stop.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        return result;
    }

    private boolean compareDateTimes(Optional<DateTime> thisDateTime, Optional<DateTime> dateTime) {
        if (!thisDateTime.isPresent() == !dateTime.isPresent()) return true;
        if (thisDateTime.isPresent() && dateTime.isPresent() && thisDateTime.get().getMillis() == dateTime.get().getMillis())
            return true;
        return false;
    }
}
