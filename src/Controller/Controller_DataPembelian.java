/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_DataPembelian;
import Model.DataPembelian;
import View.TBDataPembelian;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sandy
 */
public class Controller_DataPembelian {
    TBDataPembelian form;
    DAO_DataPembelian model;
    List<DataPembelian> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header = new String[]{"NO_BP","KODE", "NAMA", "HARGA", "STOK","TOTAL"};;
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
       
    @Override
    //Supaya grid JTable tidak dapat diedit
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
    public Controller_DataPembelian(TBDataPembelian form) {
        this.form = form;
        model = new DAO_DataPembelian();
        list = model.getAll();
        form.getTblframe().setModel(tblModel);
        form.getTblframe().setShowGrid(true);
        form.getTblframe().setShowVerticalLines(true);
        form.getTblframe().setGridColor(Color.blue);
    }
    
    //waktu bp dan auto number
    public void tampilurutankode() {    
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd");
        form.getTxttglbp().setText(String.valueOf(tgl.format(new Date())));
        
        DataPembelian D = new DataPembelian();
        model.autonumber(D);
        String no_mor=String.valueOf(model.autonumber(D));
        String oke="000".substring(0, 4 - no_mor.length());
        form.getTxtnobp().setText( "BP"+  oke +""+no_mor );
        }
    
    public void reset() {      
        form.getTxtnobp().setText("");
        form.getTxttglbp().setText("");
        form.getCmbsupp().setSelectedIndex(0);
        form.getTxtkdsupp().setText("");
        form.getTxtalamat().setText("");
        form.getTxttlp().setText("");
        form.getCmbframe().setSelectedIndex(0);
        form.getTxtkdframe().setText("");
        form.getTxthrgframe().setText("");
        form.getTxtqtyframe().setText("");
        form.getCmblensa().setSelectedIndex(0);
        form.getTxtkdlensa().setText("");
        form.getTxthrglensa().setText("");
        form.getTxtqtylensa().setText("");        
        tampilurutankode();
        isicombosupp();   
        isicomboframe();
        isicombolensa();
        tblModel.setRowCount(0);
        form.getTxtkdsupp().requestFocus();
        form.getTxtnobp().setEditable(false);
        form.getTxttglbp().setEditable(false);
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
    
    //method ini akan dipakai untuk mengisi data kedalam combo kode Supplier
    public void isicombosupp() {
        form.getCmbsupp().removeAllItems();
        form.getCmbsupp().addItem("-Pilih-");
        for (DataPembelian b : model.isicombosupp()) {
            form.getCmbsupp().addItem(b.getNmsupp());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode pelanggan dan lain-lain berdasarkan inputan combo nama pelanggan
    public void tampilkodesupp() {
        if (form.getCmbsupp().getSelectedIndex() != 0) {
            for (DataPembelian b : model.getkdplg(form.getCmbsupp().getSelectedItem().toString())) {
                form.getTxtkdsupp().setText(String.valueOf(b.getKodesupp()));
                form.getTxttlp().setText(String.valueOf(b.getTelpsupp()));
                form.getTxtalamat().setText(String.valueOf(b.getAlamatsupp()));
            }
        }
    }  
    //......................................untuk frame.........................//
    //method ini akan dipakai untuk mengisi data kedalam combo kode frame
    public void isicomboframe() {
        form.getCmbframe().removeAllItems();
        form.getCmbframe().addItem("-Pilih-");
        for (DataPembelian b : model.isicomboframe()) {
            form.getCmbframe().addItem(b.getNmframe());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode frame dan lain-lain berdasarkan inputan combo nama frame
    public void tampilkodeframe() {
        if (form.getCmbframe().getSelectedIndex() != 0) {
            for (DataPembelian b : model.getkdframe(form.getCmbframe().getSelectedItem().toString())) {
                form.getTxtkdframe().setText(String.valueOf(b.getKodeframe()));
            }
        }
    }
    
    //memanggil method simpan_isipmebelianframe pada DAO_Datapembelian      
    //method ini digunakan untuk simpan data pada tabel isipembelianframe dan
    //mengubah stok pada tabel barang
    public void simpan_isipembelianframe() {
        //text harga dan stok tidak boleh kosong
        if(form.getTxthrgframe().getText().equals("")){
            form.getTxthrgframe().setText("0");
        }
        if(form.getTxtqtyframe().getText().equals("")){
            form.getTxtqtyframe().setText("0");
        }
         
        DataPembelian B = new DataPembelian();
        B.setNobp(form.getTxtnobp().getText());
        B.setKodeframe(form.getTxtkdframe().getText());
        B.setHargaframe(Integer.parseInt(form.getTxthrgframe().getText()));
        B.setQtyframe(Integer.parseInt(form.getTxtqtyframe().getText()));
        model.insert_isipembelianframe(B);
        model.update_stokframe(B);
        model.update_hargaframe(B);
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
                    form.getTxtnobp().getText(),
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
                        
                        tblModel.setValueAt(form.getTxtnobp().getText(),i,0);
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
                        form.getTxtnobp().getText(),
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
        for (DataPembelian b : model.isicombolensa()) {
            form.getCmblensa().addItem(b.getNmlensa());
        }
    }
    
    //method ini akan digunakan untuk menampilkan kode frame dan lain-lain berdasarkan inputan combo nama frame
    public void tampilkodelensa() {
        if (form.getCmblensa().getSelectedIndex() != 0) {
            for (DataPembelian b : model.getkdlensa(form.getCmblensa().getSelectedItem().toString())) {
                form.getTxtkdlensa().setText(String.valueOf(b.getKodelensa()));
            }
        }
    }
    
    //memanggil method simpan_isipmebelianlensa pada DAO_Datapembelian      
    //method ini digunakan untuk simpan data pada tabel isipembelianframe dan
    //mengubah stok pada tabel barang
    public void simpan_isipembelianlensa() {
        //text harga dan stok tidak boleh kosong
        if(form.getTxthrglensa().getText().equals("")){
            form.getTxthrglensa().setText("0");
        }
        if(form.getTxtqtylensa().getText().equals("")){
            form.getTxtqtylensa().setText("0");
        }
         
        DataPembelian B = new DataPembelian();
        B.setNobp(form.getTxtnobp().getText());
        B.setKodelensa(form.getTxtkdlensa().getText());
        B.setHargalensa(Integer.parseInt(form.getTxthrglensa().getText()));
        B.setQtylensa(Integer.parseInt(form.getTxtqtylensa().getText()));
        model.insert_isipembelianlensa(B);
        model.update_stoklensa(B);
        model.update_hargalensa(B);
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
                    form.getTxtnobp().getText(),
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
                        
                        tblModel.setValueAt(form.getTxtnobp().getText(),i,0);
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
                        form.getTxtnobp().getText(),
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
    public void simpan_pembelian() {
        DataPembelian B = new DataPembelian();                         
        B.setNobp(form.getTxtnobp().getText());              
        B.setTglbp(form.getTxttglbp().getText());        
        B.setKodesupp(form.getTxtkdsupp().getText());  
        model.insert(B);
    }
}
