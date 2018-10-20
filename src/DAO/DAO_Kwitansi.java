/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.Database;
import Model.Kwitansi;
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
public class DAO_Kwitansi implements Model_DAO<Kwitansi>{
    public DAO_Kwitansi(){
        connection = Database.KoneksiDB();
        }
    
    Connection connection;
    String CARINOTA = "SELECT * FROM nota where no_nota=?"; 
    String TAMPILNOTA = "SELECT * FROM nota order by no_nota";
    String COUNTER = "SELECT ifnull(max(convert(right(no_kwitansi,3),signed integer)),0) as kode,"
            + "ifnull(length(max(convert(right(no_kwitansi,3),signed integer))),0)as panjang "
            + "from kwitansi ";

    @Override
    public int autonumber(Kwitansi object) {
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
    //combonota
        public List<Kwitansi> isicombonota() {
        PreparedStatement statement;
        List<Kwitansi> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(TAMPILNOTA);
            ResultSet rs = statement.executeQuery();              
            while (rs.next()) {
                Kwitansi b = new Kwitansi();  
                b.setNonota(rs.getString("no_nota"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        //manggil data nota lewat combo nota
    public List<Kwitansi> getNota(String nonota) {
        PreparedStatement statement;
        List<Kwitansi> list = null;
        try {
            list = new ArrayList();
            statement = connection.prepareStatement(CARINOTA);
            statement.setString(1, nonota);
            ResultSet rs = statement.executeQuery();   
           
            while (rs.next()) {                         
                Kwitansi b = new Kwitansi();
                b.setDp(rs.getDouble("dp"));
                b.setSisa(rs.getDouble("sisa_pembayaran"));
                list.add(b);            
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    @Override
    public void insert(Kwitansi object) {
        PreparedStatement statement = null;
        try {
            String CARI = "SELECT * from kwitansi where no_kwitansi like ?";
            statement = connection.prepareStatement(CARI);
            statement.setString(1, object.getNokwitansi());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) //jika data sudah pernah disimpan
            {
                JOptionPane.showMessageDialog(null, "Data sudah pernah di simpan!");
            } else {    //jika data belum pernah disimpan             
                PreparedStatement statement2 = null;
                String INSERT="INSERT INTO kwitansi(no_kwitansi,tgl_kwitansi,dp,sisa_pembayaran,jml_lunas,no_nota) values(?,?,?,?,?,?)" ;
                statement2 = connection.prepareStatement(INSERT);
                statement2.setString(1, object.getNokwitansi());
                statement2.setString(2, object.getTglkwitansi());
                statement2.setDouble(3, object.getDp());
                statement2.setDouble(4, object.getSisa());
                statement2.setDouble(5, object.getLunas());
                statement2.setString(6, object.getNonota());
                statement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Dicetak!");
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
    public void update(Kwitansi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Kwitansi> getAll() {
        List<Kwitansi> list = null;
        return null;
    }

    @Override
    public List<Kwitansi> getCari(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
