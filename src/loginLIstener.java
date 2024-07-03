import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.*;

public class loginLIstener implements ActionListener{
    loginUI loginUI;
    User_Information userInformation;
    //TODO 添加一个对象用于打开新的界面;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        DB db = new DB();
        userInformation = new User_Information();
        userInformation.setUname(loginUI.jTextField_id.getText());
        userInformation.setPs(loginUI.jTextField_ps.getText());
        int role = db.queryDB(userInformation);
        if (role != -1) {
            System.out.println("登录成功！");
            System.out.println("用户名：" + loginUI.jTextField_id.getText());
            System.out.println("密 码：" + loginUI.jTextField_ps.getText());
            System.out.println("-----------------");
            if(userInformation.getRole().equals("admin")) {
                JOptionPane.showMessageDialog(loginUI, "登录成功！欢迎管理员", "提示", JOptionPane.NO_OPTION);
                // 打开管理员界面
                new Main_adminUI(userInformation).setVisible(true);
            }else if(userInformation.getRole().equals("user")) {
                String welcome = "登录成功！欢迎 "+ userInformation.getUname();
                JOptionPane.showMessageDialog(loginUI, welcome, "提示", JOptionPane.NO_OPTION);
                // 打开用户界面
                new Main_userUI(userInformation).setVisible(true);
            }
            //TODO 添加一个对象用于打开新的界面;
            loginUI.dispose();
        } else {
            System.out.println("登录失败！");
            System.out.println("用户名：" + loginUI.jTextField_id.getText());
            System.out.println("密 码：" + loginUI.jTextField_ps.getText());
            System.out.println("-----------------");
            loginUI.jTextField_ps.setText("");
            JOptionPane.showMessageDialog(loginUI, "输入信息有误，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void setView(loginUI loginUI) {
        // TODO Auto-generated method stub
        this.loginUI = loginUI;
    }
}
