/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class Overtimemanagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /* try {
            Class.forName("org.h2.Driver");
            Connection conn= DriverManager.getConnection("jdbc:h2:Overtime","Admin","Admin");
            Statement st= conn.createStatement();
             
            System.out.println("Connected to the database");
            
        } catch (ClassNotFoundException e) {
        } catch (SQLException ex) {
            Logger.getLogger(Overtimemanagement.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Loginpage lp= new Loginpage();
        lp.setVisible(true);
        // TODO code application logic here
        
    }
  
}
