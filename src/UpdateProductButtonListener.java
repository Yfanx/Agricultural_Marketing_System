import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Agricultural_Information;

public class UpdateProductButtonListener implements ActionListener {
    private ViewProductUI view;
    private Agricultural_Information product;

    public UpdateProductButtonListener(ViewProductUI view, Agricultural_Information product) {
        this.view = view;
        this.product = product;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        product.setAgriculturalName(view.getProductName());
        product.setAgriculturalType(view.getProductType());
        product.setAgriculturalIntroduction(view.getProductIntroduction());
        product.setPrice(view.getPrice());
        product.setSupplierName(view.getSelectedSupplier());

        if (DB.updateProductInfo(product)) {
            JOptionPane.showMessageDialog(view, "农产品信息更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "农产品信息更新失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
