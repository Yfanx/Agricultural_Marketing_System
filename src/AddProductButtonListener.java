import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Agricultural_Information;
import model.Supplier_Information;

public class AddProductButtonListener implements ActionListener {
    private AddProductUI addProductUI;

    public AddProductButtonListener(AddProductUI addProductUI) {
        this.addProductUI = addProductUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productName = addProductUI.getProductName();
        String productType = addProductUI.getProductType();
        String productIntro = addProductUI.getProductIntroduction();
        double price = addProductUI.getPrice();
        String supplierName = addProductUI.getSelectedSupplier().toString();

        if (productName.isEmpty() || productType.isEmpty() || productIntro.isEmpty() || price < 0 || supplierName.isEmpty()) {
            JOptionPane.showMessageDialog(addProductUI, "请填写所有字段", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Supplier_Information supplier = DB.getSupplierInformation(supplierName);
        if (supplier == null) {
            JOptionPane.showMessageDialog(addProductUI, "所选供应商不存在", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Agricultural_Information product = new Agricultural_Information();
        product.setAgriculturalName(productName);
        product.setAgriculturalType(productType);
        product.setAgriculturalIntroduction(productIntro);
        product.setPrice(price);

        if (DB.addProduct(product, supplier.getSupplierID())) {
            JOptionPane.showMessageDialog(addProductUI, "农产品添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(addProductUI, "农产品添加失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
