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
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createFormPanel());
        mainPanel.add(createButtonPanel());

        add(mainPanel);

        if (customer != null) {
            idCardField.setText(customer.getCustomerIDCard());
            nameField.setText(customer.getCustomerName());
            phoneField.setText(customer.getCustomerPhone());
            addressField.setText(customer.getCustomerAddress());
        }

        setEditableFields(false);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel idCardLabel = new JLabel("身份证:");
        idCardField = new JTextField(15);

        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("电话:");
        phoneField = new JTextField(15);

        JLabel addressLabel = new JLabel("地址:");
        addressField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idCardLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idCardField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateButton = new JButton("更新");
        JButton saveButton = new JButton("保存");

        updateButton.addActionListener(e -> setEditableFields(true));

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
            setEditableFields(false);
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(saveButton);

        return buttonPanel;
    }

    private void setEditableFields(boolean editable) {
        idCardField.setEditable(editable);
        nameField.setEditable(editable);
        phoneField.setEditable(editable);
        addressField.setEditable(editable);
    }
}
