package ca.utoronto.utm.mcs;

import java.sql.*;

public class SQLDao {

    private Connection connection;

    private final String uriDb = "jdbc:postgresql://postgres:5432/root";
    private final String username = "root";
    private final String password = "123456";

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

    public ResultSet patchTodo(int id, String title, String description, boolean isChecked) {
        String query = "UPDATE todos SET title = ?, description = ?, isChecked = ? WHERE todoid = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setBoolean(3, isChecked);
            ps.setInt(4, id);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
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
        String query = "DELETE FROM todos WHERE todoid = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return null;
    }
}