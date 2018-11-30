package upsd.acceptance;

import org.junit.Test;
import upsd.domain.User;
import upsd.helpers.Helper;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

public class AT_GetAllUsers {

    @Test
    public void return_200_and_all_users_found() {
        Helper.add(new User(1, "sam"));
        Helper.add(new User(2, "nick"));
        Helper.add(new User(3, "scott"));
        Helper.add(new User(4, "andre"));

        get("/users").then()
                .statusCode(200)
                .contentType("application/json")
                .body("users.size()", is(4))
                .body("users.id", hasItems(1, 2, 3, 4))
                .body("users.name", hasItems("sam", "nick", "scott", "andre"));
    }
}
