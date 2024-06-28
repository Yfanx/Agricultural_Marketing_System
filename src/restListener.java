import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class restListener implements ActionListener {
    loginin loginin;
    @Override
    public void actionPerformed(ActionEvent e) {
        loginin.jTextField_id.setText("");
        loginin.jTextField_ps.setText("");
    }
    public void setView(loginin loginin) {
        // TODO Auto-generated method stub
        this.loginin=loginin;
    }
}
