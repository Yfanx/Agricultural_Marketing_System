import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.*;
import javax.swing.JOptionPane;
import java.util.List;

public class RefreshButtonListener implements ActionListener {
    private DefaultTableModel model;

    public RefreshButtonListener(DefaultTableModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 清空表格
        model.setRowCount(0);
        // 从数据库获取所有农产品信息并显示在表格中
        try{
            List<Agricultural_Information> products = DB.searchAllAgriculturalProducts();
            for (Agricultural_Information product : products) {
                model.addRow(new Object[]{false, product.getAgriculturalID(), product.getAgriculturalName(), product.getAgriculturalType(), product.getAgriculturalIntroduction(), product.getPrice(), product.getSupplierName()});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "刷新失败", "错误", JOptionPane.ERROR_MESSAGE);
        }finally {
            JOptionPane.showMessageDialog(null, "刷新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
