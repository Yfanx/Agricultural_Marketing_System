import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;

public class DeleteOrderButtonListener implements ActionListener {
    private ViewOrderUI viewOrderUI;
    private JTable table;

    public DeleteOrderButtonListener(ViewOrderUI viewOrderUI, JTable table) {
        this.viewOrderUI = viewOrderUI;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        boolean success = false;

        for (int i = rowCount - 1; i >= 0; i--) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int orderID = (int) model.getValueAt(i, 1);
                if (DB.deleteOrder(orderID)) {
                    model.removeRow(i);
                    success = true;
                } else {
                    JOptionPane.showMessageDialog(viewOrderUI, "删除订单失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (success) {
            JOptionPane.showMessageDialog(viewOrderUI, "订单删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(viewOrderUI, "请选择要删除的订单", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }
}
