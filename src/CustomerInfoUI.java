import javax.swing.*;
import java.awt.*;
import model.Customer_Information;

public class CustomerInfoUI extends JFrame {
    private JTextField idCardField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField addressField;
    private Customer_Information customer;
    private int userID;

    public CustomerInfoUI(int userID) {
        this.userID = userID;
        customer = DB.getCustomerInfo(userID);
        setTitle("Update Customer Info");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idCardLabel = new JLabel("身份证:");
        panel.add(idCardLabel);
        idCardField = new JTextField();
        panel.add(idCardField);

        JLabel nameLabel = new JLabel("姓名:");
        panel.add(nameLabel);
        nameField = new JTextField();
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("电话:");
        panel.add(phoneLabel);
        phoneField = new JTextField();
        panel.add(phoneField);

        JLabel addressLabel = new JLabel("地址:");
        panel.add(addressLabel);
        addressField = new JTextField();
        panel.add(addressField);

        JButton updateButton = new JButton("更新");
        panel.add(updateButton);

        JButton saveButton = new JButton("保存");
        panel.add(saveButton);

        add(panel);

        updateButton.addActionListener(e -> {
            idCardField.setEditable(true);
            nameField.setEditable(true);
            phoneField.setEditable(true);
            addressField.setEditable(true);
        });

        saveButton.addActionListener(e -> {
            System.out.println("UserID: " + userID); // 打印 UserID 进行调试
            if (customer == null) {
                customer = new Customer_Information(0, idCardField.getText(), nameField.getText(), phoneField.getText(), addressField.getText(), userID);
                if (DB.insertCustomerInfo(customer)) {
                    JOptionPane.showMessageDialog(this, "信息保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "信息保存失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                customer.setCustomerIDCard(idCardField.getText());
                customer.setCustomerName(nameField.getText());
                customer.setCustomerPhone(phoneField.getText());
                customer.setCustomerAddress(addressField.getText());
                if (DB.updateCustomerInfo(customer)) {
                    JOptionPane.showMessageDialog(this, "信息更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "信息更新失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
            idCardField.setEditable(false);
            nameField.setEditable(false);
            phoneField.setEditable(false);
            addressField.setEditable(false);
        });

        if (customer != null) {
            idCardField.setText(customer.getCustomerIDCard());
            nameField.setText(customer.getCustomerName());
            phoneField.setText(customer.getCustomerPhone());
            addressField.setText(customer.getCustomerAddress());
        }

        idCardField.setEditable(false);
        nameField.setEditable(false);
        phoneField.setEditable(false);
        addressField.setEditable(false);
    }
}
