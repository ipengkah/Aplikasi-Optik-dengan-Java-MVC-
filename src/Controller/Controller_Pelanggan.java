/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAO_Pelanggan;
import DAO.Model_DAO;
import Model.Pelanggan;
import View.MPelanggan;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ipeng
 */
public class Controller_Pelanggan {
     MPelanggan form;
    DAO_Pelanggan model;
    List<Pelanggan> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public Controller_Pelanggan(MPelanggan form) {
        this.form = form;
        model = new DAO_Pelanggan();
        list = model.getAll();
        header = new String[]{"KODE Pelanggan", "NAMA Pelanggan", "Alamat", "Umur", "Telp"};
        
        form.getTblplg().setShowGrid(true);
        form.getTblplg().setShowVerticalLines(true);
        form.getTblplg().setGridColor(Color.blue);
    }
     public void tampilurutankode() {         
            Pelanggan p = new Pelanggan();
            model.autonumber(p);
            String no_mor=String.valueOf(model.autonumber(p));
            String oke="000".substring(0, 3 - no_mor.length());
            form.getTxtkdplg().setText( "PG"+  oke +""+no_mor );
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
        for (Pelanggan B : list) {
            data[0] = B.getKode();
            data[1] = B.getNama();
            data[2] = B.getAlamat();
            data[3] = B.getUmur();
            data[4] = B.getTlp();
            tblModel.addRow(data);
        }
        form.getTblplg().setModel(tblModel);
    }
     public void reset() {      
        form.getTxtkdplg().setText("");
        form.getTxtnmplg().setText("");
        form.getTxtalamat().setText("");
        form.getTxtumur().setText("");
        form.getTxttelp().setText("");     
        form.getTxtnmplg().requestFocus();   
        tampilurutankode();   
        isiTable();
        
    }
     public void isiField(int row) {
        form.getTxtkdplg().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmplg().setText(list.get(row).getNama());
        form.getTxtalamat().setText(list.get(row).getAlamat());
        form.getTxtumur().setText(list.get(row).getUmur());
        form.getTxttelp().setText(list.get(row).getTlp());
    }
      public void insert() {
         Pelanggan B = new Pelanggan();
        B.setKode(form.getTxtkdplg().getText());
        B.setNama(form.getTxtnmplg().getText());
        B.setAlamat(form.getTxtnmplg().getText());
        B.setUmur(form.getTxtumur().getText());
        B.setTlp(form.getTxttelp().getText());
        model.insert(B);
      }
      public void update() {
        Pelanggan B = new Pelanggan();
        B.setKode(form.getTxtkdplg().getText());
        B.setNama(form.getTxtnmplg().getText());
        B.setAlamat(form.getTxtalamat().getText());
        B.setUmur(form.getTxtumur().getText());
        B.setTlp(form.getTxttelp().getText());
        model.update(B);
      }
       public void delete() {
        if (!form.getTxtkdplg().getText().trim().isEmpty()) {
            String id = (form.getTxtkdplg().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
     public void isiTableCari() {
        list = model.getCari(form.getTxtkk().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
        Object[] data = new Object[header.length];
        for (Pelanggan B : list) {
            data[0] = B.getKode();
            data[1] = B.getNama();
            data[2] = B.getAlamat();
            data[3] = B.getUmur();
            data[4] = B.getTlp();
            tblModel.addRow(data);  
            form.getTxtnmplg().setText(B.getNama());   //menampilkan ke text
            form.getTxtalamat().setText(B.getAlamat());   //menampilkan ke text
            form.getTxtumur().setText(B.getUmur());   //menampilkan ke text
            form.getTxttelp().setText(B.getTlp());   //menampilkan ke text
    }
         form.getTblplg().setModel(tblModel);
        
     }
     
}
        

