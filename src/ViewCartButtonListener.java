import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User_Information;

public class ViewCartButtonListener implements ActionListener {
    private User_Information user;

    public ViewCartButtonListener(User_Information user) {
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 创建并显示购物车界面
        ShoppingCartUI shoppingCartUI = new ShoppingCartUI(user);
        shoppingCartUI.setVisible(true);
    }
}
