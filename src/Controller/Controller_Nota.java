/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Nota;
import Model.Nota;
import View.TNota;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class Controller_Nota {
    TNota form;
    DAO_Nota model;
    List<Nota> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header= new String[]{"NO_NOTA","KODE", "NAMA", "HARGA", "JML PESAN","TOTAL"};;
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
    @Override
    //Supaya grid JTable tidak dapat diedit
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
	
    public Controller_Nota(TNota form) {
        this.form = form;
        model = new DAO_Nota();
        list = model.getAll();
        form.getTblnota().setModel(tblModel);
        form.getTblnota().setShowGrid(true);
        form.getTblnota().setShowVerticalLines(true);
        form.getTblnota().setGridColor(Color.blue);
	}
    
    //waktu bp dan auto number
    public void tampilurutankode() {    
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttgnota().setText(String.valueOf(tgl.format(new Date())));
        
        Nota D = new Nota();
        model.autonumber(D);
        String no_mor=String.valueOf(model.autonumber(D));
        String oke="000".substring(0, 4 - no_mor.length());
        form.getTxtnonota().setText( "NO"+  oke +""+no_mor );
        }
    public void reset() {
        form.getTxttotal().setText("");
        form.getCmbresep().setSelectedIndex(0);
        form.getCmbframe().setSelectedIndex(0);
        form.getCmblensa().setSelectedIndex(0);
        form.getTxttglpesan().setText("");
        form.getTxttglambil().setText("");
        form.getTxtkdplg().setText("");
        form.getTxtnmplg().setText("");
        form.getTxtkdframe().setText("");
        form.getTxthrgframe().setText("");
        form.getTxtqtyframe().setText("");
        form.getTxtkdlensa().setText("");
        form.getTxthrglensa().setText("");
        form.getTxtqtylensa().setText("");
        form.getTxtdp().setText("");
        form.getTxtsisa().setText("");
        
        
        tampilurutankode();
        isicomboresep();
        isicomboframe();
        isicombolensa();
        tblModel.setRowCount(0);
        form.getTxttglpesan().requestFocus();
        form.getTxtnonota().setEditable(false);
        form.getTxttgnota().setEditable(false);
        }
    public void resetframe(){
        form.getTxtkdframe().setText("");
        form.getCmbframe().setSelectedIndex(0);
        form.getTxthrgframe().setText("");
        form.getTxtqtyframe().setText("");
    }
        public void resetlensa(){
        form.getTxtkdlensa().setText("");
        form.getCmblensa().setSelectedIndex(0);
        form.getTxthrglensa().setText("");
        form.getTxtqtylensa().setText("");
    }
     //method untuk menghitung keseluruhan total belanja
    public void hitung_grandtotal(){
        int jmlbaris = tblModel.getRowCount();
        int total=0;
        int Amount = 0;   
        DecimalFormat konversi=new DecimalFormat("###,###,###.00");
        for(int i = 0; i < tblModel.getRowCount(); i++){       
            Amount = Integer.parseInt(tblModel.getValueAt(i,5).toString());        
            total = Amount+total;     
        }

        //jmlbaris!= 0 >> jika jtabel berisi data
        if (jmlbaris!= 0){
            form.getTxttotal().setText(String.valueOf(total));
        }else{
            form.getTxttotal().setText("Rp. 0");
        }
    }
    
        public void hitung_sisa(){
        int total=0;
        int Amount = 0;   
        DecimalFormat konversi=new DecimalFormat("###,###,###.00");
        Amount = (Integer.parseInt(form.getTxttotal().getText())-Integer.parseInt(form.getTxtdp().getText()));   
        form.getTxtsisa().setText((String.valueOf(Amount)));
        
    }

		
    //method ini akan dipakai untuk mengisi data kedalam combo kode Supplier
    public void isicomboresep() {
        form.getCmbresep().removeAllItems();
        form.getCmbresep().addItem("-Pilih-");
        for (Nota b : model.isicomboresep()) {
            form.getCmbresep().addItem(b.getNoresep());
        }
    }
    //method ini akan digunakan untuk menampilkan kode pelanggan dan lain-lain berdasarkan inputan combo nama pelanggan
    public void tampilnoresep() {
        if (form.getCmbresep().getSelectedIndex() != 0) {
            for (Nota b : model.getTglpesan(form.getCmbresep().getSelectedItem().toString())) {
                form.getTxttglpesan().setText(String.valueOf(b.getTglpesan()));
                form.getTxttglambil().setText(String.valueOf(b.getTglambil()));
                form.getTxtkdplg().setText(String.valueOf(b.getKodeplg()));
                form.getTxtnmplg().setText(String.valueOf(b.getNmplg()));
            }
        }
    }
    
    //......................................untuk frame.........................//
    //method ini akan dipakai untuk mengisi data kedalam combo kode frame
    public void isicomboframe() {
        form.getCmbframe().removeAllItems();
        form.getCmbframe().addItem("-Pilih-");
        for (Nota b : model.isicomboframe()) {
            form.getCmbframe().addItem(b.getNmframe());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode frame dan lain-lain berdasarkan inputan combo nama frame
    public void tampilkodeframe() {
        if (form.getCmbframe().getSelectedIndex() != 0) {
            for (Nota b : model.getkdframe(form.getCmbframe().getSelectedItem().toString())) {
                form.getTxtkdframe().setText(String.valueOf(b.getKodeframe()));
                form.getTxthrgframe().setText(String.valueOf(b.getHrgframe()));
            }
        }
    }
    
    //memanggil method simpan_isipmebelianframe pada DAO_Datapembelian      
    //method ini digunakan untuk simpan data pada tabel isipembelianframe dan
    //mengubah stok pada tabel barang
    public void simpan_isipenjualanframe() {
        //text harga dan stok tidak boleh kosong
        if(form.getTxthrgframe().getText().equals("")){
            form.getTxthrgframe().setText("0");
        }
        if(form.getTxtqtyframe().getText().equals("")){
            form.getTxtqtyframe().setText("0");
        }
         
        Nota B = new Nota();
        B.setNonota(form.getTxtnonota().getText());
        B.setKodeframe(form.getTxtkdframe().getText());
        B.setHrgframe(Integer.parseInt(form.getTxthrgframe().getText()));
        B.setQtyframe(Integer.parseInt(form.getTxtqtyframe().getText()));
        model.insert_isipenjualanframe(B);
        model.update_stokframe(B);
    }
    //method untuk menambahkan data kedalam JTabel 
    public void isiTableframe() {
        int jmlbaris = tblModel.getRowCount();
        int i,datasama = 0;
        int stok=1;
        
        //cek inputan qty/jumlah beli        
        if(form.getTxtqtyframe().getText().isEmpty()==true){
            
            JOptionPane.showMessageDialog(null, "Quantity/Jumlah tidak boleh kosong atau melebihi stok yang tersedia! ");               
            stok=0;
        }
        
        if (!form.getTxtkdframe().getText().isEmpty() && stok>0) {
            if (jmlbaris == 0) {
                //jika posisi jtable belum ada isi sama sekali                
                    tblModel.addRow(new Object[]{
                    form.getTxtnonota().getText(),
                    form.getTxtkdframe().getText(),
                    form.getCmbframe().getSelectedItem().toString(),
                    form.getTxthrgframe().getText(),
                    form.getTxtqtyframe().getText(),
                    (Integer.parseInt(form.getTxthrgframe().getText())*Integer.parseInt(form.getTxtqtyframe().getText()))                    
                });
            } else {
                //jika sudah ada didalam Jtable, maka cek validasi
                //tidak boleh add kode barang yang sama 
                for (i = 0; i < jmlbaris; i++) {
                    if (form.getTxtkdframe().getText().equals(tblModel.getValueAt(i, 1).toString())) {
                        datasama = 1;
                        JOptionPane.showMessageDialog(null, "Kode  " + tblModel.getValueAt(i, 1).toString() + "  sudah pernah ada, dan akan diupdate dengan data terbaru.");                           
                        
                        tblModel.setValueAt(form.getTxtnonota().getText(),i,0);
                        tblModel.setValueAt(form.getTxtkdframe().getText(), i, 1);
                        tblModel.setValueAt(form.getCmbframe().getSelectedItem().toString(), i, 2);
                        tblModel.setValueAt(form.getTxthrgframe().getText(), i, 3);
                        tblModel.setValueAt(form.getTxtqtyframe().getText(), i, 4);
                        tblModel.setValueAt((Integer.parseInt(form.getTxthrgframe().getText())*Integer.parseInt(form.getTxtqtyframe().getText())), i, 5);
                        break;
                    } else {
                        datasama = 0;
                    }
                }

                //jika kode barang belum pernah ada didalam list, maka add data ke dalam jtable
                if (datasama == 0) {
                    tblModel.addRow(new Object[]{
                        form.getTxtnonota().getText(),
                        form.getTxtkdframe().getText(),
                        form.getCmbframe().getSelectedItem().toString(),
                        form.getTxthrgframe().getText(),
                        form.getTxtqtyframe().getText(),
                        (Integer.parseInt(form.getTxthrgframe().getText())*Integer.parseInt(form.getTxtqtyframe().getText()))                    
                });                    
                }                 
            }
        }
    }
        //......................................untuk lensa.........................//
    //method ini akan dipakai untuk mengisi data kedalam combo kode frame
    public void isicombolensa() {
        form.getCmblensa().removeAllItems();
        form.getCmblensa().addItem("-Pilih-");
        for (Nota b : model.isicombolensa()) {
            form.getCmblensa().addItem(b.getNmlensa());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode frame dan lain-lain berdasarkan inputan combo nama frame
    public void tampilkodelensa() {
        if (form.getCmblensa().getSelectedIndex() != 0) {
            for (Nota b : model.getkdlensa(form.getCmblensa().getSelectedItem().toString())) {
                form.getTxtkdlensa().setText(String.valueOf(b.getKodelensa()));
                form.getTxthrglensa().setText(String.valueOf(b.getHrglensa()));
            }
        }
    }
    
    //memanggil method simpan_isipmebelianlensa pada DAO_Datapembelian      
    //method ini digunakan untuk simpan data pada tabel isipembelianframe dan
    //mengubah stok pada tabel barang
    public void simpan_isipenjualanlensa() {
        //text harga dan stok tidak boleh kosong
        if(form.getTxthrglensa().getText().equals("")){
            form.getTxthrglensa().setText("0");
        }
        if(form.getTxtqtylensa().getText().equals("")){
            form.getTxtqtylensa().setText("0");
        }
         
        Nota B = new Nota();
        B.setNonota(form.getTxtnonota().getText());
        B.setKodelensa(form.getTxtkdlensa().getText());
        B.setHrglensa(Integer.parseInt(form.getTxthrglensa().getText()));
        B.setQtylensa(Integer.parseInt(form.getTxtqtylensa().getText()));
        model.insert_isipenjualanlensa(B);
        model.update_stoklensa(B);
    }
    //method untuk menambahkan data kedalam JTabel 
    public void isiTablelensa() {
        int jmlbaris = tblModel.getRowCount();
        int i,datasama = 0;
        int stok=1;
        
        //cek inputan qty/jumlah beli        
        if(form.getTxtqtylensa().getText().isEmpty()==true){
            
            JOptionPane.showMessageDialog(null, "Quantity/Jumlah tidak boleh kosong atau melebihi stok yang tersedia! ");               
            stok=0;
        }
        
        if (!form.getTxtkdlensa().getText().isEmpty() && stok>0) {
            if (jmlbaris == 0) {
                //jika posisi jtable belum ada isi sama sekali                
                    tblModel.addRow(new Object[]{
                    form.getTxtnonota().getText(),
                    form.getTxtkdlensa().getText(),
                    form.getCmblensa().getSelectedItem().toString(),
                    form.getTxthrglensa().getText(),
                    form.getTxtqtylensa().getText(),
                    (Integer.parseInt(form.getTxthrglensa().getText())*Integer.parseInt(form.getTxtqtylensa().getText()))                    
                });
            } else {
                //jika sudah ada didalam Jtable, maka cek validasi
                //tidak boleh add kode barang yang sama 
                for (i = 0; i < jmlbaris; i++) {
                    if (form.getTxtkdlensa().getText().equals(tblModel.getValueAt(i, 1).toString())) {
                        datasama = 1;
                        JOptionPane.showMessageDialog(null, "Kode  " + tblModel.getValueAt(i, 1).toString() + "  sudah pernah ada, dan akan diupdate dengan data terbaru.");                           
                        
                        tblModel.setValueAt(form.getTxtnonota().getText(),i,0);
                        tblModel.setValueAt(form.getTxtkdlensa().getText(), i, 1);
                        tblModel.setValueAt(form.getCmblensa().getSelectedItem().toString(), i, 2);
                        tblModel.setValueAt(form.getTxthrglensa().getText(), i, 3);
                        tblModel.setValueAt(form.getTxtqtylensa().getText(), i, 4);
                        tblModel.setValueAt((Integer.parseInt(form.getTxthrglensa().getText())*Integer.parseInt(form.getTxtqtylensa().getText())), i, 5);
                        break;
                    } else {
                        datasama = 0;
                    }
                }

                //jika kode barang belum pernah ada didalam list, maka add data ke dalam jtable
                if (datasama == 0) {
                    tblModel.addRow(new Object[]{
                        form.getTxtnonota().getText(),
                        form.getTxtkdlensa().getText(),
                        form.getCmblensa().getSelectedItem().toString(),
                        form.getTxthrglensa().getText(),
                        form.getTxtqtylensa().getText(),
                        (Integer.parseInt(form.getTxthrglensa().getText())*Integer.parseInt(form.getTxtqtylensa().getText()))                    
                });                    
                }                 
            }
        }
    }
    //memanggil method simpan_transaksi pada DAO_BuktiPesan      
    //method ini digunakan untuk simpan data pada tabel buktipesan
    public void simpan_transaksi() {
        Nota B = new Nota();                         
        B.setNonota(form.getTxtnonota().getText());              
        B.setTglnota(form.getTxttgnota().getText());        
        B.setTotal(Integer.parseInt(form.getTxttotal().getText()));
        B.setDp(Integer.parseInt(form.getTxtdp().getText()));
        B.setSisa(Integer.parseInt(form.getTxtsisa().getText()));
        B.setNoresep(form.getCmbresep().getSelectedItem().toString());
        model.insert(B);
    }
    public void cetak(){
        try{
            Connection conn = Koneksi.Database.KoneksiDB();
            String path = "src/Report/RepNota.jasper";
            HashMap parameter = new HashMap();
            parameter.put("no_nota", (form.getTxtnonota().getText()));
            
            JasperReport jp=(JasperReport)JRLoader.loadObject(path);
            JasperPrint print = JasperFillManager.fillReport(jp, parameter, conn);
            JasperViewer.viewReport(print,false);
            
            OutputStream output = new FileOutputStream(new File("D:/Nota"+(form.getTxtnonota().getText())));
            JasperExportManager.exportReportToPdfStream(print,output);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Data Tidak Dapat Dicetak!!"+ex.getMessage(),
            "Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
}
