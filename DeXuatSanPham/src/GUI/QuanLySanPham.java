package GUI;

import DAO.DBConnect;
import DAO.SanPham_DAO;
import DAO.ThuongHieu_DAO;
import POJO.SanPham;
import POJO.ThuongHieu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class QuanLySanPham extends javax.swing.JFrame {
    SanPham_DAO sanPhamDao = new SanPham_DAO();
    ThuongHieu_DAO thuongHieuDAO = new ThuongHieu_DAO();
    public SanPham_DAO sp_dao;
    public ThuongHieu_DAO th_dao;
    DefaultTableModel dtm = new DefaultTableModel();
    List <SanPham> listsp = new ArrayList<>();
    List <ThuongHieu> listth = new ArrayList<>();
    /**
     * Creates new form ThemSanPham
     */
    public QuanLySanPham() {
        this.setTitle("Quản Lý Sản Phẩm");
        initComponents();
        LoadDuLieuSanPham();
    }
    
        // <editor-fold defaultstate="collapsed" desc="Methods">
    public void LoadDuLieuSanPham() {
        List <SanPham> list = SanPham_DAO.layThongTinDuLieuSanPham();
        DefaultTableModel modelSP= (DefaultTableModel) JTableDSSP.getModel();
        modelSP.setColumnIdentifiers(new Object[] {"Tên sản phẩm", "Giá", "RAM"});
        modelSP.setRowCount(0);
        for (SanPham sp: list) {
          modelSP.addRow(new Object[] {
           sp.getName(),sp.getGia(),sp.getRam()
          });
        }
    }
    public void ThemNutSP() {
        try {
            SanPham sp =new SanPham();
            sp.setName(txt_tenSP.getText());
            sp.setGia(Integer.parseInt(txt_giaTien.getText()));
            sp.setRam(txt_ram.getText());
            SanPham_DAO sp_dao = new SanPham_DAO(); 
            listsp.add(sp);
            sp_dao.TaoNutSP(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ThemNutTH() {
        try {
            ThuongHieu th =new ThuongHieu();
            th.setName(txt_thuongHieu.getText());
            ThuongHieu_DAO th_dao = new ThuongHieu_DAO(); 
            listth.add(th);
            th_dao.TaoNutTH(th);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void TaoQuanHeLienKet(){
        try {
            SanPham_DAO sp_dao = new SanPham_DAO();
            String tenSanPham = txt_tenSP.getText();
            String tenThuongHieu = txt_thuongHieu.getText();
            String relationshipType = txt_quanHe.getText();
            sp_dao.TaoQuanHe(relationshipType, tenSanPham, tenThuongHieu); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void XoaNutSP(){
        try {
            SanPham sp = new SanPham();
            sp.setName(txt_tenSP.getText());
            SanPham_DAO sp_dao = new SanPham_DAO(); 
            sp_dao.XoaNutSP(sp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void XoaNutTH(){
        try {
            ThuongHieu th =new ThuongHieu();
            th.setName(txt_thuongHieu.getText());
            ThuongHieu_DAO th_dao = new ThuongHieu_DAO(); 
            th_dao.XoaNutTH(th);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void TaiLai(){
        ckb_SanPham.setSelected(false);
        ckb_ThuongHieu.setSelected(false);
        ckb_ca2.setSelected(false);
        txt_tenSP.setText("");
        txt_giaTien.setText("");
        txt_thuongHieu.setText("");
        txt_ram.setText("");
        txt_quanHe.setText("");
        LoadDuLieuSanPham();
    }
    
    public void ThemSanPhamVaLienKetVoiThuongHieu(){
        SanPham sp = new SanPham();
        DBConnect con = DBConnect.getInstance();
        SanPham_DAO sp_dao = new SanPham_DAO();
        ThuongHieu_DAO th_dao = new ThuongHieu_DAO();
        ThuongHieu th =new ThuongHieu();
        
        th.setName(txt_thuongHieu.getText());
        sp.setGia(Integer.parseInt(txt_giaTien.getText()));
        sp.setRam(txt_ram.getText());
        
        String tenSanPham = txt_tenSP.getText();
        String tenThuongHieu = txt_thuongHieu.getText();
        
        boolean success = false;
        try {
            success = sp_dao.ThemSanPhamVaLienKetVoiThuongHieu(tenSanPham, tenThuongHieu, sp);

            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
            } else {
                System.out.println("Thêm sản phẩm thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }  
    }
    // </editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_tenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_thuongHieu = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        ckb_SanPham = new javax.swing.JCheckBox();
        ckb_ThuongHieu = new javax.swing.JCheckBox();
        ckb_ca2 = new javax.swing.JCheckBox();
        btn_xoa = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_quanHe = new javax.swing.JTextField();
        txt_giaTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ram = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_troVe = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableDSSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên sản phẩm:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Thương hiệu:");

        btn_them.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        buttonGroup1.add(ckb_SanPham);
        ckb_SanPham.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ckb_SanPham.setText("Sản phẩm");

        buttonGroup1.add(ckb_ThuongHieu);
        ckb_ThuongHieu.setText("Thương hiệu");

        buttonGroup1.add(ckb_ca2);
        ckb_ca2.setText("Cả 2");

        btn_xoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Quan hệ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Giá tiền:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Ram:");

        btn_troVe.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_troVe.setText("Trở về");
        btn_troVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_troVeActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Tải lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_them)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoa)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sua)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ram)
                            .addComponent(txt_thuongHieu)
                            .addComponent(txt_quanHe)
                            .addComponent(txt_giaTien)
                            .addComponent(txt_tenSP))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ckb_ThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckb_SanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_troVe)
                    .addComponent(ckb_ca2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_giaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckb_SanPham))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckb_ThuongHieu)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_quanHe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckb_ca2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_thuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them)
                    .addComponent(btn_xoa)
                    .addComponent(btn_sua)
                    .addComponent(btn_troVe)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        JTableDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTableDSSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableDSSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableDSSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel1)
                        .addGap(0, 150, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_troVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_troVeActionPerformed
        // TODO add your handling code here:
        LoaiUser LU = new LoaiUser();
        LU.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_troVeActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        SuaSanPham ssp = new SuaSanPham();
        ssp.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if(ckb_SanPham.isSelected()==true){
            if(txt_thuongHieu.getText().isEmpty()){
                ThemNutSP();
            } else{
                ThemSanPhamVaLienKetVoiThuongHieu();
//                JOptionPane.showMessageDialog(rootPane, "Không tồn tại thương hiệu này");
            }
//            if(equals(txt_thuongHieu.getText() != null)){
//                ThemSanPhamVaLienKetVoiThuongHieu();
//            }
        }
        if(ckb_ThuongHieu.isSelected()==true){
            ThemNutTH();
        }
        if(ckb_ca2.isSelected()==true){
            ThemNutSP();
            ThemNutTH();
            TaoQuanHeLienKet();
        } 
        if(ckb_SanPham.isSelected()==false && ckb_ThuongHieu.isSelected()==false && ckb_ca2.isSelected()==false){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn yêu cầu!");
        }
        LoadDuLieuSanPham();
        //JOptionPane.showMessageDialog(null, "Thành Công!");
        txt_tenSP.requestFocus();  
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        if(ckb_SanPham.isSelected()==true){
            XoaNutSP();
        }
        if(ckb_ThuongHieu.isSelected()==true){
            XoaNutTH();
        }
        if(ckb_ca2.isSelected()==true){
            XoaNutSP();
            XoaNutTH();
        } 
        if(ckb_SanPham.isSelected()==false && ckb_ThuongHieu.isSelected()==false && ckb_ca2.isSelected()==false) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn yêu cầu!");
        }
        LoadDuLieuSanPham();
        JOptionPane.showMessageDialog(null, "Thành Công!");
        txt_tenSP.requestFocus();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TaiLai();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JTableDSSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableDSSPMouseClicked
        // TODO add your handling code here:
        int i = JTableDSSP.getSelectedRow();
        String tensp = JTableDSSP.getValueAt(i, 0).toString().trim();
        String gia = JTableDSSP.getValueAt(i, 1).toString().trim();
        String ram = JTableDSSP.getValueAt(i, 2).toString().trim();
        
        txt_tenSP.setText(tensp);
        txt_giaTien.setText(gia);
        txt_ram.setText(ram);
    }//GEN-LAST:event_JTableDSSPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLySanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLySanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableDSSP;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_troVe;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox ckb_SanPham;
    private javax.swing.JCheckBox ckb_ThuongHieu;
    private javax.swing.JCheckBox ckb_ca2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_giaTien;
    private javax.swing.JTextField txt_quanHe;
    private javax.swing.JTextField txt_ram;
    private javax.swing.JTextField txt_tenSP;
    private javax.swing.JTextField txt_thuongHieu;
    // End of variables declaration//GEN-END:variables
}
