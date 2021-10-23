package ca.utoronto.utm.mcs;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.OutputStream;


public class Todo extends Endpoint {

    public Todo() throws ClassNotFoundException, SQLException {
        this.dao = new SQLDao();
    }

    public void handleGet(HttpExchange r) throws IOException, JSONException {
        ResultSet rs = this.dao.getTodos();
        try {
            if (rs.next()) {
                JSONObject var = new JSONObject();
                var.put("results", rs);
                this.sendResponse(r, var);
                return;
            } 
            this.sendError(r, 404);
        } catch (SQLException e) {
            this.sendError(r, 500);
        }
    }

    public void handlePatch(HttpExchange r) throws IOException, JSONException {

    }

    public void handlePost(HttpExchange r) throws IOException, JSONException {
        
    }

    public void handleDelete(HttpExchange r) throws IOException, JSONException {

    }
}
