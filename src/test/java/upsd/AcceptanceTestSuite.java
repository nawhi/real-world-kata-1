package upsd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import spark.Spark;
import upsd.acceptance.AT_GetAllUsers;
import upsd.acceptance.AT_GetUserById;
import upsd.acceptance.AT_UserNotFound;
import upsd.api.Server;
import upsd.helpers.Helper;
import upsd.repositories.UserRepository;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AT_GetUserById.class,
        AT_UserNotFound.class,
        AT_GetAllUsers.class
})
public class AcceptanceTestSuite {

    private static UserRepository userRepository = new UserRepository();
    public static final Helper helper = new Helper(userRepository);

    @BeforeClass
    public static void setUp() {
        new Server(userRepository).startOn(8080);
    }

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }
}
