import java.sql.*;
import model.*;
import java.util.*;
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
    public static int queryDB(User_Information userInformation) {

        connectionDB();

        try{
            String query = "SELECT * FROM User WHERE Username = ? AND PasswordHash = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userInformation.getUname());
            preparedStatement.setString(2, userInformation.getPs());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                userInformation.setRole(rs.getString("Role"));
                userInformation.setUid(rs.getInt("UserID"));
                return 1; // 登录成功
            }

            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return -1; // 登录失败
    }

    //注册用户
    public static boolean registerDB(User_Information userInformation) {
        connectionDB();
        // 检查用户是否已经存在
        try {
            sql = con.createStatement();
            rs = sql.executeQuery("SELECT * FROM User WHERE Username = '" + userInformation.getUname() + "'");
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
            preparedStatement.setString(1, userInformation.getUname());
            preparedStatement.setString(2, userInformation.getPs());
            preparedStatement.executeUpdate();
            closeConnection();
            return true; // 注册成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 注册失败
        }

    }
    // 查询农产品
    public static List<Agricultural_Information> searchAgriculturalProducts(String name, String type, String supplier) {
        List<Agricultural_Information> products = new ArrayList<>();
        connectionDB();
        try {
            if(name.equals("") && type.equals("")&& supplier.equals("")) {
                return searchAllAgriculturalProducts();
            }else{
                String query = "SELECT a.AgriculturalID, a.AgriculturalName, a.AgriculturalType, a.AgriculturalIntroduction, a.Price, s.SupplierName " +
                        "FROM Agricultural_Information a " +
                        "JOIN Provide p ON a.AgriculturalID = p.AgriculturalID " +
                        "JOIN Supplier_Information s ON p.SupplierID = s.SupplierID " +
                        "WHERE a.AgriculturalName LIKE ? AND a.AgriculturalType LIKE ? AND s.SupplierName LIKE ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, "%" + name + "%");
                preparedStatement.setString(2, "%" + type + "%");
                preparedStatement.setString(3, "%" + supplier + "%");
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("AgriculturalID");
                    String productName = rs.getString("AgriculturalName");
                    String productType = rs.getString("AgriculturalType");
                    String introduction = rs.getString("AgriculturalIntroduction");
                    double price = rs.getDouble("Price");
                    String supplierName = rs.getString("SupplierName");
                    Agricultural_Information product = new Agricultural_Information(id, productName, productType, introduction, price, supplierName);
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
    }
    // 显示农产品
    public static List<Agricultural_Information> searchAllAgriculturalProducts() {
        List<Agricultural_Information> products = new ArrayList<>();
        connectionDB();
        try {
            String query = "SELECT a.AgriculturalID, a.AgriculturalName, a.AgriculturalType, a.AgriculturalIntroduction, a.Price, s.SupplierName " +
                    "FROM Agricultural_Information a " +
                    "JOIN Provide p ON a.AgriculturalID = p.AgriculturalID " +
                    "JOIN Supplier_Information s ON p.SupplierID = s.SupplierID";
            sql = con.createStatement();
            rs = sql.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("AgriculturalID");
                String name = rs.getString("AgriculturalName");
                String type = rs.getString("AgriculturalType");
                String introduction = rs.getString("AgriculturalIntroduction");
                double price = rs.getDouble("Price");
                String supplierName = rs.getString("SupplierName");
                Agricultural_Information product = new Agricultural_Information(id, name, type, introduction, price, supplierName);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
    }

    // 获取客户信息
    public static Customer_Information getCustomerInfo(int userID) {
        connectionDB();
        Customer_Information customer = null;
        try {
            String query = "SELECT * FROM Customer_Information WHERE UserID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String customerIDCard = rs.getString("CustomerIDCard");
                String customerName = rs.getString("CustomerName");
                String customerPhone = rs.getString("CustomerPhone");
                String customerAddress = rs.getString("CustomerAddress");
                customer = new Customer_Information(customerID, customerIDCard, customerName, customerPhone, customerAddress, userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return customer;
    }

    // 更新客户信息
    public static boolean updateCustomerInfo(Customer_Information customer) {
        connectionDB();
        try {
            String query = "UPDATE Customer_Information SET CustomerIDCard = ?, CustomerName = ?, CustomerPhone = ?, CustomerAddress = ? WHERE UserID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, customer.getCustomerIDCard());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getCustomerPhone());
            preparedStatement.setString(4, customer.getCustomerAddress());
            preparedStatement.setInt(5, customer.getUserID());
            preparedStatement.executeUpdate();
            closeConnection();
            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // 插入客户信息
    public static boolean insertCustomerInfo(Customer_Information customer) {
        connectionDB();
        try {
            String query = "INSERT INTO Customer_Information (CustomerIDCard, CustomerName, CustomerPhone, CustomerAddress, UserID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, customer.getCustomerIDCard());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setString(3, customer.getCustomerPhone());
            preparedStatement.setString(4, customer.getCustomerAddress());
            preparedStatement.setInt(5, customer.getUserID());
            preparedStatement.executeUpdate();
            closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //添加到购物车
    // 添加商品到购物车
    public static boolean addToCart(int UserID, int agriculturalID, String supplierName) {
        connectionDB();
        try {
            String query = "INSERT INTO Shopping_Cart (UserID, AgriculturalID, SupplierName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, UserID);
            preparedStatement.setInt(2, agriculturalID);
            preparedStatement.setString(3, supplierName);
            preparedStatement.executeUpdate();
            closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // 获取购物车中的商品
    public static List<Shopping_Cart> getCartItems(int UserID) {
        List<Shopping_Cart> cartItems = new ArrayList<>();
        connectionDB();
        try {
            String query = "SELECT c.CartID, a.AgriculturalID, a.AgriculturalName, a.Price, c.SupplierName " +
                    "FROM Shopping_Cart c " +
                    "JOIN Agricultural_Information a ON c.AgriculturalID = a.AgriculturalID " +
                    "WHERE c.UserID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, UserID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cartID = rs.getInt("CartID");
                int agriculturalID = rs.getInt("AgriculturalID");
                String agriculturalName = rs.getString("AgriculturalName");
                double price = rs.getDouble("Price");
                String supplierName = rs.getString("SupplierName");
                Shopping_Cart cartItem = new Shopping_Cart(cartID, agriculturalID, agriculturalName, price, supplierName);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cartItems;
    }

    // 从购物车中移除商品
    public static boolean removeFromCart(int cartID) {
        connectionDB();
        try {
            String query = "DELETE FROM Shopping_Cart WHERE CartID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, cartID);
            preparedStatement.executeUpdate();
            closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取所有供应商信息
    public static List<Supplier_Information> getAllSuppliers() {
        List<Supplier_Information> suppliers = new ArrayList<>();
        connectionDB();
        try {
            String query = "SELECT * FROM Supplier_Information";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierName = rs.getString("SupplierName");
                String supplierAddress = rs.getString("SupplierAddress");
                String supplierPhone = rs.getString("SupplierPhone");
                Supplier_Information supplier = new Supplier_Information(supplierID, supplierName, supplierAddress, supplierPhone);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return suppliers;
    }


    //查看供应商信息
    public static Supplier_Information getSupplierInformation(String supplierName) {
        connectionDB();
        Supplier_Information supplier = null;
        try {
            String query = "SELECT * FROM Supplier_Information WHERE SupplierName = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, supplierName);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int supplierID = rs.getInt("SupplierID");
                String supplierAddress = rs.getString("SupplierAddress");
                String supplierPhone = rs.getString("SupplierPhone");
                supplier = new Supplier_Information(supplierID, supplierName, supplierAddress, supplierPhone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return supplier;
    }

    // 添加订单
    public static boolean addOrder(Sales_Order order) {
        connectionDB();
        try {
            String query = "INSERT INTO Sales_Order (AgriculturalName, OrderCreateTime, TotalPrice, UserID) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, order.getAgriculturalName());
            preparedStatement.setTimestamp(2, order.getOrderCreateTime());
            preparedStatement.setDouble(3, order.getTotalPrice());
            preparedStatement.setInt(4, order.getUserID());
            preparedStatement.executeUpdate();
            closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取订单
    public static List<Sales_Order> getOrders(int userID) {
        connectionDB();
        List<Sales_Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM Sales_Order WHERE UserID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                String agriculturalName = rs.getString("AgriculturalName");
                Timestamp orderCreateTime = rs.getTimestamp("OrderCreateTime");
                double totalPrice = rs.getDouble("TotalPrice");
                Sales_Order order = new Sales_Order(orderID, agriculturalName, orderCreateTime, totalPrice, userID);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return orders;
    }

    // 添加农产品
    public static boolean addProduct(Agricultural_Information product, int supplierID) {
        connectionDB();
        try {
            String query = "INSERT INTO Agricultural_Information (AgriculturalName, AgriculturalType, AgriculturalIntroduction, Price) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getAgriculturalName());
            preparedStatement.setString(2, product.getAgriculturalType());
            preparedStatement.setString(3, product.getAgriculturalIntroduction());
            preparedStatement.setDouble(4, product.getPrice());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productID = generatedKeys.getInt(1);
                    return addProvide(productID, supplierID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    // 添加供应商
    public static boolean addSupplier(Supplier_Information supplier) {
        connectionDB();
        try {
            String query = "INSERT INTO Supplier_Information (SupplierName, SupplierAddress, SupplierPhone) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getSupplierAddress());
            preparedStatement.setString(3, supplier.getSupplierPhone());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    supplier.setSupplierID(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    // 添加农产品供应关系
    public static boolean addProvide(int agriculturalID, int supplierID) {
        connectionDB();
        try {
            String query = "INSERT INTO Provide (AgriculturalID, SupplierID) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, agriculturalID);
            preparedStatement.setInt(2, supplierID);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
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
