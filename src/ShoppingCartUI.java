import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.*;

public class ShoppingCartUI extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private User_Information user;

    public ShoppingCartUI(User_Information user) {
        this.user = user;
        setTitle("Shopping Cart - " + user.getUname());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] columnNames = {"选择", "CartID", "供应商名称", "农产品名称", "价格"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 4:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setWidth(0);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10, 10, 760, 400);
        panel.add(tableScrollPane);

        JButton purchaseButton = new JButton("购买");
        purchaseButton.setBounds(200, 450, 120, 25);
        panel.add(purchaseButton);

        JButton deleteButton = new JButton("删除");
        deleteButton.setBounds(400, 450, 120, 25);
        panel.add(deleteButton);

        add(panel);

        // 添加事件监听器
        purchaseButton.addActionListener(new PurchaseButtonListener(user, table));
        deleteButton.addActionListener(new DeleteButtonListener(table));

        // 显示购物车中的商品
        displayShoppingCartItems();
    }

    private void displayShoppingCartItems() {
        List<Shopping_Cart> cartItems = DB.getCartItems(user.getUid());
        for (Shopping_Cart item : cartItems) {
            model.addRow(new Object[]{false, item.getCartID(), item.getSupplierName(), item.getAgriculturalName(), item.getPrice()});
        }
    }
}
