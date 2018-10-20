/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sandy
 */
public class DAO_Nota implements Model_DAO<Nota> {
    public DAO_Nota(){
        connection = Database.KoneksiDB();
        }
    Connection connection;
    String TAMPILLENSA = "SELECT * FROM lensa order by nm_lensa";
    String CARILENSA = "SELECT * FROM lensa where nm_lensa=?";
    String INSERTISIPENJUALANLENSA = "insert into isipenjualanlensa(no_nota,kd_lensa,hrg_lensa,qty) values(?,?,?,?)";
    String TAMPILFRAME = "SELECT * FROM frame order by nm_frame";
    String CARIFRAME = "SELECT * FROM frame where nm_frame=?";
    String INSERTISIPENJUALANFRAME = "insert into isipenjualanframe(no_nota,kd_frame,hrg_frame,qty) values(?,?,?,?)";
    String TAMPILRESEP = "SELECT * FROM resep order by no_resep";
    String CARIRESEP = "SELECT a.*, b.* FROM resep a,pelanggan b where a.kd_pelanggan=b.kd_pelanggan and no_resep=?"; 
    String COUNTER = "SELECT ifnull(max(convert(right(no_nota,3),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_resep,3),signed integer))),0)as panjang "
            + "from nota ";
    
    //nonota
    @Override
    public int autonumber(Nota object) {
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
     //comboresep    
        public List<Nota> isicomboresep() {
        PreparedStatement statement;
        List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILRESEP);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                Nota b = new Nota();  
                b.setNoresep(rs.getString("no_resep"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
    //manggil data resep lewat combo resep
    public List<Nota> getTglpesan(String noresep) {
        PreparedStatement statement;
        List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIRESEP);
            statement.setString(1, noresep);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                Nota b = new Nota();
                b.setTglpesan(rs.getString("tgl_pesan"));
                b.setTglambil(rs.getString("tgl_ambil"));
                b.setKodeplg(rs.getString("kd_pelanggan"));
                b.setNmplg(rs.getString("nm_pelanggan"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     //......................................untuk frame....................................//
        //combo frame
        public List<Nota> isicomboframe() {
        PreparedStatement statement;
        List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILFRAME);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                Nota b = new Nota();  
                b.setNmframe(rs.getString("nm_frame"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
            
    //manggil data frame lewat combo frame
    public List<Nota> getkdframe(String nmframe) {
    PreparedStatement statement;
    List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIFRAME);
            statement.setString(1, nmframe);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                Nota b = new Nota();
                b.setKodeframe(rs.getString("kd_frame"));
                b.setHrgframe(rs.getInt("hrg_jual_frame"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        //method untuk simpan data ke dalam tabel detilpesan
      public void insert_isipenjualanframe(Nota object) {
        PreparedStatement statement = null;
        int total = 0;
        try {            
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTISIPENJUALANFRAME);
                statement2.setString(1, object.getNonota());
                statement2.setString(2, object.getKodeframe());
                statement2.setInt(3, object.getHrgframe());
                statement2.setInt(4, object.getQtyframe());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di ditambahkan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
      
    //method untuk mengubah stok barang ke dalam tabel barang  
    public void update_stokframe(Nota object) {
       PreparedStatement statement;
       int stok_akhir=0;
        try {
                String SELECT = "select stok_frame from frame where kd_frame=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getKodeframe());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        stok_akhir=rs.getInt("stok_frame") - object.getQtyframe();

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
    
    //......................................untuk lensa....................................//
        //combo lensa
        public List<Nota> isicombolensa() {
        PreparedStatement statement;
        List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILLENSA);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                Nota b = new Nota();  
                b.setNmlensa(rs.getString("nm_lensa"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
            
    //manggil data frame lewat combo LENSA
    public List<Nota> getkdlensa(String nmlensa) {
    PreparedStatement statement;
    List<Nota> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARILENSA);
            statement.setString(1, nmlensa);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                Nota b = new Nota();
                b.setKodelensa(rs.getString("kd_lensa"));
                b.setHrglensa(rs.getInt("hrg_jual_lensa"));
                list.add(b);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        //method untuk simpan data ke dalam tabel detilpesan
      public void insert_isipenjualanlensa(Nota object) {
        PreparedStatement statement = null;
        int total = 0;
        try {            
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTISIPENJUALANLENSA);
                statement2.setString(1, object.getNonota());
                statement2.setString(2, object.getKodelensa());
                statement2.setInt(3, object.getHrglensa());
                statement2.setInt(4, object.getQtylensa());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
      
    //method untuk mengubah stok barang ke dalam tabel barang  
    public void update_stoklensa(Nota object) {
       PreparedStatement statement;
       int stok_akhir=0;
        try {
                String SELECT = "select stok_lensa from lensa where kd_lensa=?";   
                statement = connection.prepareStatement(SELECT);
                statement.setString(1, object.getKodelensa());
                ResultSet rs = statement.executeQuery();     
                
                if (rs.next()){ //jika data ditemukan (baik null maupun tidak null)
                        //hitung stok akhir berdasarkan qty masing2 kode barang yang ada di jtabel 
                        stok_akhir=rs.getInt("stok_lensa") - object.getQtylensa();

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

    @Override
    public void insert(Nota object) {
        PreparedStatement statement = null;
        try {
            String CARI = "SELECT * from nota where no_nota like ?";
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getNonota());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                String INSERT="INSERT INTO nota(no_nota,tgl_nota,total_harga,dp,sisa_pembayaran,no_resep) values(?,?,?,?,?,?)" ;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNonota());
                statement2.setString(2, object.getTglnota());
                statement2.setInt(3, object.getTotal());
                statement2.setInt(4, object.getDp());
                statement2.setInt(5, object.getSisa());
                statement2.setString(6, object.getNoresep());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Kwitansi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    @Override
    public void update(Nota object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Nota> getAll() {
        List<Nota> list = null;
        return null;
    }

    @Override
    public List<Nota> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
