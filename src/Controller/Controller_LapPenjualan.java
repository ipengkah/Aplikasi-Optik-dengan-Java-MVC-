/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LapPenjualan;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
public class Controller_LapPenjualan {
    LapPenjualan form;
    public Controller_LapPenjualan(LapPenjualan form){
        this.form = form;
    }
    public void awal(){
        SimpleDateFormat tgl1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tgl2 = new SimpleDateFormat("yyyy-MM-dd");
        form.getTgl1().setText(String.valueOf(tgl1.format(new Date())));
        form.getTgl2().setText(String.valueOf(tgl2.format(new Date())));   
    }
    public void cetak(){
        try{
            Connection conn = Koneksi.Database.KoneksiDB();
            String path = "src/Report/RepLapPenjulan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("tgl_awal",(form.getTgl1().getText()));
            parameter.put("tgl_akhir",(form.getTgl2().getText()));
            
            JasperReport jp=(JasperReport)JRLoader.loadObject(path);
            JasperPrint print = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(print,false);
            
            OutputStream output = new FileOutputStream(new File("D:/LapDataPenjualan"));
            JasperExportManager.exportReportToPdfStream(print,output);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data Tidak Dapat Dicetak!!"+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
