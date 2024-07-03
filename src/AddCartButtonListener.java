import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User_Information;

public class AddCartButtonListener implements ActionListener {
    private JTable table;
    private User_Information user;

    public AddCartButtonListener(JTable table, User_Information user) {
        this.table = table;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rowCount = table.getRowCount();
        boolean success = false; // 标志变量，用于记录是否有任何数据成功添加
        for (int i = 0; i < rowCount; i++) {
            Boolean isSelected = (Boolean) table.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int agriculturalID = (Integer) table.getValueAt(i, 1);
                String supplierName = (String) table.getValueAt(i, 6);
                if (DB.addToCart(user.getUid(), agriculturalID, supplierName)) {
                    success = true; // 有数据成功添加，设置标志变量为 true
                }
            }
        }
        if (success) {
            JOptionPane.showMessageDialog(null, "添加到购物车成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "添加到购物车失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
