import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.*;

public class ViewOrderUI extends JFrame {
    private DefaultTableModel model;
    private JTable table;

    public ViewOrderUI() {
        setTitle("View Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columnNames = {"选择", "订单编号", "农产品名称", "订单创建时间", "总价", "用户ID"};
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
        JScrollPane tableScrollPane = new JScrollPane(table);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton deleteButton = new JButton("删除订单");
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        deleteButton.addActionListener(new DeleteOrderButtonListener(this, table));

        // 显示所有订单信息
        displayAllOrders();
    }

    private void displayAllOrders() {
        List<Sales_Order> orders = DB.getAllOrders();
        for (Sales_Order order : orders) {
            model.addRow(new Object[]{false, order.getOrderID(), order.getAgriculturalName(), order.getOrderCreateTime(), order.getTotalPrice(), order.getUserID()});
        }
    }
}
