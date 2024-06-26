import java.sql.*;

public class DB {
    public static Connection con = null;
    static Statement sql;
    static ResultSet rs;

    public static void connectionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        String uri = "jdbc:mysql://localhost:3306/Agricultural_Marketing_System?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
        String user = "root";
        String password = "123456";
        try {
            con = DriverManager.getConnection(uri, user, password);
            System.out.println("Database connected successfully");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    // 查询用户
    public static int query(User user) {
        if (con == null) {
            connectionDB();
        }
        try{
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM User WHERE Username = '" + user.getUname() + "' AND PasswordHash = '" + user.getPs() + "'");
            if (rs.next()) {
                user.setRole(rs.getString("Role"));
                return 1; // 登录成功
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return -1; // 登录失败
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                // 打印堆栈跟踪信息
                e.printStackTrace();
            }
        }
    }
}
