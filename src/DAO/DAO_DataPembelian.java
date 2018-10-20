/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.DataPembelian;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sandy
 */
public class DAO_DataPembelian implements Model_DAO<DataPembelian>{
    public DAO_DataPembelian(){
            connection = Database.KoneksiDB();
        }
    Connection connection;
    String TAMPILFRAME = "SELECT * FROM frame order by nm_frame";
    String TAMPILLENSA = "SELECT * FROM lensa order by nm_lensa";
    String CARIFRAME = "SELECT * FROM frame where nm_frame=?";
    String CARILENSA = "SELECT * FROM lensa where nm_lensa=?";
    String CARIISIPEMBELIANFRAME = "SELECT * FROM isipembelianframe where no_bp=?";
    String INSERTISIPEMBELIANFRAME = "insert into isipembelianframe(no_bp,kd_frame,hrg_beli_satuan,qty) values(?,?,?,?)";
    String INSERTISIPEMBELIANLENSA = "insert into isipembelianlensa(no_bp,kd_lensa,hrg_beli_satuan,qty) values(?,?,?,?)";
    String TAMPILSUPPLIER = "SELECT * FROM supplier order by nm_supp";
    String CARISUPPLIER = "SELECT * FROM supplier where nm_supp=?"; 
    String COUNTER = "SELECT ifnull(max(convert(right(no_bp,3),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_bp,3),signed integer))),0)as panjang "
            + "from buktipembelian ";
    
    //autonumber no bp
    @Override
    public int autonumber(DataPembelian object) {
        PreparedStatement statement = null;
         int nomor=0;
        try{
            statement = connection.prepareStatement(COUNTER);
            ResultSet rs = statement.executeQuery();
            if(rs.next())             
                nomor=rs.getInt("kode")+1;          
            
        }catch (Exception e) {
            e.printStackTrace();
        }   
        return nomor;
    }
        //combosupplier    
        public List<DataPembelian> isicombosupp() {
        PreparedStatement statement;
        List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILSUPPLIER);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                DataPembelian b = new DataPembelian();  
                b.setNmsupp(rs.getString("nm_supp"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
    //manggil data supplier lewat combo supplier
    public List<DataPembelian> getkdplg(String nmsupp) {
        PreparedStatement statement;
        List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARISUPPLIER);
            statement.setString(1, nmsupp);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                DataPembelian b = new DataPembelian();
                b.setKodesupp(rs.getString("kd_supp"));
                b.setTelpsupp(rs.getString("tlpn_supp"));
                b.setAlamatsupp(rs.getString("alamat_supp"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }  
    
    //......................................untuk frame....................................//
        //combo frame
        public List<DataPembelian> isicomboframe() {
        PreparedStatement statement;
        List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILFRAME);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                DataPembelian b = new DataPembelian();  
                b.setNmframe(rs.getString("nm_frame"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
            
    //manggil data frame lewat combo frame
    public List<DataPembelian> getkdframe(String nmframe) {
    PreparedStatement statement;
    List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIFRAME);
            statement.setString(1, nmframe);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                DataPembelian b = new DataPembelian();
                b.setKodeframe(rs.getString("kd_frame"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        //method untuk simpan data ke dalam tabel detilpesan
      public void insert_isipembelianframe(DataPembelian object) {
        PreparedStatement statement = null;
        int total = 0;
        try {            
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTISIPEMBELIANFRAME);
                statement2.setString(1, object.getNobp());
                statement2.setString(2, object.getKodeframe());
                statement2.setInt(3, object.getHargaframe());
                statement2.setInt(4, object.getQtyframe());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
      
    //method untuk mengubah stok barang ke dalam tabel barang  
    public void update_stokframe(DataPembelian object) {
       PreparedStatement statement;
       int stok_akhir=0;
        try {
                String SELECT = "select stok_frame from frame where kd_frame=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getKodeframe());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        stok_akhir=rs.getInt("stok_frame")+ object.getQtyframe();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTOCK ="update frame set stok_frame=? where kd_frame=?";
                        statement2 = connection.prepareStatement(UPDATESTOCK);                
                        statement2.setInt(1, stok_akhir);
                        statement2.setString(2, object.getKodeframe());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
    public void update_hargaframe(DataPembelian object){
    PreparedStatement statement;
    int harga = 0;
    try {
        //update stok barang ke dalam table barang                     
        PreparedStatement statement2;
        harga = object.getHargaframe();
        String UPDATEHARGA ="update frame set hrg_beli_satuan=? where kd_frame=?";
                        statement2 = connection.prepareStatement(UPDATEHARGA);
                        statement2.setInt(1, harga);
                        statement2.setString(2, object.getKodeframe());     
                        statement2.executeUpdate();
                    
                
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
//......................................untuk lensa....................................//
        //combo lensa
        public List<DataPembelian> isicombolensa() {
        PreparedStatement statement;
        List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILLENSA);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                DataPembelian b = new DataPembelian();  
                b.setNmlensa(rs.getString("nm_lensa"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
            
    //manggil data frame lewat combo LENSA
    public List<DataPembelian> getkdlensa(String nmlensa) {
    PreparedStatement statement;
    List<DataPembelian> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARILENSA);
            statement.setString(1, nmlensa);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                DataPembelian b = new DataPembelian();
                b.setKodelensa(rs.getString("kd_lensa"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        //method untuk simpan data ke dalam tabel detilpesan
      public void insert_isipembelianlensa(DataPembelian object) {
        PreparedStatement statement = null;
        int total = 0;
        try {            
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTISIPEMBELIANLENSA);
                statement2.setString(1, object.getNobp());
                statement2.setString(2, object.getKodelensa());
                statement2.setInt(3, object.getHargalensa());
                statement2.setInt(4, object.getQtylensa());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
      
    //method untuk mengubah stok barang ke dalam tabel barang  
    public void update_stoklensa(DataPembelian object) {
       PreparedStatement statement;
       int stok_akhir=0;
        try {
                String SELECT = "select stok_lensa from lensa where kd_lensa=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getKodelensa());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        stok_akhir=rs.getInt("stok_lensa")+ object.getQtylensa();

                        //update stok barang ke dalam table barang                     
                        PreparedStatement statement2; 
                        String UPDATESTOCK ="update lensa set stok_lensa=? where kd_lensa=?";
                        statement2 = connection.prepareStatement(UPDATESTOCK);                
                        statement2.setInt(1, stok_akhir);
                        statement2.setString(2, object.getKodelensa());     
                        statement2.executeUpdate();
                    }
                        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
    public void update_hargalensa(DataPembelian object){
    PreparedStatement statement;
    int harga = 0;
    try {
        //update stok barang ke dalam table barang                     
        PreparedStatement statement2;
        harga = object.getHargalensa();
        String UPDATEHARGA ="update lensa set hrg_beli_satuan=? where kd_lensa=?";
                        statement2 = connection.prepareStatement(UPDATEHARGA);
                        statement2.setInt(1, harga);
                        statement2.setString(2, object.getKodelensa());     
                        statement2.executeUpdate();
                    
                
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }


    @Override
    public void insert(DataPembelian object) {
    PreparedStatement statement = null;
        try {
            String CARI = "SELECT * from buktipembelian where no_bp like ?";
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getNobp());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                String INSERT="INSERT INTO buktipembelian(no_bp,tgl_bp,kd_supp) values(?,?,?)" ;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNobp());
                statement2.setString(2, object.getTglbp());
                statement2.setString(3, object.getKodesupp());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_DataPembelian.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    @Override
    public void update(DataPembelian object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DataPembelian> getAll() {
        List<DataPembelian> list = null;
        return null;
    }
    

    @Override
    public List<DataPembelian> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
