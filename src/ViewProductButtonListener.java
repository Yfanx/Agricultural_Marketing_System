import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Agricultural_Information;

public class ViewProductButtonListener implements ActionListener {
    private JTable table;

    public ViewProductButtonListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Boolean isSelected = (Boolean) table.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                int agriculturalID = (Integer) table.getValueAt(i, 1);
                Agricultural_Information product = DB.getAgriculturalInformationById(agriculturalID);
                if (product != null) {
                    new ViewProductUI(product);
                }
            }
        }
    }
}
