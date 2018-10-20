/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_CetakResep;
import Model.CetakResep;
import View.TResep;
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
 * @author 
 */
public class Controller_CetakResep {
    TResep form;
    DAO_CetakResep model;
    List<CetakResep> list; //deklara
    
    
      public Controller_CetakResep(TResep form) {
        this.form = form;
        model = new DAO_CetakResep();
        
      }
    
       public void tampilurutankode() {    
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglpesan().setText(String.valueOf(tgl.format(new Date())));
        
        CetakResep C = new CetakResep();
        model.autonumber(C);
        String no_mor=String.valueOf(model.autonumber(C));
        String oke="000".substring(0, 4 - no_mor.length());
        form.getTxtnoresep().setText( "RS"+  oke +""+no_mor );
        }
       
       
       public void reset() {      
        form.getTxtnoresep().setText("");
        form.getTxttglpesan().setText("");
        form.getTxttglambil().setText("");
        form.getCmbpelanggan().setSelectedIndex(0);
        form.getTxtkdplg().setText("");
        form.getTxtalmt().setText("");
        form.getTxtumur().setText("");
        form.getTxtnohp().setText("");
        form.getOdsph().setText("");
        form.getOdcyl().setText("");
        form.getOdaxis().setText("");
        form.getOssph().setText("");
        form.getOscyl().setText("");
        form.getOsaxis().setText("");   
        form.getAddresep().setText("");
        form.getPdresep().setText("");
        tampilurutankode(); 
        isicombopelanggan();
    }
        public void resetdetil() {      

        tampilurutankode(); 
        isicombopelanggan();
    }
       
       public void isicombopelanggan() {
        form.getCmbpelanggan().removeAllItems();
        form.getCmbpelanggan().addItem("-Pilih-");
        for (CetakResep c : model.isicombopelanggan()) {
            form.getCmbpelanggan().addItem(c.getNamaplg());
        }
    }
       
       public void tampilkodeplg() {
        if (form.getCmbpelanggan().getSelectedIndex() != 0) {
            for (CetakResep c : model.getkdplg(form.getCmbpelanggan().getSelectedItem().toString())) {
                form.getTxtkdplg().setText(String.valueOf(c.getKodeplg()));
                form.getTxtnohp().setText(String.valueOf(c.getTlp()));
                form.getTxtalmt().setText(String.valueOf(c.getAlamatplg()));
                form.getTxtumur().setText(String.valueOf(c.getUmur()));
            }
        }
    }
       
        public void insert() {
        //text harga dan stok tidak boleh kosong
      
         
        CetakResep C = new CetakResep();
        C.setNoresep(form.getTxtnoresep().getText());
        C.setTglpesan(form.getTxttglpesan().getText());
        C.setTglambil(form.getTxttglambil().getText());
        C.setOdsph(Float.parseFloat(form.getOdsph().getText()));
        C.setOdcyl(Float.parseFloat(form.getOdcyl().getText()));
        C.setOdaxis(Integer.parseInt(form.getOdaxis().getText()));
        C.setOssph(Float.parseFloat(form.getOssph().getText()));
        C.setOscyl(Float.parseFloat(form.getOscyl().getText()));
        C.setOsaxis(Integer.parseInt(form.getOsaxis().getText()));
        C.setAddresep(Double.parseDouble(form.getAddresep().getText()));
        C.setPdresep(Integer.parseInt(form.getPdresep().getText()));
        C.setKodeplg(form.getTxtkdplg().getText());
        model.insert(C);
    }
        public void cetak(){
        try{
            Connection conn = Koneksi.Database.KoneksiDB();
            String path = "src/Report/RepResep.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no_resep", (form.getTxtnoresep().getText()));
            
            JasperReport jp=(JasperReport)JRLoader.loadObject(path);
            JasperPrint print = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(print,false);
            
            OutputStream output = new FileOutputStream(new File("D:/Resep"+(form.getTxtnoresep().getText())));
            JasperExportManager.exportReportToPdfStream(print,output);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data Tidak Dapat Dicetak!!"+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}

