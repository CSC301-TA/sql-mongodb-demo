package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.OutputStream;

public abstract class Endpoint implements HttpHandler {

    public MongoDao dao;
    public HashMap<Integer, String> errorMap;

    public Endpoint() throws ClassNotFoundException {
        this.dao = new MongoDao();
        errorMap = new HashMap<>();
        errorMap.put(200, "OK");
        errorMap.put(400, "BAD REQUEST");
        errorMap.put(404, "NOT FOUND");
        errorMap.put(500, "INTERNAL SERVER ERROR");
    }

    public void handle(HttpExchange r) {
        try {
            r.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            switch (r.getRequestMethod()) {
            case "GET":
                this.handleGet(r);
                break;
            case "PATCH":
                this.handlePatch(r);
                break;
            case "POST":
                this.handlePost(r);
                break;
            case "DELETE":
                this.handleDelete(r);
                break;
            case "OPTIONS":
                // cors voodoo magic ...
                r.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, PATCH, POST, DELETE, OPTIONS");
                r.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
                r.sendResponseHeaders(204, -1);
                break;
            default:
                System.out.println("Else");
                break;  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void handleGet(HttpExchange r) throws IOException, JSONException;

    public abstract void handlePatch(HttpExchange r) throws IOException, JSONException;

    public abstract void handlePost(HttpExchange r) throws IOException, JSONException;

    public abstract void handleDelete(HttpExchange r) throws IOException, JSONException;

    public void writeOutputStream(HttpExchange r, String response) throws IOException {
        OutputStream os = r.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void sendResponse(HttpExchange r, JSONObject obj) throws JSONException, IOException {
        String response = obj.toString();
        r.sendResponseHeaders(200, response.length());
        this.writeOutputStream(r, response);
    }

    public void sendStatus(HttpExchange r, int statusCode) throws JSONException, IOException {
        JSONObject res = new JSONObject();
        res.put("status", errorMap.get(statusCode));
        String response = res.toString();
        r.sendResponseHeaders(statusCode, response.length());
        this.writeOutputStream(r, response);
    }

}