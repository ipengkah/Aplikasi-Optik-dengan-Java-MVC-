/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

;
import DAO.DAO_Kwitansi;
import Model.Kwitansi;
import View.TKwitansi;
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
 * @author sandy
 */
public class Controller_Kwitansi {
     TKwitansi form;
    DAO_Kwitansi model;
    List<Kwitansi> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    
    public Controller_Kwitansi(TKwitansi form) {
        this.form = form;
        model = new DAO_Kwitansi();
        list = model.getAll();
    }
    
    
     //waktu bp dan auto number
    public void tampilurutankode() {    
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglkwitansi().setText(String.valueOf(tgl.format(new Date())));
        
        Kwitansi K = new Kwitansi();
        model.autonumber(K);
        String no_mor=String.valueOf(model.autonumber(K));
        String oke="000".substring(0, 4 - no_mor.length());
        form.getTxtnokwitansi().setText( "KW"+  oke +""+no_mor );
        }
      
    public void reset(){
        tampilurutankode();
        isicombonota();
    }
    //method ini akan dipakai untuk mengisi data kedalam combo kode Supplier
    public void isicombonota() {
        form.getCmbnota().removeAllItems();
        form.getCmbnota().addItem("-Pilih-");
        for (Kwitansi b : model.isicombonota()) {
            form.getCmbnota().addItem(b.getNonota());
        }
    }
    //method ini akan digunakan untuk menampilkan kode pelanggan dan lain-lain berdasarkan inputan combo nama pelanggan
    public void tampilnonota() {
        if (form.getCmbnota().getSelectedIndex() != 0) {
            for (Kwitansi b : model.getNota(form.getCmbnota().getSelectedItem().toString())) {
                form.getTxtdp().setText(String.valueOf(b.getDp()));
                form.getTxtsisa().setText(String.valueOf(b.getSisa()));
            }
        }
    }
    //memanggil method simpan_transaksi pada DAO_Kwitansi      
    //method ini digunakan untuk simpan data pada tabel Kwitansi
    public void simpan_kwitansi() {
        Kwitansi B = new Kwitansi();                         
        B.setNokwitansi(form.getTxtnokwitansi().getText());              
        B.setTglkwitansi(form.getTxttglkwitansi().getText());        
        B.setDp(Double.parseDouble(form.getTxtdp().getText()));
        B.setSisa(Double.parseDouble(form.getTxtsisa().getText()));
        B.setLunas(Double.parseDouble(form.getTxtlunas().getText()));
        B.setNonota(form.getCmbnota().getSelectedItem().toString());
        model.insert(B);
    }
        public void cetak(){
        try{
            Connection conn = Koneksi.Database.KoneksiDB();
            String path = "src/Report/RepKwitansi.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no_nota", (form.getCmbnota().getSelectedItem().toString()));
            
            JasperReport jp=(JasperReport)JRLoader.loadObject(path);
            JasperPrint print = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(print,false);
            
            OutputStream output = new FileOutputStream(new File("D:/Kwitansi"+(form.getTxtnokwitansi().getText())));
            JasperExportManager.exportReportToPdfStream(print,output);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data Tidak Dapat Dicetak!!"+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
