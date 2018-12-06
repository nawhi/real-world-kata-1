package upsd.controllers;

import com.eclipsesource.json.JsonObject;
import upsd.domain.User;

public class JsonStringCreator {
    public String notFound() {
        return new JsonObject()
                    .add("title", "Not Found")
                    .toString();
    }

    String jsonStringFor(User user) {
        return new JsonObject()
                .add("id", user.id())
                .add("name", user.name())
                .toString();
    }
}