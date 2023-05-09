
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBS {
    private static final String driver="org.postgresql.Driver";
    private static final String url="jdbc:postgresql://localhost:7777/Users";
    private static final String username="postgres";
    private static final String password="020703";
    private static  Connection con=null;

    static
    {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        if(con==null){
            try {
                con=DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}