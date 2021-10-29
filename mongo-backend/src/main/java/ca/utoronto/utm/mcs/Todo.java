package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.*;
import org.bson.Document;


public class Todo extends Endpoint {

    public Todo() throws ClassNotFoundException {
        this.dao = new MongoDao();
    }

    public void handleGet(HttpExchange r) throws IOException, JSONException {
        FindIterable<Document> cursor = this.dao.getTodos();
        try {
            if (cursor != null) {
                JSONObject var = new JSONObject();
                var.put("results", Utils.findIterableToJSONArray(cursor));
                this.sendResponse(r, var);
                return;
            }
            this.sendStatus(r, 404);
        } catch (Exception e) {
            this.sendStatus(r, 500);
        }
    }

    public void handlePatch(HttpExchange r) throws IOException, JSONException {
        // TO DO
    }

    public void handlePost(HttpExchange r) throws IOException, JSONException {
        JSONObject body = new JSONObject(Utils.convert(r.getRequestBody()));
        if (body.has("title") && body.has("description") && body.has("isChecked")) {
            try {
                this.dao.postTodo(body.getString("title"), body.getString("description"), body.getBoolean("isChecked"));
                this.sendStatus(r, 200);
            } catch (Exception e) {
                this.sendStatus(r, 500);
            }
        } else {
            this.sendStatus(r, 400);
        }
    }

    public void handleDelete(HttpExchange r) throws IOException, JSONException {
        // TO DO
    }
}
