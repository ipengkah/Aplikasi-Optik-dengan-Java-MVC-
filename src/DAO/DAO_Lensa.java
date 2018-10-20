/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Lensa;
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
public class DAO_Lensa implements Model_DAO<Lensa>{

    public DAO_Lensa(){
        connection = Database.KoneksiDB();
    }
    Connection connection;
    String INSERT = "INSERT INTO lensa(kd_lensa,nm_lensa,hrg_beli_satuan,hrg_jual_lensa,stok_lensa) values(?,?,?,?,?)";
    String UPDATE = "UPDATE lensa SET nm_lensa=?, hrg_beli_satuan=?, hrg_jual_lensa=?,stok_lensa=? WHERE kd_lensa=?";
    String DELETE = "DELETE FROM lensa WHERE kd_lensa=?";
    String SELECT = "SELECT * FROM lensa order by kd_lensa";
    String CARI = "SELECT * FROM lensa where kd_lensa like ?";
    String COUNTER = "SELECT ifnull(max(convert(right(kd_lensa,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kd_lensa,2),signed integer))),0)as panjang "
            + "from lensa ";
    
    
    @Override
    public int autonumber(Lensa object) {
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

    @Override
    public void insert(Lensa object) {
         PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKode());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getKode());
                statement2.setString(2, object.getNama());
                statement2.setDouble(3, object.getHargabeli());
                statement2.setDouble(4, object.getHargajual());
                statement2.setInt(5, object.getStok());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Lensa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Lensa object) {
          PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKode());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                statement = connection.prepareStatement(UPDATE);
                statement.setString(1, object.getNama());
                statement.setDouble(2, object.getHargabeli());
                statement.setDouble(3, object.getHargajual());
                statement.setInt(4, object.getStok());
                statement.setString(5, object.getKode());
                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah!");                
            } else {    //jika data belum pernah disimpan     
                JOptionPane.showMessageDialog(null, "Kode lensa tersebut belum pernah di simpan!");    
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Lensa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public void delete(String id) {
         PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setString(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Lensa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Lensa> getAll() {
        List<Lensa> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Lensa>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lensa l = new Lensa();
                l.setKode(rs.getString("kd_lensa"));
                l.setNama(rs.getString("nm_lensa"));
                l.setHargabeli(rs.getInt("hrg_beli_satuan"));
                l.setHargajual(rs.getInt("hrg_jual_lensa"));
                l.setStok(rs.getInt("stok_lensa"));
                list.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Lensa> getCari(String key) {
           List<Lensa> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Lensa>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
            Lensa l = new Lensa();
            l.setKode(rs.getString("kd_lensa"));
            l.setNama(rs.getString("nm_lensa"));
            l.setHargabeli(rs.getInt("hrg_beli_satuan"));
            l.setHargajual(rs.getInt("hrg_jual_lensa"));
            l.setStok(rs.getInt("stok_lensa"));
            list.add(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
