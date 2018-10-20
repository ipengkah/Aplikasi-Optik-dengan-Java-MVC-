/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Koneksi.Database;
import Model.CetakResep;
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
public class DAO_CetakResep implements Model_DAO<CetakResep>{
    public DAO_CetakResep(){
        connection = Database.KoneksiDB();
    }
    
     Connection connection;
     String TAMPILPELANGGAN = "SELECT * FROM pelanggan order by nm_pelanggan";
     String CARIPELANGGAN = "SELECT * FROM pelanggan where nm_pelanggan=?"; 
     String CARI = "SELECT * FROM resep where no_resep like ?";
     String INSERTRESEP = "INSERT into resep(no_resep,tgl_pesan,tgl_ambil,od_sph,od_cyl,od_axis,os_sph,os_cyl,os_axis,add_resep,pd_resep,kd_pelanggan) values(?,?,?,?,?,?,?,?,?,?,?,?)";
     //String INSERTDETILRESEP="INSERT into detilperiksa(no_resep,kd_pelanggan,od_sph,od_cyl,od_axis,os_sph,os_cyl,os_axis) values(?,?,?,?,?,?,?,?)";
     String COUNTER = "SELECT ifnull(max(convert(right(no_resep,3),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_resep,3),signed integer))),0)as panjang "
            + "from resep ";
    
    //method untuk menampilkan nama pelanggan berdasarkan inputan text kode pelanggan     

    
    @Override
    public int autonumber(CetakResep object) {
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
    
    public List<CetakResep> isicombopelanggan() {
        PreparedStatement statement;
        List<CetakResep> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILPELANGGAN);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                CetakResep c = new CetakResep();  
                c.setNamaplg(rs.getString("nm_pelanggan"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     public List<CetakResep> getkdplg(String namaplg) {
        PreparedStatement statement;
        List<CetakResep> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIPELANGGAN);
            statement.setString(1, namaplg);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                CetakResep c = new CetakResep();
                c.setKodeplg(rs.getString("kd_pelanggan"));
                c.setTlp(rs.getString("telp"));
                c.setAlamatplg(rs.getString("alamat"));
                c.setUmur(rs.getString("umur"));
                list.add(c);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     
    
         
    @Override
    public void insert(CetakResep object) {
        PreparedStatement statement = null;
        try {
            String CARI = "SELECT * from resep where no_resep like ?";
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getNoresep());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERTRESEP);
                statement2.setString(1, object.getNoresep());
                statement2.setString(2, object.getTglpesan());
                statement2.setString(3, object.getTglambil());
                statement2.setFloat(4, object.getOssph());
                statement2.setFloat(5, object.getOscyl());
                statement2.setInt(6, object.getOsaxis());
                statement2.setFloat(7, object.getOdsph());
                statement2.setFloat(8, object.getOdcyl());
                statement2.setInt(9, object.getOdaxis());
                statement2.setDouble(10, object.getAddresep());
                statement2.setInt(11, object.getPdresep());
                statement2.setString(12, object.getKodeplg());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Dicetak!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_CetakResep.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
        //method untuk simpan data ke dalam tabel detilpesan
     // public void insert_resep(CetakResep object) {
       // PreparedStatement statement = null;
        //int total = 0;
       // try {            
         //       PreparedStatement statement2 = null;
           //     statement2 = connection.prepareStatement(INSERTRESEP);
             //   statement2.setString(1, object.getNoresep());
              //  statement2.setString(2, object.getTglpesan());
              //  statement2.setString(3, object.getTglambil());
          //      statement2.setFloat(4, object.getOdsph());
            //    statement2.setFloat(5, object.getOdcyl());
         //       statement2.setInt(6, object.getOdaxis());
          //      statement2.setFloat(7, object.getOssph());
          //      statement2.setFloat(8, object.getOscyl());
          //      statement2.setInt(9, object.getOsaxis());
          //      statement2.setFloat(10, object.getAdd());
          //      statement2.setFloat(11, object.getPd());
          //      statement2.setString(12, object.getKodeplg());
           //     statement2.executeUpdate();
           //     JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
      //  } catch (Exception e) {
        //    e.printStackTrace();
      //  }
     // }

    @Override
    public void update(CetakResep object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CetakResep> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CetakResep> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

   
