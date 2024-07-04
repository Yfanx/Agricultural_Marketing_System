import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Supplier_Information;

public class DeleteSupplierButtonListener implements ActionListener {
    private ViewSupplierUI supplierUI;

    public DeleteSupplierButtonListener(ViewSupplierUI supplierUI) {
        this.supplierUI = supplierUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Supplier_Information supplier = supplierUI.getSupplier();
        if (DB.deleteSupplier(supplier.getSupplierID())) {
            JOptionPane.showMessageDialog(supplierUI, "供应商删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            supplierUI.dispose();
        } else {
            JOptionPane.showMessageDialog(supplierUI, "供应商删除失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
