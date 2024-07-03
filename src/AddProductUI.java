import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Supplier_Information;

public class AddProductUI extends JFrame {
    private JTextField productNameField;
    private JTextField productTypeField;
    private JTextField productIntroField;
    private JTextField priceField;
    private JComboBox<Supplier_Information> supplierComboBox;

    public AddProductUI() {
        setTitle("添加农产品信息");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel productNameLabel = new JLabel("农产品名称:");
        panel.add(productNameLabel);
        productNameField = new JTextField();
        panel.add(productNameField);

        JLabel productTypeLabel = new JLabel("农产品类型:");
        panel.add(productTypeLabel);
        productTypeField = new JTextField();
        panel.add(productTypeField);

        JLabel productIntroLabel = new JLabel("农产品介绍:");
        panel.add(productIntroLabel);
        productIntroField = new JTextField();
        panel.add(productIntroField);

        JLabel priceLabel = new JLabel("价格:");
        panel.add(priceLabel);
        priceField = new JTextField();
        panel.add(priceField);

        JLabel supplierLabel = new JLabel("供应商:");
        panel.add(supplierLabel);
        supplierComboBox = new JComboBox<>();
        List<Supplier_Information> suppliers = DB.getAllSuppliers();
        for (Supplier_Information supplier : suppliers) {
            supplierComboBox.addItem(supplier);
        }
        panel.add(supplierComboBox);

        JButton addButton = new JButton("添加农产品");
        panel.add(new JLabel()); // 空标签用于对齐
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new AddProductButtonListener(this));
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
            return -1; // Invalid input
        }
    }

    public Supplier_Information getSelectedSupplier() {
        return (Supplier_Information) supplierComboBox.getSelectedItem();
    }
}
