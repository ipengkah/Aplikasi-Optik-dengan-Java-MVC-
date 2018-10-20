/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.DAO_Supplier;
import Model.Supplier;
import View.MSupplier;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ipeng
 */
public class Controller_Supplier {
     MSupplier form;
    DAO_Supplier model;
    List<Supplier> list; //deklarasi method "List" yang sudah dibuat pada interface MODEL_DAO
    String[] header; //deklarasi variable untuk membuat judul kolom pada objek JTable
    
    public Controller_Supplier(MSupplier form) {
        this.form = form;
        model = new DAO_Supplier();
        list = model.getAll();
        header = new String[]{"KODE Supplier", "NAMA Supplier", "Alamat", "Telp"};
        
        form.getTblsupp().setShowGrid(true);
        form.getTblsupp().setShowVerticalLines(true);
        form.getTblsupp().setGridColor(Color.blue);
    }
    
    public void tampilurutankode() {         
            Supplier s = new Supplier();
            model.autonumber(s);
            String no_mor=String.valueOf(model.autonumber(s));
            String oke="000".substring(0, 3 - no_mor.length());
            form.getTxtkdsupp().setText( "SP"+  oke +""+no_mor );
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
        for (Supplier S : list) {
            data[0] = S.getKode();
            data[1] = S.getNama();
            data[2] = S.getAlamat();
            data[3] = S.getTlp();
            tblModel.addRow(data);
        }
        form.getTblsupp().setModel(tblModel);
        
     }
     
      public void reset() {      
        form.getTxtkdsupp().setText("");
        form.getTxtnmsupp().setText("");
        form.getTxtalamatsupp().setText("");
        form.getTxtnohpsupp().setText("");     
        form.getTxtnmsupp().requestFocus();   
        tampilurutankode();   
        isiTable();
        
    }
     public void isiField(int row) {
        form.getTxtkdsupp().setText(String.valueOf(list.get(row).getKode()));
        form.getTxtnmsupp().setText(list.get(row).getNama());
        form.getTxtalamatsupp().setText(list.get(row).getAlamat());
        form.getTxtnohpsupp().setText(list.get(row).getTlp());
    }
      public void insert() {
         Supplier S = new Supplier();
        S.setKode(form.getTxtkdsupp().getText());
        S.setNama(form.getTxtnmsupp().getText());
        S.setAlamat(form.getTxtalamatsupp().getText());
        S.setTlp(form.getTxtnohpsupp().getText());
        model.insert(S);
      }
      public void update() {
        Supplier S = new Supplier();
        S.setKode(form.getTxtkdsupp().getText());
        S.setNama(form.getTxtnmsupp().getText());
        S.setAlamat(form.getTxtalamatsupp().getText());
        S.setTlp(form.getTxtnohpsupp().getText());
        model.update(S);
      }
       public void delete() {
        if (!form.getTxtkdsupp().getText().trim().isEmpty()) {
            String id = (form.getTxtkdsupp().getText());
            model.delete(id);
        } else {
            JOptionPane.showMessageDialog(form, "Pilih data yang akan dihapus");
        }
    }
     public void isiTableCari() {
        list = model.getCari(form.getTxtkatakunci().getText().trim());
        DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{}, header);
        Object[] data = new Object[header.length];
        for (Supplier S : list) {
            data[0] = S.getKode();
            data[1] = S.getNama();
            data[2] = S.getAlamat();
            data[3] = S.getTlp();
            tblModel.addRow(data);  
            form.getTxtnmsupp().setText(S.getNama());   //menampilkan ke text
            form.getTxtalamatsupp().setText(S.getAlamat());   //menampilkan ke text
            form.getTxtnohpsupp().setText(S.getTlp());   //menampilkan ke text
    }
         form.getTblsupp().setModel(tblModel);
        
     }
     
}
