import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.*;

public class ViewOrderButtonListener implements ActionListener {
    private User_Information user;

    public ViewOrderButtonListener(User_Information user) {
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("订单列表");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 窗口居中

        DefaultTableModel model = new DefaultTableModel(new Object[]{"订单编号", "农产品名称", "订单创建时间", "总价"}, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        List<Sales_Order> orders = DB.getOrders(user.getUid());
        for (Sales_Order order : orders) {
            model.addRow(new Object[]{order.getOrderID(), order.getAgriculturalName(), order.getOrderCreateTime(), order.getTotalPrice()});
        }

        frame.setVisible(true);
    }
}
