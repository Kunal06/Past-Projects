/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static overtimemanagement.ReadWrite.fromtemptomaninfile;
import static overtimemanagement.ReadWrite.tableandfilesave;
import static overtimemanagement.ReadWrite.tableandfilesaveAdmin;
import static overtimemanagement.ReadWrite.userid;

/**
 *
 * @author User
 */
public class OvertimeRequestsAdmin extends javax.swing.JPanel {

    public String date;
    public String projID;
    public String ID;
    DefaultTableModel Model;
    Connection conn = new DBconnect().connect();

    /**
     * Creates new form OvertimeRequests
     */
    public OvertimeRequestsAdmin() {
        initComponents();
        unallowedit();
        notenabled();

        Model = (DefaultTableModel) res.getModel();
        date = DateTime.getTodayDate();
        dat.setText(date);
        Timer t = new Timer(1000, new Clockc());
        t.start();
        String sql = "select * from Loggedin";
        try {
            PreparedStatement pa = conn.prepareStatement(sql);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                name.setText(rs.getString("Firstname"));
                id.setText(rs.getString("autoid"));
            }
        } catch (Exception c) {
        }
        /*try {

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
         name.setText(fname);*/

        readrequestsintotableTECH();

    }

    private void readrequestsintotableTECH() {
        DefaultTableModel Model;
        Model = (DefaultTableModel) res.getModel();
        Model.setRowCount(0);
        //Model.insertRow(Model.getRowCount(), new Object[]{"", "", "","", "", "", "", "", "", "", "","", ""});
        String pm = "";

        String sqlor = "select * from OvertimeRequest ";
        String sql = "select * from users where autoid=?";

        try {
            PreparedStatement pa = conn.prepareStatement(sqlor);
            ResultSet rs = pa.executeQuery();

            while (rs.next()) {
                String userid = rs.getString("userid");
                String projectmanagerID = rs.getString("PMid");
                String projID = rs.getString("Projectid");
                String projectname = rs.getString("Projectname");
                String overtimedate = rs.getString("OvertimeDate");
                String timefrom = rs.getString("Timefrom");
                String timeto = rs.getString("Timeto");
                String hour = rs.getString("Hours");
                String min = rs.getString("Minutes");
                String daterequested = rs.getString("DateRequested");
                String amounttopay = rs.getString("AmountDue");
                String todept = rs.getString("NextDept");
                String status = rs.getString("Status");

                PreparedStatement papm = conn.prepareStatement(sql);
                papm.setString(1, userid);
                ResultSet rspm = papm.executeQuery();
                while (rspm.next()) {
                    if (todept.equals("Admin")) {
                        Model.insertRow(Model.getRowCount(), new Object[]{userid, projectmanagerID, projID, daterequested, projectname, rspm.getString("Lastname") + "," + rspm.getString("Firstname"), overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, todept, status});
                    }
                }
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
        /* try {

         FileReader fileproj = new FileReader("OvertimeRequest.txt");
         BufferedReader projfile = new BufferedReader(fileproj);
         String r = "";

         while ((r = projfile.readLine()) != null) {
         System.out.println("entered orfile");

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

         if (todept.equals("Admin")) {

         System.out.println("entered if");

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

         if (userid.equals(ID)) {
         pm = lastname + "," + firstname;
         break;
         }
         }
         Model.insertRow(Model.getRowCount(), new Object[]{userid, projectmanagerID, projID, daterequested, projectname, pm, overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, todept, status});
         userfile.close();
         }
         }

         projfile.close();

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

    private void unallowedit() {
        proj.removeAllItems();
        proj.addItem("Select Project");
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
        //Add project to project combo

        /*try {

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
        enabled();
        tech.setText("");
        ftim.setText("");
        ttim.setText("");
        odat.setText("");
        pm.setText("");
        tw.setText("");
        ph.setText("");
        em.setText("");

    }

    private void notenabled() {

        proj.setEnabled(false);
        pm.setEditable(false);
        tech.setEditable(false);
        odat.setEditable(false);
        ftim.setEditable(false);
        ttim.setEditable(false);
        tim.setEnabled(false);
        ph.setEditable(false);
        em.setEditable(false);
        tw.setEditable(false);
        id.setVisible(false);
        pmid.setVisible(false);
        projid.setVisible(false);

    }

    private void enabled() {

        proj.setEnabled(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        pm = new javax.swing.JTextField();
        l8 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        odat = new javax.swing.JTextField();
        l5 = new javax.swing.JLabel();
        ftim = new javax.swing.JTextField();
        l6 = new javax.swing.JLabel();
        ttim = new javax.swing.JTextField();
        tw = new javax.swing.JTextField();
        l2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        ph = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        proj = new javax.swing.JComboBox();
        tim = new javax.swing.JLabel();
        pmid = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        res = new javax.swing.JTable();
        l10 = new javax.swing.JLabel();
        tech = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        projid = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dat.setText("Date");
        add(dat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Overtime Requests");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        l7.setText("Project");
        add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, 20));
        add(pm, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 131, -1));

        l8.setText("Project Manager");
        add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, -1, 20));

        l4.setText("Time Worked");
        add(l4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        l9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        l9.setText("Overtime Information");
        add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, 40));
        add(odat, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 131, -1));

        l5.setText("From");
        add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, 20));
        add(ftim, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 60, -1));

        l6.setText("To");
        add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, -1, 20));
        add(ttim, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 60, -1));
        add(tw, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 60, -1));

        l2.setText("Date");
        add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, 20));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, 269));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Personal Information");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, -1, -1));
        add(ph, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 128, -1));

        jLabel7.setText("Phone No.");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 210, -1, -1));
        add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 128, -1));

        jLabel8.setText("Email Address");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, -1, -1));

        name.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        name.setText("Name of Admin");
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 180, 30));

        proj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projActionPerformed(evt);
            }
        });
        add(proj, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 137, -1));

        tim.setText("Time");
        add(tim, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 92, -1));

        pmid.setText("jLabel3");
        add(pmid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        id.setText("jLabel3");
        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        res.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "User ID", "PM ID", "Proj ID", "Date Requested", "Project", "Technician", "Overtime Date", "Start", "End", "Duration", "Amount due(dh)", "Next Dept", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        res.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(res);
        if (res.getColumnModel().getColumnCount() > 0) {
            res.getColumnModel().getColumn(0).setMinWidth(1);
            res.getColumnModel().getColumn(0).setPreferredWidth(1);
            res.getColumnModel().getColumn(0).setMaxWidth(1);
            res.getColumnModel().getColumn(1).setMinWidth(1);
            res.getColumnModel().getColumn(1).setPreferredWidth(1);
            res.getColumnModel().getColumn(1).setMaxWidth(1);
            res.getColumnModel().getColumn(2).setMinWidth(1);
            res.getColumnModel().getColumn(2).setPreferredWidth(1);
            res.getColumnModel().getColumn(2).setMaxWidth(1);
            res.getColumnModel().getColumn(3).setMinWidth(90);
            res.getColumnModel().getColumn(3).setPreferredWidth(90);
            res.getColumnModel().getColumn(3).setMaxWidth(90);
            res.getColumnModel().getColumn(4).setMinWidth(110);
            res.getColumnModel().getColumn(4).setPreferredWidth(115);
            res.getColumnModel().getColumn(4).setMaxWidth(130);
            res.getColumnModel().getColumn(5).setMinWidth(100);
            res.getColumnModel().getColumn(5).setPreferredWidth(100);
            res.getColumnModel().getColumn(5).setMaxWidth(110);
            res.getColumnModel().getColumn(6).setMinWidth(90);
            res.getColumnModel().getColumn(6).setPreferredWidth(90);
            res.getColumnModel().getColumn(6).setMaxWidth(110);
            res.getColumnModel().getColumn(7).setResizable(false);
            res.getColumnModel().getColumn(7).setPreferredWidth(60);
            res.getColumnModel().getColumn(8).setResizable(false);
            res.getColumnModel().getColumn(8).setPreferredWidth(60);
            res.getColumnModel().getColumn(9).setMinWidth(60);
            res.getColumnModel().getColumn(9).setPreferredWidth(60);
            res.getColumnModel().getColumn(9).setMaxWidth(60);
            res.getColumnModel().getColumn(10).setPreferredWidth(100);
            res.getColumnModel().getColumn(11).setMinWidth(1);
            res.getColumnModel().getColumn(11).setPreferredWidth(1);
            res.getColumnModel().getColumn(11).setMaxWidth(1);
            res.getColumnModel().getColumn(12).setMinWidth(170);
            res.getColumnModel().getColumn(12).setPreferredWidth(170);
            res.getColumnModel().getColumn(12).setMaxWidth(170);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 970, 290));

        l10.setText("Technician");
        add(l10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, -1, 20));
        add(tech, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 131, -1));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 103, 44));

        projid.setText("jLabel3");
        add(projid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void resMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseClicked
        unallowedit();
        String requestdate = "" + Model.getValueAt(res.getSelectedRow(), 3);
        String project = "" + Model.getValueAt(res.getSelectedRow(), 4);
        String Technician = "" + Model.getValueAt(res.getSelectedRow(), 5);
        String overtimedate = "" + Model.getValueAt(res.getSelectedRow(), 6);
        String timestart = "" + Model.getValueAt(res.getSelectedRow(), 7);
        String timeend = "" + Model.getValueAt(res.getSelectedRow(), 8);
        String duration = "" + Model.getValueAt(res.getSelectedRow(), 9);
        String amttopay = "" + Model.getValueAt(res.getSelectedRow(), 10);
        String status = "" + Model.getValueAt(res.getSelectedRow(), 12);

        String sqlor = "select * from users where Firstname=? && Lastname=?";

        String[] Tech = Technician.split(",");
        try {
            PreparedStatement pa = conn.prepareStatement(sqlor);
            pa.setString(1, Tech[1]);
            pa.setString(2, Tech[0]);
            ResultSet rs = pa.executeQuery();

            while (rs.next()) {
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");

                ph.setText(phone);
                em.setText(email);
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
        /*try {
         FileReader fileuser = new FileReader("userfile.txt");
         BufferedReader userfile = new BufferedReader(fileuser);
         String r1 = "";
         while ((r1 = userfile.readLine()) != null) {

         StringTokenizer userdatafile = new StringTokenizer(r1, ",");

         String ID = userdatafile.nextToken();
         if (ID.equals("")) {
         break;
         }
         String firstname = userdatafile.nextToken();
         String lastname = userdatafile.nextToken();
         String dept = userdatafile.nextToken().trim();
         String salary = userdatafile.nextToken();
         String phone = userdatafile.nextToken();
         String email = userdatafile.nextToken();
         String user = userdatafile.nextToken();
         String pass = userdatafile.nextToken();
         String fingerprint = userdatafile.nextToken();
         if ((Tech[0].trim()).equals(lastname) && (Tech[1].trim()).equals(firstname)) {
         ph.setText(phone);
         em.setText(email);
         }

         }
         } catch (Exception h) {
         h.printStackTrace();
         }*/
        tech.setText(Technician);
        odat.setText(overtimedate);
        ftim.setText(timestart);
        ttim.setText(timeend);
        tw.setText(duration);

        // TODO add your handling code here:
    }//GEN-LAST:event_resMouseClicked

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
                pmid.setText(projectmanagerid);
                projid.setText(projectid);
                
                PreparedStatement use = conn.prepareStatement(users);
                use.setString(1, projectmanagerid);
                ResultSet rusers = use.executeQuery();
                while (rusers.next()) {

                    pm.setText(rusers.getString("Lastname") + "," + rusers.getString("Firstname"));

                }

            }

        } catch (Exception c) {
        }
        /* try {

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
         }   */      // TODO add your handling code here:
    }//GEN-LAST:event_projActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        String project = "" + proj.getSelectedItem();
        String status = "Pending review from Project Manager";
        String todept = "Project Manager";

        if (project.equals("Other Project")) {
            status = "Pending review from Admin";
            todept = "Admin";
        }

        String ID = id.getText();
        String pmID = pmid.getText();
        String projID= projid.getText();
        String technician = tech.getText();
        String overtimedate = odat.getText();
        String timestart = ftim.getText();
        String timeend = ttim.getText();
        String timeworked = tw.getText();
        String email = em.getText();
        Email useremail = new Email(email);
        String valid = useremail.validateEmailAddress();
        String phone = ph.getText();

        //ADD back to row in table
        Model.setValueAt(pmID, res.getSelectedRow(), 1);
        Model.setValueAt(projID, res.getSelectedRow(), 2);
        Model.setValueAt(project, res.getSelectedRow(), 4);

        Model.setValueAt(todept, res.getSelectedRow(), 11);
        Model.setValueAt(status, res.getSelectedRow(), 12);

        tableandfilesaveAdmin(Model, ID);
        //fromtemptomaninfile();
        unallowedit();
        readrequestsintotableTECH();

    }//GEN-LAST:event_saveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dat;
    private javax.swing.JTextField em;
    private javax.swing.JTextField ftim;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JLabel name;
    private javax.swing.JTextField odat;
    private javax.swing.JTextField ph;
    private javax.swing.JTextField pm;
    private javax.swing.JLabel pmid;
    private javax.swing.JComboBox proj;
    private javax.swing.JLabel projid;
    private javax.swing.JTable res;
    private javax.swing.JButton save;
    private javax.swing.JTextField tech;
    private javax.swing.JLabel tim;
    private javax.swing.JTextField ttim;
    private javax.swing.JTextField tw;
    // End of variables declaration//GEN-END:variables
}
