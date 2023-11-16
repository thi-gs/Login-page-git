import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseHandler {
    private static final String JDBC_URL = "jdbc:mysql://your_mysql_host:your_mysql_port/your_database";
    private static final String JDBC_USER = "your_mysql_username";
    private static final String JDBC_PASSWORD = "your_mysql_password";

    public static boolean validateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // true if user is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

