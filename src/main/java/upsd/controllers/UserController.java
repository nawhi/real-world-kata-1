package upsd.controllers;

import spark.Request;
import spark.Response;
import upsd.domain.User;
import upsd.repositories.UserRepository;

public class UserController {

    private final UserRepository userRepository;
    private final JsonStringCreator jsonStringCreator = new JsonStringCreator();

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));

        res.type("application/json");

        if (userRepository.getBy(id).isPresent()) {
            User userFound = userRepository.getBy(id).get();
            res.status(200);
            return jsonStringCreator.jsonStringFor(userFound);
        }

        res.status(404);
        return jsonStringCreator.notFound();

    }

    public String getAll(Request req, Response res) {
        throw new UnsupportedOperationException("TODO");
    }
}
