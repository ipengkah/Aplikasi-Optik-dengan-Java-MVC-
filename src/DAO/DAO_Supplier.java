/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Pelanggan;
import Model.Supplier;
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
public class DAO_Supplier implements Model_DAO<Supplier>{

     //konstruktor 
    public DAO_Supplier(){
        connection = Database.KoneksiDB();
    }
    
    Connection connection;
    String INSERT = "INSERT INTO supplier(kd_supp,nm_supp,alamat_supp,tlpn_supp) values(?,?,?,?)";
    String UPDATE = "UPDATE supplier SET nm_supp=?, alamat_supp=?, tlpn_supp=? WHERE kd_supp=?";
    String DELETE = "DELETE FROM supplier WHERE kd_supp=?";
    String SELECT = "SELECT * FROM supplier order by kd_supp";
    String CARI = "SELECT * FROM supplier where kd_supp like ?";
    String COUNTER = "SELECT ifnull(max(convert(right(kd_supp,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kd_supp,2),signed integer))),0)as panjang "
            + "from supplier ";
    
    @Override
    public int autonumber(Supplier object) {
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
    public void insert(Supplier object) {
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
                statement2.setString(3, object.getAlamat());
                statement2.setString(4, object.getTlp());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Supplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Supplier object) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getKode());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                statement = connection.prepareStatement(UPDATE);
                statement.setString(1, object.getNama());
                statement.setString(2, object.getAlamat());
                statement.setString(3, object.getTlp());
                statement.setString(4, object.getKode());
                statement.executeUpdate();                    
                JOptionPane.showMessageDialog(null, "Data berhasil di ubah!");                
            } else {    //jika data belum pernah disimpan     
                JOptionPane.showMessageDialog(null, "Kode barang tersebut belum pernah di simpan!");    
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Supplier.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DAO_Supplier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Supplier>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setKode(rs.getString("kd_supp"));
                s.setNama(rs.getString("nm_supp"));
                s.setTlp(rs.getString("tlpn_supp"));
                s.setAlamat(rs.getString("alamat_supp"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Supplier> getCari(String key) {
         List<Supplier> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Supplier>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
            Supplier s = new Supplier();
            s.setKode(rs.getString("kd_supp"));
            s.setNama(rs.getString("nm_supp"));
            s.setAlamat(rs.getString("alamat_supp"));  
            s.setTlp(rs.getString("tlpn_supp"));
            list.add(s);
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
