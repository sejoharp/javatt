package routing;

import org.junit.Test;
import play.mvc.Result;
import play.test.WithApplication;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class IntervalRoutingTest extends WithApplication {
    @Test
    public void routesToStartAction() {
        Result result = route(fakeRequest(POST, "/interval"));
        assertThat(status(result)).isEqualTo(200);
    }
}
