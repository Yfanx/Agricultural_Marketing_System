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
    public static int queryDB(User user) {

        connectionDB();

        try{
            String query = "SELECT * FROM User WHERE Username = ? AND PasswordHash = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getUname());
            preparedStatement.setString(2, user.getPs());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setRole(rs.getString("Role"));
                return 1; // 登录成功
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return -1; // 登录失败
    }

    //注册用户
    public static boolean registerDB(User user) {
        connectionDB();
        // 检查用户是否已经存在
        try {
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM User WHERE Username = '" + user.getUname() + "'");
            if (rs.next()) {
                System.out.println("User already exists");
                return false; // 用户已存在，注册失败
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 查询失败，注册失败
        }
        //插入用户
        try {

            String query = "INSERT INTO User(Username, PasswordHash, Role) VALUES (?, ?, 'user')";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getUname());
            preparedStatement.setString(2, user.getPs());
            preparedStatement.executeUpdate();
            closeConnection();
            return true; // 注册成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 注册失败
        }

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
