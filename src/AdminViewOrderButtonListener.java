import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class AdminViewOrderButtonListener implements ActionListener {
    private JTable table;

    public AdminViewOrderButtonListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ViewOrderUI();
    }
}
