package upsd.controllers;

import com.eclipsesource.json.JsonObject;
import spark.Request;
import spark.Response;
import upsd.domain.User;
import upsd.repositories.UserRepository;

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));

        res.type("application/json");

        if (userRepository.getBy(id).isPresent()) {
            User userFound = userRepository.getBy(id).get();
            res.status(200);
            return jsonStringFor(userFound);
        }

        res.status(404);
        return  new JsonObject()
                    .add("title", "User Not Found")
                    .toString();

    }

    private String jsonStringFor(User user) {
        return new JsonObject()
                .add("id", user.id())
                .add("name", user.name())
                .toString();
    }
}
