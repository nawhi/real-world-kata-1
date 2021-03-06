package upsd.api;

import upsd.controllers.JsonStringCreator;
import upsd.controllers.UserController;
import upsd.repositories.UserRepository;

import static spark.Spark.get;

class Routes {

    private UserController userController;

    Routes(UserRepository userRepository) {
        userController = new UserController(userRepository, new JsonStringCreator());
    }

    void setup() {
        get("/users/:id", (req, res) -> userController.getById(req, res));
        get("/users", (req, res) -> userController.getAll(req, res));
    }
}
