import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.Agricultural_Information;

public class SearchButtonListener implements ActionListener {
    private JTextField productNameField;
    private JTextField productTypeField;
    private JTextField supplierNameField;
    private DefaultTableModel model;

    public SearchButtonListener(JTextField productNameField, JTextField productTypeField, JTextField supplierNameField, DefaultTableModel model) {
        this.productNameField = productNameField;
        this.productTypeField = productTypeField;
        this.supplierNameField = supplierNameField;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = productNameField.getText();
        String type = productTypeField.getText();
        String supplier = supplierNameField.getText();

        // 清空表格
        model.setRowCount(0);

        // 从数据库中查询符合条件的农产品并显示在表格中
        List<Agricultural_Information> products = DB.searchAgriculturalProducts(name, type, supplier);
        for (Agricultural_Information product : products) {
            model.addRow(new Object[]{false, product.getAgriculturalID(), product.getAgriculturalName(), product.getAgriculturalType(), product.getAgriculturalIntroduction(), product.getPrice(), product.getSupplierName()});
        }

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "未找到符合条件的农产品", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
