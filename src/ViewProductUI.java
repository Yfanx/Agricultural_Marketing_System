import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Supplier_Information;
import model.Agricultural_Information;

public class ViewProductUI extends JFrame {
    private JTextField productNameField;
    private JTextField productTypeField;
    private JTextField productIntroField;
    private JTextField priceField;
    private JComboBox<String> supplierComboBox;
    private Agricultural_Information product;

    public ViewProductUI(Agricultural_Information product) {
        this.product = product;
        setTitle("查看农产品信息");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel productNameLabel = new JLabel("农产品名称:");
        panel.add(productNameLabel);
        productNameField = new JTextField(product.getAgriculturalName());
        productNameField.setEditable(false);
        panel.add(productNameField);

        JLabel productTypeLabel = new JLabel("农产品类型:");
        panel.add(productTypeLabel);
        productTypeField = new JTextField(product.getAgriculturalType());
        productTypeField.setEditable(false);
        panel.add(productTypeField);

        JLabel productIntroLabel = new JLabel("农产品介绍:");
        panel.add(productIntroLabel);
        productIntroField = new JTextField(product.getAgriculturalIntroduction());
        productIntroField.setEditable(false);
        panel.add(productIntroField);

        JLabel priceLabel = new JLabel("价格:");
        panel.add(priceLabel);
        priceField = new JTextField(String.valueOf(product.getPrice()));
        priceField.setEditable(false);
        panel.add(priceField);

        JLabel supplierLabel = new JLabel("供应商:");
        panel.add(supplierLabel);
        supplierComboBox = new JComboBox<>();
        List<Supplier_Information> suppliers = DB.getAllSuppliers();
        for (Supplier_Information supplier : suppliers) {
            supplierComboBox.addItem(supplier.getSupplierName());
        }
        supplierComboBox.setSelectedItem(product.getSupplierName());
        supplierComboBox.setEnabled(false);
        panel.add(supplierComboBox);

        JButton updateButton = new JButton("更新");
        panel.add(updateButton);

        JButton saveButton = new JButton("保存");
        saveButton.setEnabled(false);
        panel.add(saveButton);

        JButton deleteButton = new JButton("删除");
        panel.add(new JLabel()); // 占位符
        panel.add(deleteButton);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(updateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> {
            productNameField.setEditable(true);
            productTypeField.setEditable(true);
            productIntroField.setEditable(true);
            priceField.setEditable(true);
            supplierComboBox.setEnabled(true);
            saveButton.setEnabled(true);
        });

        saveButton.addActionListener(new UpdateProductButtonListener(this, product));
        deleteButton.addActionListener(new DeleteProductButtonListener(this, product));
    }

    public String getProductName() {
        return productNameField.getText();
    }

    public String getProductType() {
        return productTypeField.getText();
    }

    public String getProductIntroduction() {
        return productIntroField.getText();
    }

    public double getPrice() {
        try {
            return Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            return -1; // 输入无效
        }
    }

    public String getSelectedSupplier() {
        return (String) supplierComboBox.getSelectedItem();
    }
}
