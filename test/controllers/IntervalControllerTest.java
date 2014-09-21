package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import models.Interval;
import models.IntervalDao;
import models.UserTestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

@RunWith(MockitoJUnitRunner.class)
public class IntervalControllerTest {

    @Mock
    private Http.Request request;
    @Mock
    private IntervalDao intervalDaoMock;
    @InjectMocks
    private IntervalController intervalController;

    @Test
    public void createsNewInterval() {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(false);
        setHttpContext(ImmutableMap.of("user", UserTestData.USER1));

        Result result = intervalController.start();

        verify(intervalDaoMock, times(1)).save(any(Interval.class));
        assertThat(status(result)).isEqualTo(OK);
    }

    @Test
    public void doesNotCreateNewInterval() throws IOException {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(true);
        setHttpContext(ImmutableMap.of("user", UserTestData.USER1));

        Result result = intervalController.start();

        assertThat(status(result)).isEqualTo(FORBIDDEN);
        assertThat(getReturnCode(result)).isEqualTo(ReturnCode.USER_IS_ALREADY_WORKING.name());
        verify(intervalDaoMock, never()).save(any(Interval.class));
    }

    @Test
    public void answersWithJson() {
        when(intervalDaoMock.isUserWorking(UserTestData.USER1)).thenReturn(true);
        setHttpContext(ImmutableMap.of("user", UserTestData.USER1));

        Result result = intervalController.start();

        assertThat(contentType(result)).isEqualTo("application/json");
    }

    private void setHttpContext(Map<String, Object> contextArguments) {
        Map<String, String> flashData = Collections.emptyMap();
        Long id = 2L;
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Http.Context.current.set(new Http.Context(id, header, request, flashData, flashData, contextArguments));
    }

    private JsonNode getJsonNode(Result result) throws IOException {
        return Json.parse(contentAsString(result));
    }

    private String getReturnCode(Result result) throws IOException {
        return getJsonNode(result).findPath("returnCode").textValue();
    }
}
