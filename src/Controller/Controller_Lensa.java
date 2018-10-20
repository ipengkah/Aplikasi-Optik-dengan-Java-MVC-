/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Lensa;
import Model.Lensa;
import View.MLensa;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ipeng
 */
public class Controller_Lensa {
    MLensa form;
    DAO_Lensa model;
    List<Lensa> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public Controller_Lensa(MLensa form) {
        this.form = form;
        model = new DAO_Lensa();
        list = model.getAll();
        header = new String[]{"Kode Lensa", "Nama Lensa", "Harga Beli Satuan", "Harga Jual","Stok"};
        
        form.getTbllensa().setShowGrid(true);
        form.getTbllensa().setShowVerticalLines(true);
        form.getTbllensa().setGridColor(Color.blue);
    }
    
    public void tampilurutankode() {         
            Lensa l = new Lensa();
            model.autonumber(l);
            String no_mor=String.valueOf(model.autonumber(l));
            String oke="000".substring(0, 3 - no_mor.length());
            form.getTxtkdlensa().setText( "LS"+  oke +""+no_mor );
        }
    
    public void isiTable() {
        list = model.getAll();
        //Script agar jtable tidak bisa di edit
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        Object[] data = new Object[header.length];
        for (Lensa L : list) {
            data[0] = L.getKode();
            data[1] = L.getNama();
            data[2] = L.getHargabeli();
            data[3] = L.getHargajual();
            data[4] = L.getStok();
            tblModel.addRow(data);
        }
        form.getTbllensa().setModel(tblModel);
    }
    
    public void reset() {      
        form.getTxtkdlensa().setText("");
        form.getTxtnmlensa().setText("");
        form.getTxthargabelisatuan().setText("");
        form.getTxthargajual().setText("");
        form.getTxtstok().setText("");     
        form.getTxtnmlensa().requestFocus();   
        tampilurutankode();   
        isiTable();
        
    }
     public void isiField(int row) {
        form.getTxtkdlensa().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmlensa().setText(list.get(row).getNama());
        form.getTxthargabelisatuan().setText(String.valueOf(list.get(row).getHargabeli()));
        form.getTxthargajual().setText(String.valueOf(list.get(row).getHargajual()));
        form.getTxtstok().setText(String.valueOf(list.get(row).getStok()));
        
    }
      public void insert() {
         Lensa L = new Lensa();
        L.setKode(form.getTxtkdlensa().getText());
        L.setNama(form.getTxtnmlensa().getText());
        L.setHargabeli(Integer.parseInt(form.getTxthargabelisatuan().getText()));
        L.setHargajual(Integer.parseInt(form.getTxthargajual().getText()));
        L.setStok(Integer.parseInt(form.getTxtstok().getText()));
        model.insert(L);
      }
      
       public void update() {
        Lensa L = new Lensa();
        L.setKode(form.getTxtkdlensa().getText());
        L.setNama(form.getTxtnmlensa().getText());
        L.setHargabeli(Integer.parseInt(form.getTxthargabelisatuan().getText()));
        L.setHargajual(Integer.parseInt(form.getTxthargajual().getText()));
        L.setStok(Integer.parseInt(form.getTxtstok().getText()));
        model.update(L);
      }
       public void delete() {
        if (!form.getTxtkdlensa().getText().trim().isEmpty()) {
            String id = (form.getTxtkdlensa().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
     public void isiTableCari() {
        list = model.getCari(form.getTxtkatakunci().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
        Object[] data = new Object[header.length];
        for (Lensa L : list) {
            data[0] = L.getKode();
            data[1] = L.getNama();
            data[2] = L.getHargabeli();
            data[3] = L.getHargajual();
            data[4] = L.getStok();
            tblModel.addRow(data);  
            /*form.getTxtnmframe().setText(F.getNama());   //menampilkan ke text
            form.getTxthrgsatuan().setText(F.getHargabeli());  //menampilkan ke text
            form.getTxthrgjual().setText(F.getHargajual());   //menampilkan ke text
            form.getTxtstokframe().setText(F.getStok());   //menampilkan ke text*/
    }
         form.getTbllensa().setModel(tblModel);
        
     }
}
