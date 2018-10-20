/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Ipeng
 */
public class Database {
    static Properties mypanel;
    static String driver, database,user,pass;
    static Connection conn;
    
    
    public static Connection KoneksiDB(){
        if(conn == null){
            try {
                mypanel = new Properties();
                mypanel.load(new FileInputStream("lib/database.ini"));
                driver  = mypanel.getProperty("DBDriver");
                database = mypanel.getProperty("DBDatabase");
                user = mypanel.getProperty("DBUsername");
                pass = mypanel.getProperty("DBPassword");
                
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(database,user,pass);
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil","pesan",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Koneksi Gagal","pesan",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Erorr : "+ex.getMessage());
                }
        }
        return conn;
    }
}
