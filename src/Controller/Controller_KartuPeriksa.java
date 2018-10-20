/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.DAO_KartuPeriksa;
import Model.KartuPeriksa;
import View.TKartuPeriksa;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Ipeng
 */
public class Controller_KartuPeriksa {
    TKartuPeriksa form;
    DAO_KartuPeriksa model;
    List<KartuPeriksa> list; //deklara
    
    
      public Controller_KartuPeriksa(TKartuPeriksa form) {
        this.form = form;
        model = new DAO_KartuPeriksa();
      }
    
       public void tampilurutankode() {    
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglkartuperiksa().setText(String.valueOf(tgl.format(new Date())));
        
        KartuPeriksa K = new KartuPeriksa();
        model.autonumber(K);
        String no_mor=String.valueOf(model.autonumber(K));
        String oke="000".substring(0, 4 - no_mor.length());
        form.getTxtnokartuperiksa().setText( "KP"+  oke +""+no_mor );
        }
       
       public void reset(){
           form.getCmbresep().setSelectedIndex(0);
           form.getTxtnmplg().setText("");
           form.getTxtalamat().setText("");
           form.getTxtumur().setText("");
           form.getTxtnotelp().setText("");
           form.getTxtnmplg().requestFocus();
           tampilurutankode();
           isicomboresep();
       }
       
       public void isicomboresep() {
        form.getCmbresep().removeAllItems();
        form.getCmbresep().addItem("-Pilih-");
        for (KartuPeriksa k : model.isicomboresep()) {
            form.getCmbresep().addItem(k.getNoresep());
        }
    }
       
       public void tampilnoresep() {
        if (form.getCmbresep().getSelectedIndex() != 0) {
            for (KartuPeriksa k : model.getNamaplg(form.getCmbresep().getSelectedItem().toString())) {
                form.getTxtnmplg().setText(String.valueOf(k.getNamaplg()));
                form.getTxtalamat().setText(String.valueOf(k.getAlamatplg()));
                form.getTxtumur().setText(String.valueOf(k.getUmur()));
                form.getTxtnotelp().setText(String.valueOf(k.getTlp()));
            }
        }
    }
           //memanggil method simpan_transaksi pada DAO_Kwitansi      
    //method ini digunakan untuk simpan data pada tabel Kwitansi
    public void simpan_kartuperiksa() {
        KartuPeriksa B = new KartuPeriksa();                         
        B.setNokartuperiksa(form.getTxtnokartuperiksa().getText());              
        B.setTglperiksa(form.getTxttglkartuperiksa().getText());        
        B.setNoresep(form.getCmbresep().getSelectedItem().toString());
        model.insert(B);
    }
            public void cetak(){
        try{
            Connection conn = Koneksi.Database.KoneksiDB();
            String path = "src/Report/RepKartuPeriksa.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no_kartuperiksa", (form.getTxtnokartuperiksa().getText()));
            
            JasperReport jp=(JasperReport)JRLoader.loadObject(path);
            JasperPrint print = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(print,false);
            
            OutputStream output = new FileOutputStream(new File("D:/KartuPeriksa"+(form.getTxtnokartuperiksa().getText())));
            JasperExportManager.exportReportToPdfStream(print,output);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data Tidak Dapat Dicetak!!"+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
