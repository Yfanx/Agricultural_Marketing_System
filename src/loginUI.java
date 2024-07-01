import javax.swing.*;

import java.awt.*;

public class loginUI extends JFrame {
    JTextField jTextField_id;
    JTextField jTextField_ps;
    JButton jButton;
    login login;

    public loginUI(String title) throws HeadlessException {
        super(title);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        init();
    }

    private void init() {


        JPanel jPanel = new JPanel();
        jPanel.setLayout(null); //布局设置为空，之后可以手动设置组件的坐标位置和大小

        //创建JLabel(用户名)
        JLabel user_label = new JLabel("用户名:");
        user_label.setFont(new Font("微软雅黑", 0, 13));
        //定义组件的位置和宽高
        user_label.setBounds(10, 20, 80, 25);
        //把组件添加到JPanel上
        jPanel.add(user_label);
        //创建文不域用于用户输入
        jTextField_id = new JTextField(20);
        //设置文本域的位置和宽高
        jTextField_id.setBounds(100, 20, 165, 25);
        //把文本域组件添加上
        jPanel.add(jTextField_id);


        //创建JLabel(密码)
        JLabel password_label = new JLabel("密码:");
        password_label.setFont(new Font("微软雅黑", 0, 13));
        //设置位置和大小
        password_label.setBounds(10, 50, 80, 25);
        //添加组件
        jPanel.add(password_label);
        //密码文本域输入
        jTextField_ps = new JPasswordField(20);  //密码输入框，输入密码自动隐藏

        jTextField_ps.setBounds(100, 50, 165, 25);
        jPanel.add(jTextField_ps);

//        // 在密码文本右侧加入一个勾选框，如果勾选则显示密码
//        JCheckBox checkBox = new JCheckBox("显示密码");
//        checkBox.setBounds(270, 50, 100, 25);


        //登录按钮
        JButton login = new JButton("登录");
        login.setBounds(80, 100, 80, 25);
        //注册按钮
        JButton register = new JButton("注册");
        register.setBounds(200, 100, 80, 25);
        jPanel.add(register);
        jPanel.add(login);
//        jPanel.add(checkBox);

        add(jPanel);
        this.login =new login();
        this.login.setView(this);
        login.addActionListener(this.login);

        // 添加注册按钮的事件监听，显示注册界面

        register.addActionListener(e -> {
            registerUI registerView = new registerUI("注册");
            register registerController = new register();
            registerView.setController(registerController);
        });



    }

}
