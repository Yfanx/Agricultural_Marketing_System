import javax.swing.*;
import java.awt.*;
import model.Supplier_Information;

public class ViewSupplierUI extends JFrame {
    private JTextField supplierNameField;
    private JTextField supplierAddressField;
    private JTextField supplierPhoneField;
    private Supplier_Information supplier;

    public ViewSupplierUI(Supplier_Information supplier) {
        this.supplier = supplier;
        setTitle("查看供应商信息");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel supplierNameLabel = new JLabel("供应商名称:");
        panel.add(supplierNameLabel);
        supplierNameField = new JTextField(supplier.getSupplierName());
        supplierNameField.setEditable(false);
        panel.add(supplierNameField);

        JLabel supplierAddressLabel = new JLabel("供应商地址:");
        panel.add(supplierAddressLabel);
        supplierAddressField = new JTextField(supplier.getSupplierAddress());
        supplierAddressField.setEditable(false);
        panel.add(supplierAddressField);

        JLabel supplierPhoneLabel = new JLabel("供应商电话:");
        panel.add(supplierPhoneLabel);
        supplierPhoneField = new JTextField(supplier.getSupplierPhone());
        supplierPhoneField.setEditable(false);
        panel.add(supplierPhoneField);

        JButton updateButton = new JButton("更新");
        JButton saveButton = new JButton("保存");
        JButton deleteButton = new JButton("删除");

        panel.add(updateButton);
        panel.add(saveButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(updateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> {
            supplierNameField.setEditable(true);
            supplierAddressField.setEditable(true);
            supplierPhoneField.setEditable(true);
        });

        saveButton.addActionListener(new SaveSupplierButtonListener(this));
        deleteButton.addActionListener(new DeleteSupplierButtonListener(this));
    }

    public Supplier_Information getSupplier() {
        return new Supplier_Information(
                supplier.getSupplierID(),
                supplierNameField.getText(),
                supplierAddressField.getText(),
                supplierPhoneField.getText()
        );
    }
}
