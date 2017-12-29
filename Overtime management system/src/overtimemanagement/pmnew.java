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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static overtimemanagement.ReadWrite.SaveEdittoORFilefromTable;
import static overtimemanagement.ReadWrite.fromtemptomaninfile;
import static overtimemanagement.ReadWrite.loadloggedinuser;
import static overtimemanagement.ReadWrite.tableandfilesave;
/**
 *
 * @author User
 */
public class pmnew extends javax.swing.JFrame {
    public String date;
    DefaultTableModel Model;
    /**
     * Creates new form pmnew
     */
    public pmnew() {
        initComponents();
    unallowedit();
        notenabled();

        tim.setEnabled(false);
        Model = (DefaultTableModel) res.getModel();
        date = DateTime.getTodayDate();
        dat.setText(date);
        Timer t = new Timer(1000, new Clockc());
        t.start();
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
                id.setText(ID);

            }

        } catch (Exception h) {
            h.printStackTrace();
        }
        name.setText(fname);

        readrequestsintotableTECH();

    }

    private void readrequestsintotableTECH() {
        DefaultTableModel Model;
        Model = (DefaultTableModel) res.getModel();
        Model.setRowCount(0);
        //Model.insertRow(Model.getRowCount(), new Object[]{"", "", "","", "", "", "", "", "", "", "","", ""});
        String pm = "";

        try {

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

                if(todept.equals("Project Manager")){
                if (projectmanagerID.equals(id.getText())) {
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
            }
            projfile.close();

        } catch (Exception h) {
            h.printStackTrace();
        }

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
        app.setSelected(false);
        dec.setSelected(false);
        save.setEnabled(false);
        app.setEnabled(false);
        dec.setEnabled(false);
        proj.setText("");
        tech.setText("");
        ftim.setText("");
        ttim.setText("");
        odat.setText("");
        tw.setText("");
        ph.setText("");
        em.setText("");
        setsel.setSelected(true);

    }

    private void notenabled() {
        proj.setEditable(false);
        tech.setEditable(false);
        odat.setEditable(false);
        ftim.setEditable(false);
        ttim.setEditable(false);
        tim.setEnabled(false);
        ph.setEditable(false);
        em.setEditable(false);
        tw.setEditable(false);
        id.setVisible(false);
        setsel.setSelected(true);
        setsel.setVisible(false);
    }

    private void allowedit() {
        app.setSelected(false);
        dec.setSelected(false);
        save.setEnabled(true);
        app.setEnabled(true);
        dec.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        name = new javax.swing.JLabel();
        dat = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        tim = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        res = new javax.swing.JTable();
        l9 = new javax.swing.JLabel();
        app = new javax.swing.JRadioButton();
        id = new javax.swing.JLabel();
        dec = new javax.swing.JRadioButton();
        odat = new javax.swing.JTextField();
        l5 = new javax.swing.JLabel();
        ftim = new javax.swing.JTextField();
        l6 = new javax.swing.JLabel();
        ttim = new javax.swing.JTextField();
        setsel = new javax.swing.JRadioButton();
        tw = new javax.swing.JTextField();
        l2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        ph = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        proj = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        tech = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        name.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        name.setText("Name of Project Manager");

        dat.setText("Date");

        l8.setText("Technician");

        tim.setText("Time");

        l4.setText("Time Worked");

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
        jScrollPane2.setViewportView(res);

        l9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        l9.setText("Overtime Information");

        buttonGroup1.add(app);
        app.setText("Approve");

        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(dec);
        dec.setText("Decline");

        l5.setText("From");

        l6.setText("To");

        buttonGroup1.add(setsel);
        setsel.setText("jRadioButton1");

        l2.setText("Date");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Personal Information");

        jLabel7.setText("Phone No.");

        jLabel8.setText("Email Address");

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Unapproved Overtime Requests");

        l7.setText("Project");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu3.setText("Logout");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addGap(238, 238, 238)
                        .addComponent(tim, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(l9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(app)
                                .addGap(58, 58, 58)
                                .addComponent(dec))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(l2)
                                        .addGap(18, 18, 18)
                                        .addComponent(odat))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(l4)
                                                .addGap(12, 12, 12)
                                                .addComponent(tw, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(l5)
                                                .addGap(18, 18, 18)
                                                .addComponent(ftim, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(12, 12, 12)
                                        .addComponent(l6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(l7)
                                            .addComponent(l8))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tech)
                                            .addComponent(proj))))
                                .addGap(18, 18, 18)
                                .addComponent(ttim, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(ph, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(setsel)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(188, 188, 188))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dat)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1))
                            .addComponent(tim))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2))
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(proj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(odat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ttim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(l4))
                                    .addComponent(tw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(app)
                                    .addComponent(dec))
                                .addGap(18, 18, 18)
                                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel7))
                                            .addComponent(ph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel8))
                                            .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(setsel)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addGap(19, 19, 19)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        this.dispose();
        new Loginpage().setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void resMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseClicked
        unallowedit();
        allowedit();
        String requestdate = "" + Model.getValueAt(res.getSelectedRow(), 3);
        String project = "" + Model.getValueAt(res.getSelectedRow(), 4);
        String Technician = "" + Model.getValueAt(res.getSelectedRow(), 5);
        String overtimedate = "" + Model.getValueAt(res.getSelectedRow(), 6);
        String timestart = "" + Model.getValueAt(res.getSelectedRow(), 7);
        String timeend = "" + Model.getValueAt(res.getSelectedRow(), 8);
        String duration = "" + Model.getValueAt(res.getSelectedRow(), 9);
        String amttopay = "" + Model.getValueAt(res.getSelectedRow(), 10);
        String status = "" + Model.getValueAt(res.getSelectedRow(), 12);

        String[] Tech = Technician.split(",");
        try {
            FileReader fileuser = new FileReader("userfile.txt");
            BufferedReader userfile = new BufferedReader(fileuser);
            String r1 = "";
            while ((r1 = userfile.readLine()) != null) {

            StringTokenizer userdatafile = new StringTokenizer(r1, ",");

                String ID = userdatafile.nextToken();
                if(ID.equals("")){
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
        }
        proj.setText(project);
        tech.setText(Technician);
        odat.setText(overtimedate);
        ftim.setText(timestart);
        ttim.setText(timeend);
        tw.setText(duration);

        // TODO add your handling code here:
    }//GEN-LAST:event_resMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        String status = "";
        String todept = "";
        if (app.isSelected()) {
            status = "Waiting for payment";
            todept = "Accounts";

        } else if (dec.isSelected()) {
            status = "Declined by Project Manager";
            todept = "Technician";

        } else {
            status = "Pending review from Project Manager";
            todept = "Project Manager";
        }

        String ID = id.getText();
        String project = proj.getText();
        String projectmanager = tech.getText();
        String overtimedate = odat.getText();
        String timestart = ftim.getText();
        String timeend = ttim.getText();
        String timeworked = tw.getText();
        String email = em.getText();
        Email useremail = new Email(email);
        String valid = useremail.validateEmailAddress();
        String phone = ph.getText();

        //ADD back to row in table
        Model.setValueAt(project, res.getSelectedRow(), 4);
        Model.setValueAt(projectmanager, res.getSelectedRow(), 5);
        Model.setValueAt(overtimedate, res.getSelectedRow(), 6);
        Model.setValueAt(timestart, res.getSelectedRow(), 7);
        Model.setValueAt(timeend, res.getSelectedRow(), 8);
        Model.setValueAt(timeworked, res.getSelectedRow(), 9);
        Model.setValueAt(todept, res.getSelectedRow(), 11);
        Model.setValueAt(status, res.getSelectedRow(), 12);

        tableandfilesave(Model,ID);
        fromtemptomaninfile();
        unallowedit();
        readrequestsintotableTECH();
        

    }//GEN-LAST:event_saveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pmnew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pmnew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pmnew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pmnew.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pmnew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton app;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dat;
    private javax.swing.JRadioButton dec;
    private javax.swing.JTextField em;
    private javax.swing.JTextField ftim;
    private javax.swing.JLabel id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
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
    private javax.swing.JTextField proj;
    private javax.swing.JTable res;
    private javax.swing.JButton save;
    private javax.swing.JRadioButton setsel;
    private javax.swing.JTextField tech;
    private javax.swing.JLabel tim;
    private javax.swing.JTextField ttim;
    private javax.swing.JTextField tw;
    // End of variables declaration//GEN-END:variables
}
