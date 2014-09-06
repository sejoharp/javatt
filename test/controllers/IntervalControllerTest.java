package controllers;

import models.IntervalDao;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.status;

@RunWith(MockitoJUnitRunner.class)
public class IntervalControllerTest {

    private static FakeApplication app;

    @Mock
    private Http.Request request;
    @Mock
    private IntervalDao intervalDaoMock;
    @InjectMocks
    private IntervalController intervalController;

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication();
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void beforeTest() {
        Map<String, String> flashData = Collections.emptyMap();
        Map<String, Object> argData = Collections.emptyMap();
        Long id = 2L;
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Http.Context context = new Http.Context(id, header, request, flashData, flashData, argData);
        Http.Context.current.set(context);
    }

    @Test
    public void createsNewInterval() {
        Result result = new IntervalController().start();
        assertThat(status(result), is(OK));
    }
}
