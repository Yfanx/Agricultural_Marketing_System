import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.User_Information;

public class ModifyInfoButtonListener implements ActionListener {
    private User_Information user;

    public ModifyInfoButtonListener(User_Information user) {
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(user.getUid());
        new CustomerInfoUI(user.getUid());
    }
}
