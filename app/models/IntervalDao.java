package models;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.jongo.MongoCollection;
import uk.co.panaxiom.playjongo.PlayJongo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IntervalDao {

    public void save(Interval interval) {
        collection().save(interval);
    }

    public void drop() {
        collection().drop();
    }

    public Interval findById(ObjectId id) {
        return collection().findOne(id).as(Interval.class);
    }

    private MongoCollection collection() {
        return PlayJongo.getCollection("collection");
    }

    public List<Interval> findAllRange(ObjectId userId, DateTime from, DateTime to) {
//        return StreamSupport.stream(collection()
//                .find("{userId:#,start:{$gte :#},stop:{$lte:#}}", userId, from, to)
//                .as(Interval.class).spliterator(), false).collect(Collectors.toList());
        Iterable<Interval> intervalIterable = collection()
                .find("{userId:#,start:{$gte :#},stop:{$lte:#}}", userId, from, to)
                .as(Interval.class);
        intervalIterable.forEach(System.out::println);
        return null;
    }
}
