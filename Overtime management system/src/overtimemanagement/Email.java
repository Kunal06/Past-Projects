package overtimemanagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Email {
    
    public Pattern regexPattern;
    public Matcher regMatcher;
    public String email;
    
    public Email(String email){
        this.email=email;
    }
    
    public String validateEmailAddress() {

    regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
    regMatcher   = regexPattern.matcher(email);
    if(regMatcher.matches()){
        return "Valid Email Address";
    } else {
    return "Invalid Email Address";
    }
}
    public static int amounttobepaid(int Salary, int hours,int min){
        double mintohours= min/60.00;
        double basic= (double)(Salary/30.00)/8.0;
        double totalhours= (double) hours + mintohours;
        double amttopay= Math.round(basic * 1.5 * totalhours);
        int finalpay= (int) amttopay;
        return finalpay;
        
    }
    
}
