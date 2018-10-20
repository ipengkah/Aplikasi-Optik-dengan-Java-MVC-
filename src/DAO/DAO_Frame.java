/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Frame;
import Model.Pelanggan;
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
public class DAO_Frame implements Model_DAO<Frame>{
    
    public DAO_Frame(){
        connection = Database.KoneksiDB();
    }
    Connection connection;
    String INSERT = "INSERT INTO frame(kd_frame,nm_frame,hrg_beli_satuan,hrg_jual_frame,stok_frame) values(?,?,?,?,?)";
    String UPDATE = "UPDATE frame SET nm_frame=?, hrg_beli_satuan=?, hrg_jual_frame=?,stok_frame=? WHERE kd_frame=?";
    String DELETE = "DELETE FROM frame WHERE kd_frame=?";
    String SELECT = "SELECT * FROM frame order by kd_frame";
    String CARI = "SELECT * FROM frame where kd_frame like ?";
    String COUNTER = "SELECT ifnull(max(convert(right(kd_frame,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kd_frame,2),signed integer))),0)as panjang "
            + "from frame ";

    @Override
    public int autonumber(Frame object) {
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
    public void insert(Frame object) {
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
                Logger.getLogger(DAO_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Frame object) {
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
                JOptionPane.showMessageDialog(null, "Kode barang tersebut belum pernah di simpan!");    
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}

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
                Logger.getLogger(DAO_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}

    @Override
    public List<Frame> getAll() {
        List<Frame> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Frame>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Frame f = new Frame();
                f.setKode(rs.getString("kd_frame"));
                f.setNama(rs.getString("nm_frame"));
                f.setHargabeli(rs.getInt("hrg_beli_satuan"));
                f.setHargajual(rs.getInt("hrg_jual_frame"));
                f.setStok(rs.getInt("stok_frame"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Frame> getCari(String key) {
        List<Frame> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Frame>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
            Frame f = new Frame();
            f.setKode(rs.getString("kd_frame"));
            f.setNama(rs.getString("nm_frame"));
            f.setHargabeli(rs.getInt("hrg_beli_satuan"));
            f.setHargajual(rs.getInt("hrg_jual_frame"));
            f.setStok(rs.getInt("stok_frame"));
            list.add(f);
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
