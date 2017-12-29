/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static overtimemanagement.ReadWrite.WriteToFilenoID;
import static overtimemanagement.ReadWrite.loadloggedinuser;
import static overtimemanagement.Email.amounttobepaid;

/**
 *
 * @author User
 */
public class AddOvertimeRequest extends javax.swing.JPanel {

    public String date;
    public int sal;
    Connection conn = new DBconnect().connect();

    /**
     * Creates new form AddOvertimeRequest
     */
    public AddOvertimeRequest() {
        initComponents();
        load();

    }

    private void load() {
        fd.setText("");
        td.setText("");
        pm.setText("");

        projid.setVisible(false);
        pm.setEnabled(false);
        sala.setVisible(false);
        id.setVisible(false);
        usid.setVisible(false);
        //Read user logged in
        String sql = "select autoid,Firstname from Loggedin";
        try {
            PreparedStatement pa = conn.prepareStatement(sql);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {

                name.setText(rs.getString("Firstname"));
                usid.setText(rs.getString("autoid"));

            }
        } catch (Exception c) {
        }

        /*String fname = "";
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
         usid.setText(ID);
         sala.setText(salary);

         }

         } catch (Exception h) {
         h.printStackTrace();
         }
         name.setText(fname);*/
        //Add Date
        date = DateTime.getTodayDate();
        dat.setText(date);
        ordat.setCalendar(null);
        //Add Time
        Timer t = new Timer(1000, new Clockc());
        t.start();

        proj.removeAllItems();
        proj.addItem("Select Project");

        //Add project to project combo
        String sqlproj = "select Projectname from Project";
        try {
            PreparedStatement pa = conn.prepareStatement(sqlproj);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                String projectname = rs.getString("Projectname");
                if (!(projectname.equals("Other Project"))) {
                    proj.addItem(projectname);
                }
            }
        } catch (Exception c) {
        }
        proj.addItem("Other Project");

        /* try {

         FileReader fileuser = new FileReader("project.txt");
         BufferedReader userfile = new BufferedReader(fileuser);
         String r = "";

         while ((r = userfile.readLine()) != null) {

         StringTokenizer userdata = new StringTokenizer(r, ",");
         String projectmanagerID = userdata.nextToken();
         String projID = userdata.nextToken();

         String projectname = userdata.nextToken();
         String date = userdata.nextToken().trim();
         if (!(projectname.equals("Other Project"))) {
         proj.addItem(projectname);
         }

         }
         proj.addItem("Other Project");

         } catch (Exception h) {
         h.printStackTrace();
         }*/
    }

    class Clockc implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            tim.setText(sdf.format(cal.getTime()));

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tim = new javax.swing.JLabel();
        proj = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        name = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        projid = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        ordat = new com.toedter.calendar.JDateChooser();
        fd = new javax.swing.JTextField();
        td = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pm = new javax.swing.JTextField();
        sala = new javax.swing.JTextField();
        usid = new javax.swing.JTextField();

        dateChooserDialog1.setShowOneMonth(true);

        jLabel3.setText("Time");

        jLabel4.setText("From");

        dat.setText("Current Date");

        jLabel5.setText("To");

        jLabel6.setText("Project");

        tim.setText("Current Time");

        proj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Add Overtime Request");

        jButton1.setText("Send For Review");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        name.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        name.setText("Name Displayed Here");

        jLabel9.setText("Date");

        fd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fdKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fdKeyReleased(evt);
            }
        });

        td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tdKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tdKeyReleased(evt);
            }
        });

        jLabel2.setText("Project Manager");

        sala.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tim, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ordat, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(354, 354, 354)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(fd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(td, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(proj, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(projid, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(467, 467, 467)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(439, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dat)
                    .addComponent(tim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addComponent(ordat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(fd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(td, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(proj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(projid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            String userID = usid.getText();
            String pmID = id.getText();
            String projectID = projid.getText();
            
            String project = "" + proj.getSelectedItem();
            String projectmanager = pm.getText();
            String[] projmanager = projectmanager.split(",");
            int usersalary = Integer.parseInt(sala.getText());

            // Calculate date and time in minutes
            String fromt = fd.getText();
            String tot = td.getText();
            DateFormat sdf = new SimpleDateFormat("kk:mm");
            Date fromtime = sdf.parse(fromt);
            Date totime = sdf.parse(tot);

            long diff = totime.getTime() - fromtime.getTime();
            long diffhours = diff / (60 * 60 * 1000) % 24;
            long diffMinutes = (diff / (60 * 1000) % 60);
            int remainingmin = Integer.parseInt(diffMinutes + "");
            int totalhrworked = Integer.parseInt(diffhours + "");
            int amountopay = amounttobepaid(usersalary, totalhrworked, remainingmin);
            System.out.println(amountopay);

            String hr = "" + totalhrworked;
            String min = "" + remainingmin;
            String hours = "";
            String mins = "";
            if (hr.length() < 2) {
                hours = "0" + hr;
            }
            if (min.length() < 2) {
                mins = "00" + diffMinutes;
            }
            mins = min;

            Date dayworked = ordat.getCalendar().getTime();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String overtimedate = format.format(dayworked);

            String status = "Pending review from Project Manager";
            String todept = "Project Manager";

            if (project.equals("Other Project")) {
                status = "Pending review from Admin";
                todept = "Admin";
            }
            if (overtimedate.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter date of Overtime");
            } else if (fromt.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Overtime Start Time");
            } else if (tot.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter Overtime End Time");
            } else if (projectmanager.equals("")) {
                JOptionPane.showMessageDialog(null, "Select Project");
            } else {
                try {
                    PreparedStatement pa = conn.prepareStatement("insert into OvertimeRequest(userid,PMid,Projectid,Projectname,OvertimeDate,Timefrom,Timeto,Hours,Minutes,DateRequested,AmountDue,NextDept,Status) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    pa.setString(1, userID);
                    pa.setString(2, pmID);
                    pa.setString(3, projectID);
                    pa.setString(4, project);
                    pa.setString(5, overtimedate);
                    pa.setString(6, fromt);
                    pa.setString(7, tot);
                    pa.setString(8, hours);
                    pa.setString(9, mins);
                    pa.setString(10, dat.getText());
                    pa.setInt(11, amountopay);
                    pa.setString(12, todept);
                    pa.setString(13, status);

                    int i = pa.executeUpdate();

                    if (i > 0) {
                        JOptionPane.showMessageDialog((null), "saved");
                    } else {
                        JOptionPane.showMessageDialog((null), "notsaved");

                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception e) {
                    //Handle errors for Class.forName
                    e.printStackTrace();
                }
                //String data = userID + "," + pmID + "," + projectID + "," + project + "," + overtimedate + "," + fromt + "," + tot + "," + hours + "," + mins + "," + dat.getText() + "," + amountopay + "," + todept + "," + status;
                //WriteToFilenoID("OvertimeRequest.txt", data);
                load();
            }
        } catch (ParseException ex) {
            Logger.getLogger(AddOvertimeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void projActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projActionPerformed
        String project = "" + proj.getSelectedItem();

        String sqlp = "select * from Project where Projectname= ?";
        String users = "select * from users where autoid=?";
        try {
            PreparedStatement pa = conn.prepareStatement(sqlp);
            pa.setString(1, project);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                System.out.println("entered1");
                String projectmanagerid = rs.getString("userid");
                String projectid = rs.getString("projectid");
                id.setText(projectmanagerid);
                projid.setText(projectid);
                PreparedStatement use = conn.prepareStatement(users);
                use.setString(1, projectmanagerid);
                ResultSet rusers = use.executeQuery();
                while (rusers.next()) {
                    System.out.println("entered2");
                    id.setText("" + rusers.getInt("autoid"));
                    sala.setText("" + rusers.getInt("Salary"));

                    pm.setText(rusers.getString("Lastname") + "," + rusers.getString("Firstname"));

                }

            }

        } catch (Exception c) {
        }
        /*try {

         FileReader fileproj = new FileReader("project.txt");
         BufferedReader projfile = new BufferedReader(fileproj);
         String r = "";

         while ((r = projfile.readLine()) != null) {

         StringTokenizer projdata = new StringTokenizer(r, ",");
         String projectmanagerID = projdata.nextToken();
         String projID = projdata.nextToken();
         String projectname = projdata.nextToken();
         String date = projdata.nextToken().trim();

         if (project.equals(projectname)) {
         projid.setText(projID);
         id.setText(projectmanagerID);

         FileReader fileuser = new FileReader("userfile.txt");
         BufferedReader userfile = new BufferedReader(fileuser);
         String r1 = "";
         while ((r1 = userfile.readLine()) != null) {

         StringTokenizer userdatafile = new StringTokenizer(r1, ",");

         String ID = userdatafile.nextToken();
         String firstname = userdatafile.nextToken();
         String lastname = userdatafile.nextToken();
         String dept = userdatafile.nextToken().trim();
         String salary = userdatafile.nextToken();
         String phone = userdatafile.nextToken();
         String email = userdatafile.nextToken();
         String user = userdatafile.nextToken();
         String pass = userdatafile.nextToken();
         String fingerprint = userdatafile.nextToken();

         if (ID.equals(projectmanagerID)) {
         pm.setText(lastname + "," + firstname);
         break;
         }
         }
         }
         }

         } catch (Exception h) {
         h.printStackTrace();
         }    */
        // TODO add your handling code here:
    }//GEN-LAST:event_projActionPerformed

    private void fdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fdKeyTyped
        String date = fd.getText();
        int count = date.length();
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || count > 4) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_fdKeyTyped

    private void fdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fdKeyReleased
        String date = fd.getText();
        int count = date.length();
        if (count <= 5) {
            if (count == 2) {
                if (Integer.parseInt(date) < 25) {
                    fd.setText(date + ":");
                    fd.setBackground(Color.white);
                } else {
                    fd.setBackground(Color.red);
                }
            }
            if (count == 5) {
                String[] datearray = date.split(":");
                if (Integer.parseInt(datearray[1]) > 60) {
                    System.out.println("Minutes too big");
                    fd.setBackground(Color.red);
                } else {
                    fd.setBackground(Color.white);

                }
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_fdKeyReleased

    private void tdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdKeyTyped
        // TODO add your handling code here:
        String date = td.getText();
        int count = date.length();
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || count > 4) {
            evt.consume();
        }
    }//GEN-LAST:event_tdKeyTyped

    private void tdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdKeyReleased
        // TODO add your handling code here:
        String date = td.getText();
        int count = date.length();
        if (count <= 5) {
            if (count == 2) {
                if (Integer.parseInt(date) < 25) {
                    td.setText(date + ":");
                    td.setBackground(Color.white);
                } else {
                    td.setBackground(Color.red);
                }
            }
            if (count == 5) {
                String[] datearray = date.split(":");
                if (Integer.parseInt(datearray[1]) > 60) {
                    System.out.println("Minutes too big");
                    td.setBackground(Color.red);
                } else {
                    td.setBackground(Color.white);

                }
            }
        }
    }//GEN-LAST:event_tdKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dat;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JTextField fd;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel name;
    private com.toedter.calendar.JDateChooser ordat;
    private javax.swing.JTextField pm;
    private javax.swing.JComboBox proj;
    private javax.swing.JTextField projid;
    private javax.swing.JTextField sala;
    private javax.swing.JTextField td;
    private javax.swing.JLabel tim;
    private javax.swing.JTextField usid;
    // End of variables declaration//GEN-END:variables
}
