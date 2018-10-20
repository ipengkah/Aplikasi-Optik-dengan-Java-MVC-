/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
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
public class DAO_Pelanggan implements Model_DAO<Pelanggan>{
    
     //konstruktor 
    public DAO_Pelanggan(){
        connection = Database.KoneksiDB();
    }
    Connection connection;
    String INSERT = "INSERT INTO pelanggan(kd_pelanggan,nm_pelanggan,alamat,umur,telp) values(?,?,?,?,?)";
    String UPDATE = "UPDATE pelanggan SET nm_pelanggan=?, alamat=?, umur=?,telp=? WHERE kd_pelanggan=?";
    String DELETE = "DELETE FROM pelanggan WHERE kd_pelanggan=?";
    String SELECT = "SELECT * FROM pelanggan order by kd_pelanggan";
    String CARI = "SELECT * FROM pelanggan where kd_pelanggan like ?";
    String COUNTER = "SELECT ifnull(max(convert(right(kd_pelanggan,2),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(kd_pelanggan,2),signed integer))),0)as panjang "
            + "from pelanggan ";
    
    @Override
    public int autonumber(Pelanggan object) {
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
    public void insert(Pelanggan object) {
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
                statement2.setString(4, object.getUmur());
                statement2.setString(5, object.getTlp());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil di simpan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }

    @Override
    public void update(Pelanggan object) {
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
                statement.setString(3, object.getUmur());
                statement.setString(4, object.getTlp());
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
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DAO_Pelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Pelanggan> getAll() {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Pelanggan>();
            statement = connection.prepareStatement(SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Pelanggan b = new Pelanggan();
                b.setKode(rs.getString("kd_pelanggan"));
                b.setNama(rs.getString("nm_pelanggan"));
                b.setAlamat(rs.getString("alamat"));
                b.setUmur(rs.getString("umur"));
                b.setTlp(rs.getString("telp"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }

    @Override
    public List<Pelanggan> getCari(String key) {
        List<Pelanggan> list = null;
        PreparedStatement statement = null;
        try {
            list = new ArrayList<Pelanggan>();
            statement = connection.prepareStatement(CARI);
            statement.setString(1, "%"+key+"%");
            ResultSet rs = statement.executeQuery();
 
            while (rs.next()) {
            Pelanggan b = new Pelanggan();
            b.setKode(rs.getString("kd_pelanggan"));
            b.setNama(rs.getString("nm_pelanggan"));
            b.setAlamat(rs.getString("alamat"));
            b.setUmur(rs.getString("umur"));
            b.setTlp(rs.getString("telp"));
            list.add(b);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        }
    
        /*public String autonumber2(Integer id) {
        PreparedStatement statement = null;
        int nomor_berikutnya = 0;
        String urutan = "";
        try {

            statement = connection.prepareStatement(COUNTER);
            statement.setInt(1, id);
            ResultSet rs2 = statement.executeQuery();
            if (rs2.next()) {               
                 nomor_berikutnya = rs2.getInt("kode") + 1; 
                if (rs2.getInt("kode")!= 0) {//jika kode kategori sudah pernah ada                         
                            if (rs2.getInt("panjang") == 1) { //jika jumlah digitnya adalah 1
                                urutan = "B" + id + "0" + nomor_berikutnya;
                            } else if (rs2.getInt("panjang") == 2) { //jika jumlah digitnya adalah 2
                                urutan = "B" + id + nomor_berikutnya;
                            }
                    }
                    else {//jika kode kategori belum pernah ada                      
                        urutan = "B" + id + "01";
                } 

            }else
                 JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");              
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urutan;
    }*/

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
