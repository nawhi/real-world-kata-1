package upsd.acceptance;

import org.junit.Test;

import static io.restassured.RestAssured.get;

public class AT_UserNotFound {

    @Test
    public void return_404_for_nonexistent_user() {
        get("/users/0").then()
                .statusCode(404)
                .contentType("application/json");
    }
}
