/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.DAO_Frame;
import Model.Frame;
import View.MFrame;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ipeng
 */
public class Controller_Frame {
    MFrame form;
    DAO_Frame model;
    List<Frame> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public Controller_Frame(MFrame form) {
        this.form = form;
        model = new DAO_Frame();
        list = model.getAll();
        header = new String[]{"Kode Frame", "Nama Frame", "Harga Beli Satuan", "Harga Jual","Stok"};
        
        form.getTblframe().setShowGrid(true);
        form.getTblframe().setShowVerticalLines(true);
        form.getTblframe().setGridColor(Color.blue);
    }
    
      public void tampilurutankode() {         
            Frame f = new Frame();
            model.autonumber(f);
            String no_mor=String.valueOf(model.autonumber(f));
            String oke="000".substring(0, 3 - no_mor.length());
            form.getTxtkdframe().setText( "FM"+  oke +""+no_mor );
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
        for (Frame F : list) {
            data[0] = F.getKode();
            data[1] = F.getNama();
            data[2] = F.getHargabeli();
            data[3] = F.getHargajual();
            data[4] = F.getStok();
            tblModel.addRow(data);
        }
        form.getTblframe().setModel(tblModel);
    }
    
    public void reset() {      
        form.getTxtkdframe().setText("");
        form.getTxtnmframe().setText("");
        form.getTxthrgsatuan().setText("");
        form.getTxthrgjual().setText("");
        form.getTxtstokframe().setText("");     
        form.getTxtnmframe().requestFocus();   
        tampilurutankode();   
        isiTable();
        
    }
     public void isiField(int row) {
        form.getTxtkdframe().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmframe().setText(list.get(row).getNama());
        form.getTxthrgsatuan().setText(String.valueOf(list.get(row).getHargabeli()));
        form.getTxthrgjual().setText(String.valueOf(list.get(row).getHargajual()));
        form.getTxtstokframe().setText(String.valueOf(list.get(row).getStok()));
        
    }
      public void insert() {
         Frame F = new Frame();
        F.setKode(form.getTxtkdframe().getText());
        F.setNama(form.getTxtnmframe().getText());
        F.setHargabeli(Integer.parseInt(form.getTxthrgsatuan().getText()));
        F.setHargajual(Integer.parseInt(form.getTxthrgjual().getText()));
        F.setStok(Integer.parseInt(form.getTxtstokframe().getText()));
        model.insert(F);
      }
      
      public void update() {
        Frame F = new Frame();
        F.setKode(form.getTxtkdframe().getText());
        F.setNama(form.getTxtnmframe().getText());
        F.setHargabeli(Integer.parseInt(form.getTxthrgsatuan().getText()));
        F.setHargajual(Integer.parseInt(form.getTxthrgjual().getText()));
        F.setStok(Integer.parseInt(form.getTxtstokframe().getText()));
        model.update(F);
      }
       public void delete() {
        if (!form.getTxtkdframe().getText().trim().isEmpty()) {
            String id = (form.getTxtkdframe().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
     public void isiTableCari() {
        list = model.getCari(form.getTxtkatakunci().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
        Object[] data = new Object[header.length];
        for (Frame F : list) {
            data[0] = F.getKode();
            data[1] = F.getNama();
            data[2] = F.getHargabeli();
            data[3] = F.getHargajual();
            data[4] = F.getStok();
            tblModel.addRow(data);  
            /*form.getTxtnmframe().setText(F.getNama());   //menampilkan ke text
            form.getTxthrgsatuan().setText(F.getHargabeli());  //menampilkan ke text
            form.getTxthrgjual().setText(F.getHargajual());   //menampilkan ke text
            form.getTxtstokframe().setText(F.getStok());   //menampilkan ke text*/
    }
         form.getTblframe().setModel(tblModel);
        
     }
}
