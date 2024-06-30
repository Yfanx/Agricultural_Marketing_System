import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.management.relation.Role;
import javax.swing.JOptionPane;

public class controller implements ActionListener{
    loginin loginin;
    User user;
    //TODO 添加一个对象用于打开新的界面;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        DB db = new DB();
        user = new User();
        user.setUname(loginin.jTextField_id.getText());
        user.setPs(loginin.jTextField_ps.getText());
        int role = db.queryDB(user);
        if (role != -1) {
            System.out.println("登录成功！");
            System.out.println("用户名：" + loginin.jTextField_id.getText());
            System.out.println("密 码：" + loginin.jTextField_ps.getText());
            System.out.println("-----------------");
            if(user.getRole().equals("admin")) {
                JOptionPane.showMessageDialog(loginin, "登录成功！欢迎管理员", "提示", JOptionPane.NO_OPTION);
            }else if(user.getRole().equals("user")) {
                JOptionPane.showMessageDialog(loginin, "登录成功！", "提示", JOptionPane.NO_OPTION);
            }
            //TODO 添加一个对象用于打开新的界面;
            loginin.dispose();
        } else {
            System.out.println("登录失败！");
            System.out.println("用户名：" + loginin.jTextField_id.getText());
            System.out.println("密 码：" + loginin.jTextField_ps.getText());
            System.out.println("-----------------");
            loginin.jTextField_ps.setText("");
            JOptionPane.showMessageDialog(loginin, "输入信息有误，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void setView(loginin loginin) {
        // TODO Auto-generated method stub
        this.loginin=loginin;
    }
}
