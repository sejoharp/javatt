package models;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.jongo.Mapper;
import org.jongo.marshall.jackson.JacksonMapper;
import uk.co.panaxiom.playjongo.JongoMapperFactory;

/**
 * Created by joscha on 29.08.14.
 */
public class JongoMapperFactoryImpl implements JongoMapperFactory {
    @Override
    public Mapper create() {
        return new JacksonMapper.Builder()
                .registerModule(new JodaModule())
                .build();
    }
}
