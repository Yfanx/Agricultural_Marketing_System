import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import model.Supplier_Information;

public class ViewSupplierButtonListener implements ActionListener {
    private JTable table;

    public ViewSupplierButtonListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String supplierName = (String) table.getValueAt(selectedRow, 6);
            Supplier_Information supplier = DB.getSupplierInformation(supplierName);
            if (supplier != null) {
                new ViewSupplierUI(supplier);
            }
        } else {
            JOptionPane.showMessageDialog(table, "请选择一行数据进行查看。", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
