package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.util.Optional;

public class Interval {
    @JsonProperty("_id")
    public ObjectId id;
    public ObjectId userId;
    public DateTime start;
    public Optional<DateTime> stop;

    public Interval(ObjectId id, ObjectId userId, DateTime start, Optional<DateTime> stop) {
        this.id = id;
        this.userId = userId;
        this.start = start;
        this.stop = stop;
    }

    public Interval(ObjectId userId, DateTime start, Optional<DateTime> stop) {
        this.id = ObjectId.get();
        this.userId = userId;
        this.start = start;
        this.stop = stop;
    }

    public Interval() {
    }

    @Override
    public String toString() {
        return "Interval{" +
                "id=" + id +
                ", userId=" + userId +
                ", start=" + start +
                ", stop=" + stop +
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

    private boolean compareDateTimes(Optional<DateTime> thisDateTime, Optional<DateTime> dateTime) {
        if (!thisDateTime.isPresent() == !dateTime.isPresent()) return true;
        if (thisDateTime.isPresent() && dateTime.isPresent() && thisDateTime.get().getMillis() == dateTime.get().getMillis())
            return true;
        return false;
    }
}
