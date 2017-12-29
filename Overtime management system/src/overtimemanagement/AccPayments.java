/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.awt.Container;
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

import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static overtimemanagement.ReadWrite.fromtemptomaninfile;
import static overtimemanagement.ReadWrite.tableandfilesaveAccounts;

/**
 *
 * @author User
 */
public class AccPayments extends javax.swing.JFrame {

    public String date;
    DefaultTableModel Model;
    Connection conn = new DBconnect().connect();

    /**
     * Creates new form AddOvertimeInformation
     */
    public AccPayments() {
        initComponents();
        this.setExtendedState(AccPayments.MAXIMIZED_BOTH);
        Container c = getContentPane();
        JScrollPane scroll = new JScrollPane(c);
        setContentPane(scroll);

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
        } catch (Exception h) {
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
         // id.setText(ID);

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
                    if (todept.equals("Accounts")) {
                        Model.insertRow(Model.getRowCount(), new Object[]{userid, projectmanagerID, projID, daterequested, projectname, rspm.getString("Lastname") + "," + rspm.getString("Firstname"), overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, todept, status});
                    }
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

         if (todept.equals("Accounts")) {

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
         Model.insertRow(Model.getRowCount(), new Object[]{userid, projectmanagerID, projID, daterequested, projectname, pm, overtimedate, timefrom, timeto, hour + ":" + min, amounttopay, todept, status});

         userfile.close();
         break;
         }

         }

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
        paidbut.setSelected(false);
        unpaidbut.setSelected(false);
        save.setEnabled(false);
        print.setEnabled(false);
        scan.setEnabled(false);
        proj.setText("");
        tech.setText("");
        ftim.setText("");
        ttim.setText("");
        odat.setText("");
        sal.setText("");
        tw.setText("");
        ph.setText("");
        em.setText("");
        pm.setText("");
        pay.setText("");
        setsel.setSelected(true);
        abd.setEnabled(false);
        abd.setSelected(false);

    }

    private void notenabled() {
        pm.setEditable(false);
        proj.setEditable(false);
        tech.setEditable(false);
        odat.setEditable(false);
        ftim.setEditable(false);
        ttim.setEditable(false);
        tim.setEnabled(false);
        ph.setEditable(false);
        pay.setEditable(false);
        em.setEditable(false);
        tw.setEditable(false);
        id.setVisible(false);
        projid.setVisible(false);
        pmid.setVisible(false);
        setsel.setSelected(true);
        setsel.setVisible(false);
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
        l2 = new javax.swing.JLabel();
        dat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        paidbut = new javax.swing.JRadioButton();
        unpaidbut = new javax.swing.JRadioButton();
        odat = new javax.swing.JTextField();
        l5 = new javax.swing.JLabel();
        ftim = new javax.swing.JTextField();
        l6 = new javax.swing.JLabel();
        ttim = new javax.swing.JTextField();
        tw = new javax.swing.JTextField();
        sal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ph = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pay = new javax.swing.JTextField();
        proj = new javax.swing.JTextField();
        l7 = new javax.swing.JLabel();
        pm = new javax.swing.JTextField();
        l8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        l9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        abd = new javax.swing.JCheckBox();
        print = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        prev = new javax.swing.JTextArea();
        scan = new javax.swing.JButton();
        tim = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        res = new javax.swing.JTable();
        l10 = new javax.swing.JLabel();
        tech = new javax.swing.JTextField();
        setsel = new javax.swing.JRadioButton();
        id = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        projid = new javax.swing.JLabel();
        pmid = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        logout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        l2.setText("Date");

        dat.setText("Date");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Overtime Payments due");

        l3.setText("Payment due (dh)");

        l4.setText("Time Worked");

        buttonGroup1.add(paidbut);
        paidbut.setText("Paid");

        buttonGroup1.add(unpaidbut);
        unpaidbut.setText("Unpaid");

        l5.setText("From");

        l6.setText("To");

        jLabel11.setText("Salary");

        jLabel7.setText("Phone No.");

        jLabel8.setText("Email Address");

        l7.setText("Project");

        l8.setText("Project Manager");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        l9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        l9.setText("Overtime Information");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Personal Information");

        name.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        name.setText("Name of Accountant");

        abd.setText("Approved by Director");
        abd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abdMouseClicked(evt);
            }
        });

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        prev.setColumns(20);
        prev.setRows(5);
        prev.setText("Preview before Printing\n");
        jScrollPane1.setViewportView(prev);

        scan.setText("Scan");
        scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanActionPerformed(evt);
            }
        });

        tim.setText("Time");

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
            res.getColumnModel().getColumn(4).setPreferredWidth(110);
            res.getColumnModel().getColumn(4).setMaxWidth(110);
            res.getColumnModel().getColumn(5).setMinWidth(90);
            res.getColumnModel().getColumn(5).setPreferredWidth(90);
            res.getColumnModel().getColumn(5).setMaxWidth(90);
            res.getColumnModel().getColumn(6).setMinWidth(90);
            res.getColumnModel().getColumn(6).setPreferredWidth(90);
            res.getColumnModel().getColumn(6).setMaxWidth(90);
            res.getColumnModel().getColumn(7).setMinWidth(75);
            res.getColumnModel().getColumn(7).setPreferredWidth(75);
            res.getColumnModel().getColumn(7).setMaxWidth(75);
            res.getColumnModel().getColumn(8).setMinWidth(75);
            res.getColumnModel().getColumn(8).setPreferredWidth(75);
            res.getColumnModel().getColumn(8).setMaxWidth(75);
            res.getColumnModel().getColumn(9).setMinWidth(75);
            res.getColumnModel().getColumn(9).setPreferredWidth(75);
            res.getColumnModel().getColumn(9).setMaxWidth(75);
            res.getColumnModel().getColumn(10).setMinWidth(95);
            res.getColumnModel().getColumn(10).setPreferredWidth(95);
            res.getColumnModel().getColumn(10).setMaxWidth(95);
            res.getColumnModel().getColumn(11).setMinWidth(1);
            res.getColumnModel().getColumn(11).setPreferredWidth(1);
            res.getColumnModel().getColumn(11).setMaxWidth(1);
            res.getColumnModel().getColumn(12).setMinWidth(150);
            res.getColumnModel().getColumn(12).setPreferredWidth(150);
            res.getColumnModel().getColumn(12).setMaxWidth(150);
        }

        l10.setText("Technician");

        buttonGroup1.add(setsel);
        setsel.setText("jRadioButton1");

        id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        projid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pmid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        logout.setText("Logout");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projid, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(pmid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(l3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(176, 176, 176))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(l2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(odat, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(l8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGap(34, 34, 34)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(l7)
                                                        .addComponent(l10))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(proj, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(l4)
                                                    .addComponent(l5))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(ftim, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(l6)
                                                        .addGap(12, 12, 12)
                                                        .addComponent(ttim, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(tw, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(40, 40, 40)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(160, 160, 160)
                                        .addComponent(jLabel7))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(240, 240, 240)
                                        .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(180, 180, 180)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(jLabel8))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ph, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(130, 130, 130)
                                            .addComponent(jLabel2))
                                        .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(abd))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(scan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(paidbut)
                                        .addGap(82, 82, 82)
                                        .addComponent(unpaidbut))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(75, 75, 75)
                                            .addComponent(setsel))
                                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(188, 188, 188)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tech, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(l9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(303, 303, 303)
                        .addComponent(jLabel1)
                        .addGap(208, 208, 208)
                        .addComponent(tim, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ftim, pay, ttim, tw});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tim, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(dat))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(projid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pmid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(proj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(odat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ftim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ttim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(sal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(ph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(abd)
                        .addGap(17, 17, 17)
                        .addComponent(scan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paidbut)
                            .addComponent(unpaidbut))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(setsel)))
                .addGap(22, 22, 22))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ftim, odat, pay, pm, proj, ttim, tw});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {l2, l4, l5, l6, l7, l8});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        this.dispose();
        new Loginpage().setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_logoutMouseClicked

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printActionPerformed

    private void scanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scanActionPerformed

    private void resMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resMouseClicked
        unallowedit();
        //String requestdate = "" + Model.getValueAt(res.getSelectedRow(), 3);
        String userid = "" + Model.getValueAt(res.getSelectedRow(), 0);
        String projectmanagerid = "" + Model.getValueAt(res.getSelectedRow(), 1);
        String projectid = "" + Model.getValueAt(res.getSelectedRow(), 2);
        String project = "" + Model.getValueAt(res.getSelectedRow(), 4);
        String Technician = "" + Model.getValueAt(res.getSelectedRow(), 5);
        String overtimedate = "" + Model.getValueAt(res.getSelectedRow(), 6);
        String timestart = "" + Model.getValueAt(res.getSelectedRow(), 7);
        String timeend = "" + Model.getValueAt(res.getSelectedRow(), 8);
        String duration = "" + Model.getValueAt(res.getSelectedRow(), 9);
        String amttopay = "" + Model.getValueAt(res.getSelectedRow(), 10);
        String status = "" + Model.getValueAt(res.getSelectedRow(), 12);

        String[] Tech = Technician.split(",");
        String sqlor = "select * from users where Firstname=? && Lastname=?";

        try {
            PreparedStatement pa = conn.prepareStatement(sqlor);
            pa.setString(1, Tech[1]);
            pa.setString(2, Tech[0]);
            ResultSet rs = pa.executeQuery();

            while (rs.next()) {
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                String salary = rs.getString("Salary");

                ph.setText(phone);
                em.setText(email);
                sal.setText(salary);
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
         sal.setText(salary);

         }

         }
         } catch (Exception h) {
         h.printStackTrace();
         }*/
        String sqlpr = "select * from Project where Projectname=?";
        String sqlpm = "select * from users where autoid=?";

        try {
            PreparedStatement pa = conn.prepareStatement(sqlpr);
            System.out.println("1");
            pa.setString(1, project);
            ResultSet rs = pa.executeQuery();
            System.out.println("2");

            while (rs.next()) {
                String pmID=rs.getString("userid");
                projid.setText(rs.getString("Projectid"));
                pmid.setText(pmID);
                System.out.println(pmID);
                
                PreparedStatement pauser = conn.prepareStatement(sqlpm);
                pauser.setString(1, pmID);
                ResultSet rsuser = pauser.executeQuery();
                while (rsuser.next()) {
                          System.out.println("4");

                    String lastname= rsuser.getString("Lastname");
                    System.out.println("400"+lastname);
                    String firstname= rsuser.getString("Firstname");
                    pm.setText(lastname + "," + firstname);
                    
                }
                
            }
        } catch (Exception c) {
            c.printStackTrace();
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
                    pmid.setText(projectmanagerID);

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
        }*/
        id.setText(userid);
        //pm.setText(projectmanagerid);
        //projid.setText(projectid);
        proj.setText(project);
        tech.setText(Technician);
        odat.setText(overtimedate);
        ftim.setText(timestart);
        ttim.setText(timeend);
        tw.setText(duration);
        pay.setText(amttopay);
        print.setEnabled(true);
        save.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_resMouseClicked

    private void abdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abdMouseClicked
        if (abd.isSelected()) {
            scan.setEnabled(true);

        }// TODO add your handling code here:
    }//GEN-LAST:event_abdMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        String project = proj.getText();
        String status = "Waiting for payment";
        String todept = "Accounts";

        if (paidbut.isSelected()) {
            status = "Payment Complete";
            todept = "Technician";
        }

        String ID = id.getText();
        String projID = projid.getText();
        String promanid = pmid.getText();
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
        Model.setValueAt(promanid, res.getSelectedRow(), 1);
        Model.setValueAt(projID, res.getSelectedRow(), 2);

        Model.setValueAt(project, res.getSelectedRow(), 4);

        Model.setValueAt(todept, res.getSelectedRow(), 11);
        Model.setValueAt(status, res.getSelectedRow(), 12);

        tableandfilesaveAccounts(Model, ID);
        //fromtemptomaninfile();
        unallowedit();
        readrequestsintotableTECH();
    }//GEN-LAST:event_saveActionPerformed

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
            java.util.logging.Logger.getLogger(AccPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccPayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccPayments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox abd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dat;
    private javax.swing.JTextField em;
    private javax.swing.JTextField ftim;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel l10;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
    private javax.swing.JMenu logout;
    private javax.swing.JLabel name;
    private javax.swing.JTextField odat;
    private javax.swing.JRadioButton paidbut;
    private javax.swing.JTextField pay;
    private javax.swing.JTextField ph;
    private javax.swing.JTextField pm;
    private javax.swing.JLabel pmid;
    private javax.swing.JTextArea prev;
    private javax.swing.JButton print;
    private javax.swing.JTextField proj;
    private javax.swing.JLabel projid;
    private javax.swing.JTable res;
    private javax.swing.JTextField sal;
    private javax.swing.JButton save;
    private javax.swing.JButton scan;
    private javax.swing.JRadioButton setsel;
    private javax.swing.JTextField tech;
    private javax.swing.JLabel tim;
    private javax.swing.JTextField ttim;
    private javax.swing.JTextField tw;
    private javax.swing.JRadioButton unpaidbut;
    // End of variables declaration//GEN-END:variables
}
