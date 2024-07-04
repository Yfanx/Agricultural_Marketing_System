import javax.swing.*;
import java.awt.*;

public class AddSupplierUI extends JFrame {
    private JTextField supplierNameField;
    private JTextField supplierAddressField;
    private JTextField supplierPhoneField;

    public AddSupplierUI() {
        setTitle("添加供应商信息");
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
        supplierNameField = new JTextField();
        panel.add(supplierNameField);

        JLabel supplierAddressLabel = new JLabel("供应商地址:");
        panel.add(supplierAddressLabel);
        supplierAddressField = new JTextField();
        panel.add(supplierAddressField);

        JLabel supplierPhoneLabel = new JLabel("供应商电话:");
        panel.add(supplierPhoneLabel);
        supplierPhoneField = new JTextField();
        panel.add(supplierPhoneField);

        JButton addButton = new JButton("添加");
        panel.add(new JLabel()); // 占位符
        panel.add(addButton);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new AddSupplierButtonListener(this));
    }

    public String getSupplierName() {
        return supplierNameField.getText();
    }

    public String getSupplierAddress() {
        return supplierAddressField.getText();
    }

    public String getSupplierPhone() {
        return supplierPhoneField.getText();
    }
}
