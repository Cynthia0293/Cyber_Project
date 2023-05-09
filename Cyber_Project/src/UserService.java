import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {
    private Connection conn = null;
    private PreparedStatement ps = null;
    public boolean checkUser(Users user) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            conn = DBS.getConnection();
            String sql = "select * from users where username =? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int n = rs.getRow();
            if (n > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public int addUser(Users user) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            conn = DBS.getConnection();

            String test = "select * from users where username =?";
            ps = conn.prepareStatement(test);
            ps.setString(1, username);
            ResultSet rs2 = ps.executeQuery();
            rs2.next();
            int n = rs2.getRow();
            if (n > 0) {
                return 1;
            }
            String sql = "insert into users (username,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            int x = ps.executeUpdate();
            if (x > 0) {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 2;
    }
}

