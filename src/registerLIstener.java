import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;

public class registerLIstener implements ActionListener {
    registerUI registerUI;

    public void setView(registerUI registerUI) {
        this.registerUI = registerUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = registerUI.jTextField_username.getText();
        String password = new String(registerUI.jPasswordField_password.getPassword());
        String confirmPassword = new String(registerUI.jPasswordField_confirmPassword.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(registerUI, "所有字段必须填写", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registerUI, "密码不匹配", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 执行注册逻辑，例如保存到数据库

        boolean success = registerUser(username, password, "user");
        if (success) {
            JOptionPane.showMessageDialog(registerUI, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            registerUI.dispose();
        } else {
            JOptionPane.showMessageDialog(registerUI, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean registerUser(String username, String password, String role) {
        User_Information userInformation = new User_Information(username, password, role);
        return DB.registerDB(userInformation);
    }
}
