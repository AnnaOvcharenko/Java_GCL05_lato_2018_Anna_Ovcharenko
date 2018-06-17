/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class MainForm extends javax.swing.JFrame {

        public JList<String> alarmListShower;
        public AboutForm MyAboutFrame = new AboutForm();
        public AlarmForm showAlarmFrame= new AlarmForm();
        public AddAlarmFrame MyAddAlarmFrame = new AddAlarmFrame();
        public DeleteAlarmPopupMenu deleteAlarmPopupMenu = new DeleteAlarmPopupMenu();   
        
        class DeleteAlarmPopupMenu extends JPopupMenu
        {
            DeleteAlarmPopupMenu()
            {
                JMenuItem anItem = new JMenuItem("Delete Me!");
                add(anItem);
                
                DeleteAlarmPopupMenuListener MyDeleteAlarmPopupMenuListener = new DeleteAlarmPopupMenuListener();
                anItem.addActionListener(MyDeleteAlarmPopupMenuListener);
            }
        } 
        
        class DeleteAlarmPopupMenuListener implements ActionListener
        {
        @Override
        public void actionPerformed(ActionEvent e) {
            MyAddAlarmFrame.alarmListModel.removeElementAt(alarmListShower.getSelectedIndex());
            //MyAddAlarmFrame.alarmList.remove(alarmListShower.getSelectedIndex());
            alarmListShower.setModel(MyAddAlarmFrame.alarmListModel);
            //alarmListShower.remove(alarmListShower.getSelectedIndex());
        }
            
        }
        
        class ClockPanel extends JPanel implements Runnable
        {
            Thread t = new Thread(this);
            
            int panelHeight=500;
            int panelWidth=500;
            
            int secHandX;
            int secHandY;
            int minHandX;
            int minHandY;
            int hourHandX;
            int hourHandY;
            
            int hourHandLength=panelWidth/2-100;
            int minHandLength=panelWidth/2-70;
            int secHandLength=panelWidth/2-50;
            
           // Canvas clockCanvas = new Canvas();
            
            int bigDotDiabmener = 8;
            int smallDotDiabmener = 4;
            
            public ClockPanel()
            {
                setMaximumSize(new Dimension(panelHeight, panelWidth));
                setMinimumSize(new Dimension(panelHeight, panelWidth));
                setPreferredSize(new Dimension(panelHeight, panelWidth));
                setLayout(null);
                setResizable(true);
                t.start();
            }
            
            @Override
            public void run() {
                while (true)
                {
                    try {
                    int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
                    int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
                    int currentHour = Calendar.getInstance().get(Calendar.HOUR);
                    
                    secHandX = timeToLocation(currentSecond, secHandLength).x;
                    secHandY = timeToLocation(currentSecond, secHandLength).y;
                    minHandX = timeToLocation(currentMinute, minHandLength).x;
                    minHandY = timeToLocation(currentMinute, minHandLength).y;
                    hourHandX = timeToLocation(currentHour*5+getHourHandOffset(currentMinute), hourHandLength).x;
                    hourHandY = timeToLocation(currentHour*5+getHourHandOffset(currentMinute), hourHandLength).y;
                    
                    for (int i=0;i<MyAddAlarmFrame.alarmList.size();i++)
                        if (MyAddAlarmFrame.alarmList.get(i).hours==Calendar.getInstance().get(Calendar.HOUR) && MyAddAlarmFrame.alarmList.get(i).minutes==Calendar.getInstance().get(Calendar.MINUTE))
                            showAlarmFrame.setVisible(true);
                    repaint();                    
                    Thread.sleep(1);
                    
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            private int getHourHandOffset(int min) {
                return min / 12;
            }
            
            @Override
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.clearRect(0, 0, getWidth(), getHeight());

                g2.setColor(Color.black);
                
                for (int i = 0; i < 60; i++) {
      
                    Point dotCoordinates = timeToLocation(i, panelWidth/2-40);
                    g2.setColor((i <= Calendar.getInstance().get(Calendar.SECOND)) ? Color.red : Color.BLACK);

                    if (i % 5 == 0) {
                        g2.fillOval(dotCoordinates.x - (bigDotDiabmener / 2), dotCoordinates.y - (bigDotDiabmener / 2), bigDotDiabmener, bigDotDiabmener);
                        } 
                    else {
                        g2.fillOval(dotCoordinates.x - (smallDotDiabmener / 2), dotCoordinates.y - (smallDotDiabmener / 2), smallDotDiabmener, smallDotDiabmener);
                        }
                }
                g2.setColor(Color.blue);
                g2.drawLine(panelWidth / 2, panelHeight / 2, secHandX, secHandY);
                g2.drawLine(panelWidth / 2, panelHeight / 2, minHandX, minHandY);
                g2.drawLine(panelWidth / 2, panelHeight / 2, hourHandX, hourHandY);
            }
    
            private Point timeToLocation(int timeStep, int radius) {
                double t = 2 * Math.PI * (timeStep-15) / 60;
                int x = (int)(panelWidth / 2 + radius * Math.cos(t));
                int y = (int)(panelHeight / 2 + radius * Math.sin(t));
                return new Point(x, y);
            }        
        }
        
        class AddAlarmButton extends JButton
        {
            AddAlarmButton()
            {
                setPreferredSize(new Dimension(100, 50));
                setText("Add Alarm");
            }                  
        }  
        ActionListener AddAlarmButtonListener = new ActionListener()
               {
                @Override
                public void actionPerformed(ActionEvent e) {
                   MyAddAlarmFrame.setVisible(true);
                }
            };
            
       
        
    public MainForm() {
        super("Analog Clok");
        int frameWidth= 600;
        int frameHeight = 600;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //setMaximumSize(new Dimension(frameHeight, frameWidth));
        //setMinimumSize(new Dimension(frameHeight, frameWidth));
        setPreferredSize(new Dimension(frameHeight, frameWidth));
        setResizable(true);
        setLayout(new BorderLayout());
        
        ClockPanel clPan = new ClockPanel();
        
        AddAlarmButton addAlBut = new AddAlarmButton();
        addAlBut.addActionListener(AddAlarmButtonListener);
        
        AddAlarmFrame MyAddAlarmFrame = new AddAlarmFrame();
        MyAddAlarmFrame.setVisible(false);
        MyAddAlarmFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                
        alarmListShower = new JList<String>();
        alarmListShower.setModel(MyAddAlarmFrame.alarmListModel);
        
        alarmListShower.setPreferredSize(new Dimension(50, 100));
              //alarmListShower.setBackground(Color.red);
        
        MyAddAlarmFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                alarmListShower.setModel(MyAddAlarmFrame.alarmListModel);
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
                alarmListShower.setModel(MyAddAlarmFrame.alarmListModel);
            }
        });
        
        alarmListShower.addMouseListener(new MouseAdapter() {
            
        @Override
        public void mousePressed(MouseEvent e)  {check(e);}
        @Override
        public void mouseReleased(MouseEvent e) {check(e);}

        public void check(MouseEvent e) {
            if (e.isPopupTrigger()) { //if the event shows the menu
                alarmListShower.setSelectedIndex(alarmListShower.locationToIndex(e.getPoint())); //select the item
                deleteAlarmPopupMenu.show(alarmListShower, e.getX(), e.getY()); //and show the menu
            }
            }        
        });
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu programMenu = new JMenu("Program");
        JMenu alarmMenu = new JMenu("Alarm");
        JMenuItem aboutMenu = new JMenuItem("About");
        
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem clearAlarmsItem = new JMenuItem("Clear alarms");
        JMenuItem addAlarmItem = new JMenuItem("Add Alarm");
        
        alarmMenu.add(clearAlarmsItem);
        alarmMenu.add(addAlarmItem);
        programMenu.add(exitItem);
        
        addAlarmItem.addActionListener(AddAlarmButtonListener);
        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyAboutFrame.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        
        clearAlarmsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyAddAlarmFrame.alarmListModel.removeAllElements();
                alarmListShower.setModel(MyAddAlarmFrame.alarmListModel);
            }
        });
        
        menuBar.add(programMenu);
        menuBar.add(alarmMenu);
        menuBar.add(aboutMenu);
        
        add(menuBar, BorderLayout.PAGE_START);
        add(clPan, BorderLayout.CENTER);
        add(addAlBut, BorderLayout.PAGE_END);
        add(alarmListShower, BorderLayout.EAST);
        
        pack();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm MyMainForm =  new MainForm();
                MyMainForm.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
