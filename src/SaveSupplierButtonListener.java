import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Supplier_Information;

public class SaveSupplierButtonListener implements ActionListener {
    private ViewSupplierUI supplierUI;

    public SaveSupplierButtonListener(ViewSupplierUI supplierUI) {
        this.supplierUI = supplierUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Supplier_Information supplier = supplierUI.getSupplier();
        if (DB.updateSupplierInfo(supplier)) {
            JOptionPane.showMessageDialog(supplierUI, "信息更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(supplierUI, "信息更新失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
