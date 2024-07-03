import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Supplier_Information;

public class AddSupplierButtonListener implements ActionListener {
    private AddSupplierUI addSupplierUI;

    public AddSupplierButtonListener(AddSupplierUI addSupplierUI) {
        this.addSupplierUI = addSupplierUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String supplierName = addSupplierUI.getSupplierName();
        String supplierAddress = addSupplierUI.getSupplierAddress();
        String supplierPhone = addSupplierUI.getSupplierPhone();

        if (supplierName.isEmpty() || supplierAddress.isEmpty() || supplierPhone.isEmpty()) {
            JOptionPane.showMessageDialog(addSupplierUI, "请正确填写所有栏目", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Supplier_Information supplier = new Supplier_Information(0, supplierName, supplierAddress, supplierPhone);
        if (DB.addSupplier(supplier)) {
            JOptionPane.showMessageDialog(addSupplierUI, "供应商信息添加成功", "Success", JOptionPane.INFORMATION_MESSAGE);
            addSupplierUI.dispose();
        } else {
            JOptionPane.showMessageDialog(addSupplierUI, "供应商信息添加失败", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
