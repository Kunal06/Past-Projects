/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ReadWrite {

    Connection conn = new DBconnect().connect();

    public static void savelatestID(String file, String ID) {
        //filewriter
        //get the number from the textfield
        //save it (println)
        try {
            FileWriter file2 = new FileWriter(file, false);
            PrintWriter idFile = new PrintWriter(file2);
            idFile.println(ID);
            idFile.close();

        } catch (Exception e) {
            System.out.println("Error in writing ");
        }
    }

    public static String userid(String file) {
        String m = "";
        //read the file customerID
        //filereader , readLine()
        String newID = "";
        try {
            FileReader idFile = new FileReader(file);
            BufferedReader idReader = new BufferedReader(idFile);
            int idPresent = Integer.parseInt(idReader.readLine());
            //increment the id by 1
            idPresent = idPresent + 1;
            newID = "" + idPresent;
            idReader.close();

        } catch (Exception e) {
            System.out.println("Error in ID generator");
        }

        return newID;

    }

    public static void WriteToFile(String datafile, String idfile, String data, String ID) {
        try {

            FileWriter userfw = new FileWriter(datafile, true);
            PrintWriter userfile = new PrintWriter(userfw);
            userfile.println(data);
            userfile.close();
            savelatestID(idfile, ID);
            JOptionPane.showMessageDialog(null, "The data has been saved");

            // reload all fields
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteToFilenoID(String datafile, String data) {
        try {

            FileWriter userfw = new FileWriter(datafile, true);
            PrintWriter userfile = new PrintWriter(userfw);
            userfile.println(data);
            userfile.close();
            JOptionPane.showMessageDialog(null, "The user data has been saved");

            // reload all fields
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteToFilenoIDlogin(String datafile, String data) {
        try {

            FileWriter userfw = new FileWriter(datafile, false);
            PrintWriter userfile = new PrintWriter(userfw);
            userfile.println(data);
            userfile.close();

            // reload all fields
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ReadFromFileLogin(String username, String password) {
        String department = "";

        try {

            FileReader fileuser = new FileReader("userfile.txt");
            BufferedReader userfile = new BufferedReader(fileuser);
            String r = "";

            while ((r = userfile.readLine()) != null) {

                StringTokenizer userdata = new StringTokenizer(r, ",");

                String ID = userdata.nextToken();
                String firstname = userdata.nextToken();
                String lastname = userdata.nextToken();
                String dept = userdata.nextToken().trim();
                String salary = userdata.nextToken();
                String phone = userdata.nextToken();
                String email = userdata.nextToken();
                String user = userdata.nextToken();
                String pass = userdata.nextToken();
                String fingerprint = userdata.nextToken();

                if (username.equalsIgnoreCase(user) && password.equals(pass)) {
                    department = dept;
                    String data = ID + "," + firstname + "," + lastname + "," + department + "," + salary + "," + phone + "," + email + "," + username + "," + pass + "," + fingerprint;
                    WriteToFilenoIDlogin("name.txt", data);
                    break;
                } else {
                    department = "";
                }
            }
            if (department.equals("")) {

                //JOptionPane.showMessageDialog(null, "Incorrect username or password");
            }

        } catch (Exception h) {
            h.printStackTrace();
        }
        return department;
    }

    public static void ReadFromuserFiletoTable(DefaultTableModel Model) {
        String department = "";

        Model.setRowCount(0);
        try {

            FileReader fileuser = new FileReader("userfile.txt");
            BufferedReader userfile = new BufferedReader(fileuser);
            String r = "";

            while ((r = userfile.readLine()) != null) {

                StringTokenizer userdata = new StringTokenizer(r, ",");

                String ID = userdata.nextToken();
                String firstname = userdata.nextToken();
                String lastname = userdata.nextToken();
                String dept = userdata.nextToken().trim();
                String salary = userdata.nextToken();
                String phone = userdata.nextToken();
                String email = userdata.nextToken();
                String user = userdata.nextToken();
                String pass = userdata.nextToken();
                String fingerprint = userdata.nextToken();

                Model.insertRow(Model.getRowCount(), new Object[]{ID, firstname, lastname, dept, salary, phone, email, user, pass, fingerprint});
            }
        } catch (Exception h) {
            h.printStackTrace();
        }
    }

    public static void SavetouserFilefromTable(DefaultTableModel Model) {
        String data = "";
        try {
            FileWriter userfw = new FileWriter("userfile.txt", false);
            PrintWriter userfile = new PrintWriter(userfw);

            for (int i = 0; i < Model.getRowCount(); i++) {

                String ID = "" + Model.getValueAt(i, 0);
                String firstname = "" + Model.getValueAt(i, 1);
                String lastname = "" + Model.getValueAt(i, 2);
                String department = "" + Model.getValueAt(i, 3);
                String salary = "" + Model.getValueAt(i, 4);
                String phone = "" + Model.getValueAt(i, 5);
                String email = "" + Model.getValueAt(i, 6);
                String username = "" + Model.getValueAt(i, 7);
                String Password = "" + Model.getValueAt(i, 8);
                String fingerprint = "" + Model.getValueAt(i, 9);

                data += ID + "," + firstname + "," + lastname + "," + department + "," + salary + "," + phone + "," + email + "," + username + "," + Password + "," + fingerprint + "\n";

            }

            userfile.println(data);
            userfile.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void SaveEdittoORFilefromTable(DefaultTableModel Model, String PMID) {
        String data = "";
        String newdata = "";
        try {
            FileWriter overtime = new FileWriter("temp.txt", false);
            PrintWriter overtimefile = new PrintWriter(overtime);

            FileReader fileproj = new FileReader("OvertimeRequest.txt");
            BufferedReader projfile = new BufferedReader(fileproj);
            String r = "";

            while ((r = projfile.readLine()) != null) {

                StringTokenizer projdata = new StringTokenizer(r, ",");
                String userid = projdata.nextToken();
                String projectmanagerID = projdata.nextToken();
                String projID = projdata.nextToken();
                String projectname = projdata.nextToken();
                String overtimedate = projdata.nextToken();
                String timefrom = projdata.nextToken();
                String timeto = projdata.nextToken();
                String hour = projdata.nextToken();
                String min = projdata.nextToken();
                String daterequested = projdata.nextToken();
                String amounttopay = projdata.nextToken();
                String todept = projdata.nextToken();
                String status = projdata.nextToken();

                if (PMID.equals(projectmanagerID)) {

                    for (int i = 0; i <= Model.getRowCount(); i++) {

                        String userID = "" + Model.getValueAt(i, 0);
                        String pmID = "" + Model.getValueAt(i, 1);
                        String projectID = "" + Model.getValueAt(i, 2);
                        String daterequest = "" + Model.getValueAt(i, 3);;
                        String project = "" + Model.getValueAt(i, 4);
                        String overtimedat = "" + Model.getValueAt(i, 6);
                        String fromtime = "" + Model.getValueAt(i, 7);
                        String totime = "" + Model.getValueAt(i, 8);
                        String Duration = "" + Model.getValueAt(i, 9);
                        String amounttobepaid = "" + Model.getValueAt(i, 10);
                        String todep = "" + Model.getValueAt(i, 11);
                        String Status = "" + Model.getValueAt(i, 12);

                        String[] hrmin = Duration.split(":");

                        if (pmID.equals(projectmanagerID) && userID.equals(userid) && projID.equals(projectID)) {
                            System.out.println("matched");
                            data += userID + "," + pmID + "," + projectID + "," + project + "," + overtimedat + "," + fromtime + "," + totime + "," + hrmin[0] + "," + hrmin[1] + "," + daterequest + "," + amounttobepaid + "," + todep + "," + Status + "\n";
                            break;
                        }
                        //data += userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status+"\n";

                    }

                } else {
                    data += userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status + "\n";
                }

            }
            overtimefile.println(data);
            overtimefile.close();
            projfile.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            FileReader fileproj = new FileReader("temp.txt");
            BufferedReader projfile = new BufferedReader(fileproj);
            String r = "";

            FileWriter overtime = new FileWriter("OvertimeRequest.txt", false);
            PrintWriter overtimefile = new PrintWriter(overtime);

            while ((r = projfile.readLine()) != null) {
                overtimefile.println(r);

            }

            overtimefile.close();

        } catch (Exception e) {

        }

    }

    public static void tableandfilesave(DefaultTableModel Model, String promanID) {
                String sqlor = "UPDATE OvertimeRequest SET NextDept=?, Status=? WHERE userid=? AND PMid=? AND Projectid=? ";
        Connection conn = new DBconnect().connect();
        for (int i = 0; i < Model.getRowCount(); i++) {
            System.out.println("Enters for");
            String userID = "" + Model.getValueAt(i, 0);
            String pmID = "" + Model.getValueAt(i, 1);
            String projectID = "" + Model.getValueAt(i, 2);
            String daterequest = "" + Model.getValueAt(i, 3);;
            String project = "" + Model.getValueAt(i, 4);
            String overtimedat = "" + Model.getValueAt(i, 6);
            String fromtime = "" + Model.getValueAt(i, 7);
            String totime = "" + Model.getValueAt(i, 8);
            String Duration = "" + Model.getValueAt(i, 9);
            String amounttobepaid = "" + Model.getValueAt(i, 10);
            String todep = "" + Model.getValueAt(i, 11);
            String Status = "" + Model.getValueAt(i, 12);

            String[] hrmin = Duration.split(":");
            if (!(todep.equals("Project Manager"))) {
                try {
                    PreparedStatement papm = conn.prepareStatement(sqlor);

                    papm.setString(1, todep);
                    papm.setString(2, Status);
                    papm.setString(3, userID);
                    papm.setString(4, pmID);
                    papm.setString(5, projectID);

                    int rspm = papm.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /*try {
         FileWriter overtime = new FileWriter("temp.txt", false);
         PrintWriter overtimefile = new PrintWriter(overtime);

         FileReader fileproj = new FileReader("OvertimeRequest.txt");
         BufferedReader projfile = new BufferedReader(fileproj);
         String r = "";

         while ((r = projfile.readLine()) != null) {
         System.out.println("Enters while");
         StringTokenizer projdata = new StringTokenizer(r, ",");
         String userid = projdata.nextToken();
         String projectmanagerID = projdata.nextToken();
         String projID = projdata.nextToken();
         String projectname = projdata.nextToken();
         String overtimedate = projdata.nextToken();
         String timefrom = projdata.nextToken();
         String timeto = projdata.nextToken();
         String hour = projdata.nextToken();
         String min = projdata.nextToken();
         String daterequested = projdata.nextToken();
         String amounttopay = projdata.nextToken();
         String todept = projdata.nextToken();
         String status = projdata.nextToken();

         if (projectmanagerID.equals(promanID) && status.equals("Pending review from Project Manager")) {

         for (int i = 0; i < Model.getRowCount(); i++) {
         System.out.println("Enters for");
         String userID = "" + Model.getValueAt(i, 0);
         String pmID = "" + Model.getValueAt(i, 1);
         String projectID = "" + Model.getValueAt(i, 2);
         String daterequest = "" + Model.getValueAt(i, 3);;
         String project = "" + Model.getValueAt(i, 4);
         String overtimedat = "" + Model.getValueAt(i, 6);
         String fromtime = "" + Model.getValueAt(i, 7);
         String totime = "" + Model.getValueAt(i, 8);
         String Duration = "" + Model.getValueAt(i, 9);
         String amounttobepaid = "" + Model.getValueAt(i, 10);
         String todep = "" + Model.getValueAt(i, 11);
         String Status = "" + Model.getValueAt(i, 12);

         String[] hrmin = Duration.split(":");

         if (userID.equals(userid) && projID.equals(projectID)) {
         System.out.println("matched");
         data = userID + "," + pmID + "," + projectID + "," + project + "," + overtimedat + "," + fromtime + "," + totime + "," + hrmin[0] + "," + hrmin[1] + "," + daterequest + "," + amounttobepaid + "," + todep + "," + Status;
         overtimefile.println(data);
         }

         }

         } else {
         data = userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status;
         overtimefile.println(data);
         }
         }

         overtimefile.close();
         projfile.close();

         } catch (Exception e) {
         e.printStackTrace();

         }*/
    }

    public static void fromtemptomaninfile() {
        String data = "";
        try {
            FileWriter overtime = new FileWriter("OvertimeRequest.txt", false);
            PrintWriter overtimefile = new PrintWriter(overtime);

            FileReader fileproj = new FileReader("temp.txt");
            BufferedReader projfile = new BufferedReader(fileproj);
            String r = "";

            while ((r = projfile.readLine()) != null) {
                System.out.println("Enters while");

                StringTokenizer projdata = new StringTokenizer(r, ",");
                String userid = projdata.nextToken();
                String projectmanagerID = projdata.nextToken();
                String projID = projdata.nextToken();
                String projectname = projdata.nextToken();
                String overtimedate = projdata.nextToken();
                String timefrom = projdata.nextToken();
                String timeto = projdata.nextToken();
                String hour = projdata.nextToken();
                String min = projdata.nextToken();
                String daterequested = projdata.nextToken();
                String amounttopay = projdata.nextToken();
                String todept = projdata.nextToken();
                String status = projdata.nextToken();

                data = userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status;
                overtimefile.println(data);
            }
            overtimefile.close();

        } catch (Exception e) {

        }
    }

    public static void tableandfilesaveAdmin(DefaultTableModel Model, String promanID) {
        Connection conn = new DBconnect().connect();
        for (int i = 0; i < Model.getRowCount(); i++) {
            System.out.println("Enters for");
            String userID = "" + Model.getValueAt(i, 0);
            String pmID = "" + Model.getValueAt(i, 1);
            String projectID = "" + Model.getValueAt(i, 2);
            String daterequest = "" + Model.getValueAt(i, 3);;
            String project = "" + Model.getValueAt(i, 4);
            String overtimedat = "" + Model.getValueAt(i, 6);
            String fromtime = "" + Model.getValueAt(i, 7);
            String totime = "" + Model.getValueAt(i, 8);
            String Duration = "" + Model.getValueAt(i, 9);
            String amounttobepaid = "" + Model.getValueAt(i, 10);
            String todep = "" + Model.getValueAt(i, 11);
            String Status = "" + Model.getValueAt(i, 12);

            String[] hrmin = Duration.split(":");
            if (!(todep.equals("Admin"))) {
                System.out.println("Enters if");

                String sqlor = "UPDATE OvertimeRequest SET Projectname=?, NextDept=?, Status=? WHERE userid=? AND OvertimeDate=? ";
                try {
                    PreparedStatement papm = conn.prepareStatement(sqlor);
                    System.out.println("Enters try");
                    
                    papm.setString(1, project);
                    papm.setString(2, todep);
                    papm.setString(3, Status);
                    papm.setString(4, userID);
                    papm.setString(5, overtimedat);

                    int rspm = papm.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*try {
     FileWriter overtime = new FileWriter("temp.txt", false);
     PrintWriter overtimefile = new PrintWriter(overtime);

     FileReader fileproj = new FileReader("OvertimeRequest.txt");
     BufferedReader projfile = new BufferedReader(fileproj);
     String r = "";

     while ((r = projfile.readLine()) != null) {
     System.out.println("Enters while");
     StringTokenizer projdata = new StringTokenizer(r, ",");
     String userid = projdata.nextToken();
     String projectmanagerID = projdata.nextToken();
     String projID = projdata.nextToken();
     String projectname = projdata.nextToken();
     String overtimedate = projdata.nextToken();
     String timefrom = projdata.nextToken();
     String timeto = projdata.nextToken();
     String hour = projdata.nextToken();
     String min = projdata.nextToken();
     String daterequested = projdata.nextToken();
     String amounttopay = projdata.nextToken();
     String todept = projdata.nextToken();
     String status = projdata.nextToken();

     if (todept.equals("Admin") && status.equals("Pending review from Admin")) {

     for (int i = 0; i < Model.getRowCount(); i++) {
     System.out.println("Enters for");
     String userID = "" + Model.getValueAt(i, 0);
     String pmID = "" + Model.getValueAt(i, 1);
     String projectID = "" + Model.getValueAt(i, 2);
     String daterequest = "" + Model.getValueAt(i, 3);;
     String project = "" + Model.getValueAt(i, 4);
     String overtimedat = "" + Model.getValueAt(i, 6);
     String fromtime = "" + Model.getValueAt(i, 7);
     String totime = "" + Model.getValueAt(i, 8);
     String Duration = "" + Model.getValueAt(i, 9);
     String amounttobepaid = "" + Model.getValueAt(i, 10);
     String todep = "" + Model.getValueAt(i, 11);
     String Status = "" + Model.getValueAt(i, 12);

     String[] hrmin = Duration.split(":");

     if (userID.equals(userid) && overtimedat.equals(overtimedate)) {
     System.out.println("matched");
     data = userID + "," + pmID + "," + projectID + "," + project + "," + overtimedat + "," + fromtime + "," + totime + "," + hrmin[0] + "," + hrmin[1] + "," + daterequest + "," + amounttobepaid + "," + todep + "," + Status;
     overtimefile.println(data);
     }

     }

     } else {
     data = userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status;
     overtimefile.println(data);
     }
     }

     overtimefile.close();
     projfile.close();

     } catch (Exception e) {
     e.printStackTrace();

     }
     }*/
    public static void tableandfilesaveAccounts(DefaultTableModel Model, String manID) {
                Connection conn = new DBconnect().connect();
        for (int i = 0; i < Model.getRowCount(); i++) {
            System.out.println("Enters for");
            String userID = "" + Model.getValueAt(i, 0);
            String pmID = "" + Model.getValueAt(i, 1);
            String projectID = "" + Model.getValueAt(i, 2);
            String daterequest = "" + Model.getValueAt(i, 3);;
            String project = "" + Model.getValueAt(i, 4);
            String overtimedat = "" + Model.getValueAt(i, 6);
            String fromtime = "" + Model.getValueAt(i, 7);
            String totime = "" + Model.getValueAt(i, 8);
            String Duration = "" + Model.getValueAt(i, 9);
            String amounttobepaid = "" + Model.getValueAt(i, 10);
            String todep = "" + Model.getValueAt(i, 11);
            String Status = "" + Model.getValueAt(i, 12);

            String[] hrmin = Duration.split(":");
            if (!(todep.equals("Accounts"))) {
                System.out.println("Enters if");

                String sqlor = "UPDATE OvertimeRequest SET Projectname=?, NextDept=?, Status=? WHERE userid=? AND OvertimeDate=? ";
                try {
                    PreparedStatement papm = conn.prepareStatement(sqlor);
                    System.out.println("Enters try");
                    
                    papm.setString(1, project);
                    papm.setString(2, todep);
                    papm.setString(3, Status);
                    papm.setString(4, userID);
                    papm.setString(5, overtimedat);

                    int rspm = papm.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        /*try {
            FileWriter overtime = new FileWriter("temp.txt", false);
            PrintWriter overtimefile = new PrintWriter(overtime);

            FileReader fileproj = new FileReader("OvertimeRequest.txt");
            BufferedReader projfile = new BufferedReader(fileproj);
            String r = "";

            while ((r = projfile.readLine()) != null) {
                System.out.println("Enters while");
                StringTokenizer projdata = new StringTokenizer(r, ",");
                String userid = projdata.nextToken();
                String projectmanagerID = projdata.nextToken();
                String projID = projdata.nextToken();
                String projectname = projdata.nextToken();
                String overtimedate = projdata.nextToken();
                String timefrom = projdata.nextToken();
                String timeto = projdata.nextToken();
                String hour = projdata.nextToken();
                String min = projdata.nextToken();
                String daterequested = projdata.nextToken();
                String amounttopay = projdata.nextToken();
                String todept = projdata.nextToken();
                String status = projdata.nextToken();

                if (todept.equals("Accounts") && status.equals("Waiting for payment")) {

                    for (int i = 0; i < Model.getRowCount(); i++) {
                        System.out.println("Enters for");
                        String userID = "" + Model.getValueAt(i, 0);
                        String pmID = "" + Model.getValueAt(i, 1);
                        String projectID = "" + Model.getValueAt(i, 2);
                        String daterequest = "" + Model.getValueAt(i, 3);;
                        String project = "" + Model.getValueAt(i, 4);
                        String overtimedat = "" + Model.getValueAt(i, 6);
                        String fromtime = "" + Model.getValueAt(i, 7);
                        String totime = "" + Model.getValueAt(i, 8);
                        String Duration = "" + Model.getValueAt(i, 9);
                        String amounttobepaid = "" + Model.getValueAt(i, 10);
                        String todep = "" + Model.getValueAt(i, 11);
                        String Status = "" + Model.getValueAt(i, 12);

                        String[] hrmin = Duration.split(":");

                        if (userID.equals(userid) && overtimedat.equals(overtimedate)) {
                            System.out.println("matched");
                            data = userID + "," + pmID + "," + projectID + "," + project + "," + overtimedat + "," + fromtime + "," + totime + "," + hrmin[0] + "," + hrmin[1] + "," + daterequest + "," + amounttobepaid + "," + todep + "," + Status;
                            overtimefile.println(data);
                        }

                    }

                } else {
                    data = userid + "," + projectmanagerID + "," + projID + "," + projectname + "," + overtimedate + "," + timefrom + "," + timeto + "," + hour + "," + min + "," + daterequested + "," + amounttopay + "," + todept + "," + status;
                    overtimefile.println(data);
                }
            }

            overtimefile.close();
            projfile.close();

        } catch (Exception e) {
            e.printStackTrace();

        }*/
    }

    public static String loadloggedinuser() {
        String fname = "";
        try {

            FileReader fileuser = new FileReader("name.txt");
            BufferedReader userfile = new BufferedReader(fileuser);
            String r = "";

            while ((r = userfile.readLine()) != null) {

                StringTokenizer userdata = new StringTokenizer(r, ",");

                String ID = userdata.nextToken();
                String firstname = userdata.nextToken();
                String lastname = userdata.nextToken();
                String dept = userdata.nextToken().trim();
                String salary = userdata.nextToken();
                String phone = userdata.nextToken();
                String email = userdata.nextToken();
                String user = userdata.nextToken();
                String pass = userdata.nextToken();
                String fingerprint = userdata.nextToken();

                fname = firstname;
            }

        } catch (Exception h) {
            h.printStackTrace();
        }
        return fname;
    }

    public static void planereader() {
        try {

            FileReader fileuser = new FileReader("userfile.txt");
            BufferedReader userfile = new BufferedReader(fileuser);
            String r = "";

            while ((r = userfile.readLine()) != null) {

                StringTokenizer userdata = new StringTokenizer(r, ",");

                String ID = userdata.nextToken();
                String firstname = userdata.nextToken();
                String lastname = userdata.nextToken();
                String dept = userdata.nextToken().trim();
                String salary = userdata.nextToken();
                String phone = userdata.nextToken();
                String email = userdata.nextToken();
                String user = userdata.nextToken();
                String pass = userdata.nextToken();
                String fingerprint = userdata.nextToken();

            }
        } catch (Exception h) {
            h.printStackTrace();
        }
    }

}
