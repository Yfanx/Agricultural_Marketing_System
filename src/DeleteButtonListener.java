import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;
import javax.swing.JOptionPane;

public class DeleteButtonListener implements ActionListener {
    private JTable table;

    public DeleteButtonListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        boolean success = false; // 标志变量，用于记录是否有任何数据成功删除

        // 从下到上删除选中的行
        for (int i = rowCount - 1; i >= 0; i--) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int cartID = Integer.parseInt(model.getValueAt(i, 1).toString()); // 获取 CartID
                if (DB.removeFromCart(cartID)) {
                    success = true; // 有数据成功删除，设置标志变量为 true
                    model.removeRow(i); // 从表格中删除该行
                }
            }
        }

        if (success) {
            JOptionPane.showMessageDialog(null, "从购物车中删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "从购物车中删除失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
