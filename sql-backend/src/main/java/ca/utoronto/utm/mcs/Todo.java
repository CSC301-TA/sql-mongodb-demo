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
                String response = var.toString();   
                r.sendResponseHeaders(200, response.length());
                OutputStream os = r.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        } catch (SQLException e) {
            JSONObject res = new JSONObject();
            res.put("status", "INTERNAL SERVER ERROR");
            String response = res.toString();
            r.sendResponseHeaders(500, response.length());
            // Writing response body
            OutputStream os = r.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public void handlePatch(HttpExchange r) throws IOException, JSONException {

    }

    public void handlePost(HttpExchange r) throws IOException, JSONException {

    }

    public void handleDelete(HttpExchange r) throws IOException, JSONException {

    }
}
