package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import org.json.JSONException;


public class Todo extends Endpoint {

    public Todo() throws ClassNotFoundException, SQLException {
        this.dao = new SQLDao();
    }

    public void handleGet(HttpExchange r) throws IOException, JSONException {
    
    }

    public void handlePatch(HttpExchange r) throws IOException, JSONException {

    }

    public void handlePost(HttpExchange r) throws IOException, JSONException {

    }

    public void handleDelete(HttpExchange r) throws IOException, JSONException {

    }
}
