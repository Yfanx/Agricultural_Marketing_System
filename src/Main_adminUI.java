import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.*;

public class Main_adminUI extends JFrame {
    private JTextField productNameField;
    private JTextField productTypeField;
    private JTextField supplierNameField;
    private DefaultTableModel model;
    private JTable table;
    private User_Information user;

    public Main_adminUI(User_Information user) {
        this.user = user;
        setTitle("Admin Main UI - Welcome " + user.getUname());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false);
        init();
        setVisible(true);
    }

    private void init() {
        // 创建主面板
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // 顶部欢迎信息
        JLabel welcomeLabel = new JLabel("欢迎你回来：" + user.getUname(), JLabel.CENTER);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        welcomeLabel.setBounds(10, 10, 880, 30);
        panel.add(welcomeLabel);

        // 表单面板
        JLabel productNameLabel = new JLabel("农产品名称：");
        productNameLabel.setFont(new Font("微软雅黑", 0, 13));
        productNameLabel.setBounds(10, 50, 100, 25);
        panel.add(productNameLabel);

        productNameField = new JTextField(20);
        productNameField.setBounds(120, 50, 165, 25);
        panel.add(productNameField);

        JLabel productTypeLabel = new JLabel("农产品类型：");
        productTypeLabel.setFont(new Font("微软雅黑", 0, 13));
        productTypeLabel.setBounds(300, 50, 100, 25);
        panel.add(productTypeLabel);

        productTypeField = new JTextField(20);
        productTypeField.setBounds(410, 50, 165, 25);
        panel.add(productTypeField);

        JLabel supplierNameLabel = new JLabel("供应商名称：");
        supplierNameLabel.setFont(new Font("微软雅黑", 0, 13));
        supplierNameLabel.setBounds(600, 50, 100, 25);
        panel.add(supplierNameLabel);

        supplierNameField = new JTextField(20);
        supplierNameField.setBounds(710, 50, 165, 25);
        panel.add(supplierNameField);

        JButton searchButton = new JButton("查询");
        searchButton.setBounds(600, 90, 80, 25);
        panel.add(searchButton);

        JButton refreshButton = new JButton("刷新");
        refreshButton.setBounds(700, 90, 80, 25);
        panel.add(refreshButton);

        // 表格
        String[] columnNames = {"选择", "农产品编号", "农产品名称", "农产品类型", "农产品介绍", "价格", "供应商名称"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 5:
                        return Double.class;
                    default:
                        return String.class;
                }
            }
        };
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(10, 130, 880, 300);
        panel.add(tableScrollPane);

        // 底部表单和按钮
        JButton viewProductButton = new JButton("查看农产品信息");
        viewProductButton.setBounds(70, 450, 150, 25);
        panel.add(viewProductButton);

        JButton addProductButton = new JButton("添加农产品信息");
        addProductButton.setBounds(230, 450, 150, 25);
        panel.add(addProductButton);

        JButton viewSupplierButton = new JButton("查看供应商信息");
        viewSupplierButton.setBounds(390, 450, 150, 25);
        panel.add(viewSupplierButton);

        JButton addSupplierButton = new JButton("添加供应商信息");
        addSupplierButton.setBounds(550, 450, 150, 25);
        panel.add(addSupplierButton);

        JButton viewOrderButton = new JButton("查看订单信息");
        viewOrderButton.setBounds(710, 450, 150, 25);
        panel.add(viewOrderButton);

        add(panel);

        // 添加事件监听器
        searchButton.addActionListener(new SearchButtonListener(productNameField, productTypeField, supplierNameField,model));
        refreshButton.addActionListener(new RefreshButtonListener(model));
        addSupplierButton.addActionListener(e -> new AddSupplierUI());
        addProductButton.addActionListener(e -> new AddProductUI());
        viewProductButton.addActionListener(new ViewProductButtonListener(table));
        viewSupplierButton.addActionListener(new ViewSupplierButtonListener(table));
        viewOrderButton.addActionListener(new AdminViewOrderButtonListener(table));

        // 初始化时显示所有农产品信息
        displayAllAgriculturalProducts();
    }

    private void displayAllAgriculturalProducts() {
        List<Agricultural_Information> products = DB.searchAllAgriculturalProducts();
        for (Agricultural_Information product : products) {
            model.addRow(new Object[]{false, product.getAgriculturalID(), product.getAgriculturalName(), product.getAgriculturalType(), product.getAgriculturalIntroduction(), product.getPrice(), product.getSupplierName()});
        }
    }

}
