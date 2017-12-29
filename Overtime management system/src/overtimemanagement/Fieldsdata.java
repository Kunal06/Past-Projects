/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class Fieldsdata {

    public static void onlyint(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }
    public static void autocapitalizefirstletter(JTextField name){
        if (!(name.getText().equals(""))) {
            String a = name.getText();
            name.setText(Character.toUpperCase(a.charAt(0)) + a.substring(1));        
        }    
    }
    public static void timeonly(KeyEvent evt) {
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
            
        }
    }

}
