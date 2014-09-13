package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Interval;
import models.IntervalDao;
import models.UserTestData;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

@RunWith(MockitoJUnitRunner.class)
public class IntervalControllerTest {

    private static FakeApplication app;

    @Mock
    private Http.Request request;
    @Mock
    private IntervalDao intervalDaoMock;
    @InjectMocks
    private IntervalController intervalController;

    private Map<String, Object> contextArguments;

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
        Http.Context.current.set(createContext());
    }

    @Test
    public void createsNewInterval() {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(false);
        contextArguments.put("user", UserTestData.USER1);

        Result result = new IntervalController().start();

        verify(intervalDaoMock, times(1)).save(any(Interval.class));
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void doesNotCreateNewInterval() throws IOException {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(true);
        contextArguments.put("user", UserTestData.USER1);

        Result result = new IntervalController().start();

        assertThat(status(result)).isEqualTo(FORBIDDEN);
        assertThat(getReturnCode(result)).isEqualTo("USER_IS_ALREADY_WORKING");
        verify(intervalDaoMock, never()).save(any(Interval.class));
    }

    @Test
    public void answersWithJson() {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(true);
        contextArguments.put("user", UserTestData.USER1);

        Result result = new IntervalController().start();

        assertThat(contentType(result)).isEqualTo("application/json");
    }

    private Http.Context createContext() {
        Map<String, String> flashData = Collections.emptyMap();
        Long id = 2L;
        contextArguments = new HashMap<>();
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        return new Http.Context(id, header, request, flashData, flashData, contextArguments);
    }

    private JsonNode getJsonNode(Result result) throws IOException {
        return Json.parse(contentAsString(result));
    }

    private String getReturnCode(Result result) throws IOException {
        return getJsonNode(result).findPath("returnCode").textValue();
    }
}
