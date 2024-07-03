import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.*;

public class SupplierInfoButtonListener implements ActionListener {
    private JTable table;

    public SupplierInfoButtonListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Boolean isSelected = (Boolean) table.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                String supplierName = (String) table.getValueAt(i, 6);
                Supplier_Information supplier = DB.getSupplierInformation(supplierName);
                if (supplier != null) {
                    JOptionPane.showMessageDialog(null,
                            "供应商名称: " + supplier.getSupplierName() + "\n" +
                                    "供应商地址: " + supplier.getSupplierAddress() + "\n" +
                                    "供应商电话: " + supplier.getSupplierPhone(),
                            "供应商信息", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "未找到供应商信息", "错误", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "请先选择一个供应商", "提示", JOptionPane.WARNING_MESSAGE);
    }
}
