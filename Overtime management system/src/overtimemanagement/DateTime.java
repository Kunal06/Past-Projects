/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class DateTime {
    
    
    
        
    public static String getTodayDate() {
        //defines the format
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
//gets system date 
        Date todayD = new Date();
//formats todays date as per our format
        String m = d.format(todayD);
//setting date to text box 
        return m;//will return string m
//if anything other than void , this statement has to be there
    }

    
    
    
    
    
}
