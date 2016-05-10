package DB_Connection;
    import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    public static Connection connection = null;

    public static  Connection getConnection(String DBName,String username,String password) {

        System.out.println("Mysql Testing");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBName, username, password);

        } catch (Exception ex) {
            System.out.println("Connection Error");
            ex.printStackTrace();
        } finally {

            if (connection != null) {
                System.out.println("Connection Success");

            } else {
                System.out.println("Connection Failed!");
            }

        }

        return connection;
    } 
}