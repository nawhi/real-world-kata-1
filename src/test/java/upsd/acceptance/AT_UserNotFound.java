package upsd.acceptance;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class AT_UserNotFound {

    @Test
    public void return_404_for_nonexistent_user() {
        get("/users/0").then()
                .statusCode(404)
                .contentType("application/json")
                .body("title", is("User Not Found"));
    }
}
