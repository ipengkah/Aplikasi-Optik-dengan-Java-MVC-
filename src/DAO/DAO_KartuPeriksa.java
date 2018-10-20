/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.KartuPeriksa;
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
 * @author Ipeng
 */
public class DAO_KartuPeriksa implements Model_DAO<KartuPeriksa>{
     public DAO_KartuPeriksa(){
        connection = Database.KoneksiDB();
    }
    
     Connection connection;
     String TAMPILRESEP = "SELECT * FROM resep order by no_resep";
     String CARIRESEP = "SELECT a.*, b.* FROM resep a,pelanggan b where a.kd_pelanggan=b.kd_pelanggan and no_resep=?";
     String COUNTER = "SELECT ifnull(max(convert(right(no_kartuperiksa,3),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_kartuperiksa,3),signed integer))),0)as panjang "
            + "from kartuperiksa ";

    @Override
    public int autonumber(KartuPeriksa object) {
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
        public List<KartuPeriksa> isicomboresep() {
        PreparedStatement statement;
        List<KartuPeriksa> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILRESEP);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                KartuPeriksa k = new KartuPeriksa();  
                k.setNoresep(rs.getString("no_resep"));
                list.add(k);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        
     //manggil data resep lewat combo resep
    public List<KartuPeriksa> getNamaplg(String noresep) {
        PreparedStatement statement;
        List<KartuPeriksa> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARIRESEP);
            statement.setString(1, noresep);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                KartuPeriksa k = new KartuPeriksa();
                k.setNamaplg(rs.getString("nm_pelanggan"));
                k.setAlamatplg(rs.getString("alamat"));
                k.setUmur(rs.getString("umur"));
                k.setTlp(rs.getString("telp"));
                list.add(k);            
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    @Override
    public void insert(KartuPeriksa object) {
        PreparedStatement statement = null;
        try {
            String CARI = "SELECT * from kartuperiksa where no_kartuperiksa like ?";
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getNokartuperiksa());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                String INSERT="INSERT INTO kartuperiksa(no_kartuperiksa,no_resep,tgl_periksa) values(?,?,?)" ;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNokartuperiksa());
                statement2.setString(2, object.getNoresep());
                statement2.setString(3, object.getTglperiksa());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Dicetak!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_KartuPeriksa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(KartuPeriksa object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KartuPeriksa> getAll() {
        List<KartuPeriksa> list = null;
        return null;
    }

    @Override
    public List<KartuPeriksa> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
