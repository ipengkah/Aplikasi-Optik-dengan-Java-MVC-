
package View;

public class MenuUtama extends javax.swing.JFrame {
    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();        
         setLocationRelativeTo(this);
         Koneksi.Database.KoneksiDB();  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MMenu = new javax.swing.JMenu();
        MPelanggan = new javax.swing.JMenuItem();
        MFrame = new javax.swing.JMenuItem();
        MLensa = new javax.swing.JMenuItem();
        MSupplier = new javax.swing.JMenuItem();
        MTransaksi = new javax.swing.JMenu();
        TDataPembelian = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        TNota = new javax.swing.JMenuItem();
        TKwitansi = new javax.swing.JMenuItem();
        TKartuPeriksa = new javax.swing.JMenuItem();
        MLaporan = new javax.swing.JMenu();
        LPelanggan = new javax.swing.JMenuItem();
        LapPembelian = new javax.swing.JMenuItem();
        LBarang = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        MExit = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\budiluhur\\semester 6\\KKP\\hasil scan\\img-710223136-0001.jpg")); // NOI18N
        jLabel2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\sandy\\Desktop\\img-710224347-0001.jpg")); // NOI18N
        jPanel1.add(jLabel3);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 90, 820, 200);

        MMenu.setText("File Master");

        MPelanggan.setText("Entry Data Pelanggan");
        MPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MPelangganActionPerformed(evt);
            }
        });
        MMenu.add(MPelanggan);

        MFrame.setText("Entry Data Frame");
        MFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MFrameActionPerformed(evt);
            }
        });
        MMenu.add(MFrame);

        MLensa.setText("Entry Data Lensa");
        MLensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MLensaActionPerformed(evt);
            }
        });
        MMenu.add(MLensa);

        MSupplier.setText("Entry Data Supplier");
        MSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MSupplierActionPerformed(evt);
            }
        });
        MMenu.add(MSupplier);

        jMenuBar1.add(MMenu);

        MTransaksi.setText("Transaksi Pembelian");

        TDataPembelian.setText("Entry Data Pembelian");
        TDataPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDataPembelianActionPerformed(evt);
            }
        });
        MTransaksi.add(TDataPembelian);

        jMenuBar1.add(MTransaksi);

        jMenu3.setText("Transaksi Penjualan");

        jMenuItem2.setText("Cetak Resep");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        TNota.setText("Cetak Nota");
        TNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNotaActionPerformed(evt);
            }
        });
        jMenu3.add(TNota);

        TKwitansi.setText("Cetak Kwitansi");
        TKwitansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKwitansiActionPerformed(evt);
            }
        });
        jMenu3.add(TKwitansi);

        TKartuPeriksa.setText("Cetak Kartu Periksa");
        TKartuPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKartuPeriksaActionPerformed(evt);
            }
        });
        jMenu3.add(TKartuPeriksa);

        jMenuBar1.add(jMenu3);

        MLaporan.setText("Laporan");

        LPelanggan.setText("Laporan Data Penjualan");
        LPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LPelangganActionPerformed(evt);
            }
        });
        MLaporan.add(LPelanggan);

        LapPembelian.setText("Laporan Data Pembelian");
        LapPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPembelianActionPerformed(evt);
            }
        });
        MLaporan.add(LapPembelian);

        LBarang.setText("Laporan Data Barang");
        LBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LBarangActionPerformed(evt);
            }
        });
        MLaporan.add(LBarang);

        jMenuItem3.setText("Laporan Data Resep");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MLaporan.add(jMenuItem3);

        jMenuBar1.add(MLaporan);

        MExit.setText("Exit Program");
        MExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MExitMouseClicked(evt);
            }
        });
        jMenuBar1.add(MExit);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(851, 478));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MExitMouseClicked
   System.exit(0);
         
    }//GEN-LAST:event_MExitMouseClicked

    private void MPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MPelangganActionPerformed
         View.MPelanggan P = new View.MPelanggan();
         P.setVisible(true); 
         setLocationRelativeTo(this);
         P.getTxtnmplg().requestFocus();                                 
    }//GEN-LAST:event_MPelangganActionPerformed

    private void TDataPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDataPembelianActionPerformed
        View.TBDataPembelian T = new View.TBDataPembelian();
        T.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_TDataPembelianActionPerformed

    private void MFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MFrameActionPerformed
        View.MFrame k = new View.MFrame();
         k.setVisible(true);   
         setLocationRelativeTo(this);
         k.getTxtnmframe().requestFocus();
    }//GEN-LAST:event_MFrameActionPerformed

    private void MLensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MLensaActionPerformed
         View.MLensa B = new View.MLensa();
         B.setVisible(true);   
         setLocationRelativeTo(this);
         B.getTxtnmlensa().requestFocus();
    }//GEN-LAST:event_MLensaActionPerformed

    private void MSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MSupplierActionPerformed
        View.MSupplier B = new View.MSupplier();
         B.setVisible(true);   
         setLocationRelativeTo(this);
         B.getTxtnmsupp().requestFocus();
    }//GEN-LAST:event_MSupplierActionPerformed

    private void TNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNotaActionPerformed
       View.TNota N = new View.TNota();
       N.setVisible(true);
       setLocationRelativeTo(this);
       N.getTxttglpesan().requestFocus();
    }//GEN-LAST:event_TNotaActionPerformed

    private void TKwitansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKwitansiActionPerformed
       View.TKwitansi K = new View.TKwitansi();
       K.setVisible(true);
       setLocationRelativeTo(this);
    }//GEN-LAST:event_TKwitansiActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        View.TResep R = new View.TResep();
        R.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void TKartuPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKartuPeriksaActionPerformed
        View.TKartuPeriksa R = new View.TKartuPeriksa();
        R.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_TKartuPeriksaActionPerformed

    private void LBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LBarangActionPerformed
        View.LapDataBarang R = new View.LapDataBarang();
        R.setVisible(true);
        setLocationRelativeTo(this);
    }//GEN-LAST:event_LBarangActionPerformed

    private void LPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LPelangganActionPerformed
        View.LapPenjualan R = new View.LapPenjualan();
        R.setVisible(true);
        setLocationRelativeTo(this);  
    }//GEN-LAST:event_LPelangganActionPerformed

    private void LapPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPembelianActionPerformed
        View.LapPembelian R = new View.LapPembelian();
        R.setVisible(true);
        setLocationRelativeTo(this);  
    }//GEN-LAST:event_LapPembelianActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        View.LapResep R = new View.LapResep();
        R.setVisible(true);
        setLocationRelativeTo(this);  
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true); 
                //menu utama maximize otomatis
                /*MenuUtama P = new MenuUtama();
                P.setVisible(true);   
                P.setExtendedState(P.MAXIMIZED_BOTH);//untuk maximize otomatis*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem LBarang;
    private javax.swing.JMenuItem LPelanggan;
    private javax.swing.JMenuItem LapPembelian;
    private javax.swing.JMenu MExit;
    private javax.swing.JMenuItem MFrame;
    private javax.swing.JMenu MLaporan;
    private javax.swing.JMenuItem MLensa;
    private javax.swing.JMenu MMenu;
    private javax.swing.JMenuItem MPelanggan;
    private javax.swing.JMenuItem MSupplier;
    private javax.swing.JMenu MTransaksi;
    private javax.swing.JMenuItem TDataPembelian;
    private javax.swing.JMenuItem TKartuPeriksa;
    private javax.swing.JMenuItem TKwitansi;
    private javax.swing.JMenuItem TNota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
