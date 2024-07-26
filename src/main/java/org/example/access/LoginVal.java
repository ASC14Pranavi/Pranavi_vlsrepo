package org.example.access;
import org.example.model.Login;
import org.example.JdbcConn.Dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginVal {
    private Dbconn dbConn = new Dbconn();

    public boolean authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
