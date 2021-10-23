package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;
import org.json.JSONException;

public abstract class Endpoint implements HttpHandler {

    public SQLDao dao;

    public Endpoint() throws ClassNotFoundException, SQLException {
        this.dao = new SQLDao();
    }

    public void handle(HttpExchange r) {
        try {
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
            default:
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

}