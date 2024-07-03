import javax.swing.*;
import java.awt.*;

public class registerUI extends JFrame {
    JTextField jTextField_username;
    JPasswordField jPasswordField_password;
    JPasswordField jPasswordField_confirmPassword;
    JButton jButton_register;
    registerLIstener registerLIstenerController;

    public registerUI(String title) {
        super(title);
        setSize(300,200);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        init();
    }

    private void init() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        // 用户名
        JLabel user_label = new JLabel("用户名:");
        user_label.setFont(new Font("微软雅黑", 0, 13));
        user_label.setBounds(10, 20, 80, 25);
        jPanel.add(user_label);

        jTextField_username = new JTextField(20);
        jTextField_username.setBounds(100, 20, 165, 25);
        jPanel.add(jTextField_username);

        // 密码
        JLabel password_label = new JLabel("密码:");
        password_label.setFont(new Font("微软雅黑", 0, 13));
        password_label.setBounds(10, 50, 80, 25);
        jPanel.add(password_label);

        jPasswordField_password = new JPasswordField(20);
        jPasswordField_password.setBounds(100, 50, 165, 25);
        jPanel.add(jPasswordField_password);

        // 确认密码
        JLabel confirm_password_label = new JLabel("确认密码:");
        confirm_password_label.setFont(new Font("微软雅黑", 0, 13));
        confirm_password_label.setBounds(10, 80, 80, 25);
        jPanel.add(confirm_password_label);

        jPasswordField_confirmPassword = new JPasswordField(20);
        jPasswordField_confirmPassword.setBounds(100, 80, 165, 25);
        jPanel.add(jPasswordField_confirmPassword);

        // 注册按钮
        jButton_register = new JButton("注册");
        jButton_register.setBounds(80, 120, 80, 25);
        jPanel.add(jButton_register);

        add(jPanel);
    }

    public void setController(registerLIstener controller) {
        this.registerLIstenerController = controller;
        jButton_register.addActionListener(controller);
        controller.setView(this);
    }
}
