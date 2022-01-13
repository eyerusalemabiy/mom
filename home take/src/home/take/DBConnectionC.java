/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.take;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author THECREW
 */
public class DBConnectionC {

     private static final String url = "jdbc:mysql://localhost:3306/cci";
    private static final String user = "root";
    private static final String password = "";
    private static final String driver = "com.mysql.jdbc.Driver";

    public Connection connMethod() throws ClassNotFoundException {
        Class.forName(driver);
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
           // JOptionPane.showMessageDialog(null, "connected");

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error: " + ex);
        }
        return con;
    }
         

}
