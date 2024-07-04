import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Agricultural_Information;

public class DeleteProductButtonListener implements ActionListener {
    private ViewProductUI view;
    private Agricultural_Information product;

    public DeleteProductButtonListener(ViewProductUI view, Agricultural_Information product) {
        this.view = view;
        this.product = product;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(view, "你确定要删除这个农产品吗？", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            if (DB.deleteProductInfo(product)) {
                JOptionPane.showMessageDialog(view, "农产品删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "农产品删除失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
