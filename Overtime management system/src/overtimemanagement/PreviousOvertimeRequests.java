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
import static overtimemanagement.Fieldsdata.onlyint;

/**
 *
 * @author User
 */
public class PreviousOvertimeRequests extends javax.swing.JPanel {

    public String date;
    Connection conn = new DBconnect().connect();

    /**
     * Creates new form PreviousOvertimeRequests
     */
    public PreviousOvertimeRequests() {
        initComponents();

        dep.setEnabled(false);
        sal.setEnabled(false);
        ph.setEnabled(false);
        em.setEnabled(false);
        tim.setEnabled(false);
        res.setEnabled(false);
        id.setVisible(false);

        date = DateTime.getTodayDate();
        dat.setText(date);
        Timer t = new Timer(1000, new Clockc());
        t.start();

        String fname = "";
        String sql = "select * from Loggedin";
        try {
            PreparedStatement pa = conn.prepareStatement(sql);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                id.setText("" + rs.getInt("autoid"));
                name.setText(rs.getString("Firstname"));
                dep.setText(rs.getString("Department"));
                sal.setText(rs.getString("Salary"));
                ph.setText(rs.getString("Phone"));
                em.setText(rs.getString("Email"));
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
         id.setText(ID);
         dep.setText(dept);
         sal.setText(salary);
         ph.setText(phone);
         em.setText(email);
         }

         } catch (Exception h) {
         h.printStackTrace();
         }
         name.setText(fname);*/
        
        readrequestsintotable();

    }

    class Clockc implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            tim.setText(sdf.format(cal.getTime()));

        }

    }

    private void readrequestsintotable() {
        DefaultTableModel Model;
        Model = (DefaultTableModel) res.getModel();
        Model.setRowCount(0);
        String pm = "";

        String sqlor = "select * from OvertimeRequest where userid=?";
        String sql = "select * from users where autoid=?";

        try {
            PreparedStatement pa = conn.prepareStatement(sqlor);
            pa.setString(1, id.getText());
            ResultSet rs = pa.executeQuery();
            System.out.println("Done 1");
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
                papm.setString(1, projectmanagerID);
                ResultSet rspm = papm.executeQuery();
                            System.out.println("Done 2");

                while (rspm.next()) {
                    Model.insertRow(Model.getRowCount(), new Object[]{daterequested, projectname, rspm.getString("Lastname")+","+rspm.getString("Firstname") , overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, status});
                            System.out.println("Done 3");

                }
            }
        } catch (Exception c) {
            c.printStackTrace();
        }
        /*try {

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

         if (userid.equals(id.getText())) {
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

         if (projectmanagerID.equals(ID)) {
         pm = lastname + "," + firstname;
         break;
         }
         }
         Model.insertRow(Model.getRowCount(), new Object[]{daterequested, projectname, pm, overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, status});
         }
         }

         } catch (Exception h) {
         h.printStackTrace();
         }*/

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
        jScrollPane2 = new javax.swing.JScrollPane();
        res = new javax.swing.JTable();
        tim = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sal = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ph = new javax.swing.JTextField();
        dep = new javax.swing.JTextField();

        dat.setText("Date");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Previous Overtime Requests");

        res.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date Requested", "Project", "Project Manager", "Overtime Date", "Start", "End", "Duration", "Amount to be Paid", "Status"
            }
        ));
        jScrollPane2.setViewportView(res);
        if (res.getColumnModel().getColumnCount() > 0) {
            res.getColumnModel().getColumn(1).setResizable(false);
            res.getColumnModel().getColumn(1).setPreferredWidth(100);
            res.getColumnModel().getColumn(4).setMinWidth(85);
            res.getColumnModel().getColumn(4).setPreferredWidth(85);
            res.getColumnModel().getColumn(4).setMaxWidth(85);
            res.getColumnModel().getColumn(5).setMinWidth(85);
            res.getColumnModel().getColumn(5).setPreferredWidth(85);
            res.getColumnModel().getColumn(5).setMaxWidth(85);
            res.getColumnModel().getColumn(8).setResizable(false);
            res.getColumnModel().getColumn(8).setPreferredWidth(220);
        }

        tim.setText("Time");

        name.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        name.setText("Name Displayed Here");

        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Phone No.");

        sal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salKeyTyped(evt);
            }
        });

        jLabel11.setText("Salary(dh)");

        jLabel8.setText("Email Address");

        jLabel4.setText("Department");

        ph.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phKeyTyped(evt);
            }
        });

        dep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                depKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 605, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(174, 174, 174)
                .addComponent(tim, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(382, 382, 382)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(ph, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dep, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(sal))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dep, ph, sal});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dat)
                            .addComponent(tim)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void salKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salKeyTyped
        onlyint(evt);     // TODO add your handling code here:
    }//GEN-LAST:event_salKeyTyped

    private void phKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phKeyTyped
        onlyint(evt);
    }//GEN-LAST:event_phKeyTyped

    private void depKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_depKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_depKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dat;
    private javax.swing.JTextField dep;
    private javax.swing.JTextField em;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel name;
    private javax.swing.JTextField ph;
    private javax.swing.JTable res;
    private javax.swing.JTextField sal;
    private javax.swing.JLabel tim;
    // End of variables declaration//GEN-END:variables
}
