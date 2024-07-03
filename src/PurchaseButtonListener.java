import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.util.*;
import model.*;
import java.util.Vector;

public class PurchaseButtonListener implements ActionListener {
    private JTable table;
    private User_Information user;

    public PurchaseButtonListener(User_Information user, JTable table) {
        this.user = user;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        double totalPrice = 0;
        boolean success = false;
        StringBuilder agriculturalNames = new StringBuilder();
        Vector<Integer> cartIDs = new Vector<Integer>();
        for (int i = rowCount - 1; i >= 0; i--) {
            Boolean isSelected = (Boolean) model.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int cartID = Integer.parseInt(model.getValueAt(i, 1).toString());
                String supplierName = model.getValueAt(i, 2).toString();
                String agriculturalName = model.getValueAt(i, 3).toString();
                double price = Double.parseDouble(model.getValueAt(i, 4).toString());
                agriculturalNames.append(agriculturalName).append(", ");
                totalPrice += price;
                cartIDs.add(cartID);
                success = true;
            }
        }



        if (success) {
            String agriculturalNameList = agriculturalNames.toString();
            if (agriculturalNameList.endsWith(", ")) {
                agriculturalNameList = agriculturalNameList.substring(0, agriculturalNameList.length() - 2);
            }
            Sales_Order order = new Sales_Order(0, agriculturalNameList, new Timestamp(System.currentTimeMillis()), totalPrice, user.getUid());
            if (DB.addOrder(order)) {
                JOptionPane.showMessageDialog(null, "购买成功，总价: " + totalPrice, "提示", JOptionPane.INFORMATION_MESSAGE);
                // 从购物车中移除已购买的商品
                for (int cartID : cartIDs) {
                    if (DB.removeFromCart(cartID)) {
                        for (int i = 0; i < rowCount; i++) {
                            if (Integer.parseInt(model.getValueAt(i, 1).toString()) == cartID) {
                                model.removeRow(i);
                                break;
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "购买失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择要购买的商品", "提示", JOptionPane.WARNING_MESSAGE);
        }

    }
}
