package upsd.controllers;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import upsd.domain.User;
import upsd.repositories.UserRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserControllerShould {

    private Request request;
    private Response response;
    private UserController userController;
    private final User USER = new User(1, "bob");
    private UserRepository userRepository;
    private JsonStringCreator jsonStringCreator;

    @Before
    public void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        userRepository = mock(UserRepository.class);
        jsonStringCreator = mock(JsonStringCreator.class);
        userController = new UserController(userRepository, jsonStringCreator);
    }

    @Test
    public void return_user_for_supplied_id() {
        given(request.params(":id")).willReturn("1");
        given(userRepository.getBy(1)).willReturn(Optional.of(USER));

        userController.getById(request, this.response);

        verify(response).type("application/json");
        verify(jsonStringCreator).jsonStringFor(USER);
    }

    @Test
    public void return_user_not_found() {
        given(request.params(":id")).willReturn("1");
        given(userRepository.getBy(1)).willReturn(Optional.empty());

        userController.getById(request, response);

        verify(jsonStringCreator).notFound();
    }
}
