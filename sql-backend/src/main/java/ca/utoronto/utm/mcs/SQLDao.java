package ca.utoronto.utm.mcs;

import java.sql.*;

public class SQLDao {

    private Connection connection;

    private final String uriDb = "jdbc:postgresql://postgres:5432/postgres";
    private final String username = "postgres";
    private final String password = "password";

    public SQLDao() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(this.uriDb, this.username, this.password);
    }

    public ResultSet getTodos() {
        String query = "SELECT * FROM todos;";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return null;
    }

    public ResultSet patchTodo(int id, String title, String description, boolean isChecked) throws SQLException {
        // TO DO
        return null;
    }

    public ResultSet postTodo(String title, String description, boolean isChecked) {
        String query = "INSERT INTO todos(title, description, isChecked) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setBoolean(3, isChecked);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return null;
    }

    public ResultSet deleteTodo(int id) {
        // TO DO
        return null;
    }
}