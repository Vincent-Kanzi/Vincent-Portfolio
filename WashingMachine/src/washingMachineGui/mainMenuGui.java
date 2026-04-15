package washingMachineGui;

import javax.swing.*;
import java.awt.*;

public class mainMenuGui extends JFrame{
    private JPanel topPanel, contentPanel;
    private JButton residenceBtn, machineBtn, repairBtn, incidentBtn, logoutBtn;
    private CardLayout cardLayout;
    private  String LoggedInUser;


    public mainMenuGui(String LoggedInUser) {
        this.LoggedInUser = LoggedInUser;
        setTitle("Washing Machine Residence Maintenance");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        residenceBtn = new JButton("HOME");
        machineBtn = new JButton("Manage Machines");
        repairBtn = new JButton("Manage Repairs");
        incidentBtn = new JButton("Manage Incidents");
        logoutBtn = new JButton("Logout");

        // --- Logout Icon Button ---
        //ImageIcon logoutIcon = new ImageIcon("C:\\Users\\CPUT\\Pictures\\JavaPics\\logout4.jpg"); // use your own path
        //logoutBtn = new JButton(logoutIcon);
        logoutBtn.setSize(5, 5);
        logoutBtn.setToolTipText("Logout");
        logoutBtn.setBorderPainted(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setFocusPainted(false);

        if(LoggedInUser.equals("gammie@cput.ac.za") || LoggedInUser.equals("beven@cput.ac.za")){
            topPanel.add(residenceBtn);
            topPanel.add(machineBtn);
            topPanel.add(repairBtn);
            topPanel.add(incidentBtn);
            topPanel.add(logoutBtn);
        }else if(LoggedInUser.equals("siya@cput.ac.za") || LoggedInUser.equals("olivier@cput.ac.za")){
            topPanel.add(machineBtn);
            topPanel.add(repairBtn);
            topPanel.add(incidentBtn);
            topPanel.add(logoutBtn);
        }else if(LoggedInUser.equals("kanziv@cput.ac.za")|| LoggedInUser.equals("mpehlev@cput.ac.za")){
            topPanel.add(incidentBtn);
            topPanel.add(logoutBtn);
        };

        // Add logout button at the far right
        topPanel.add(Box.createHorizontalStrut(100));
        topPanel.add(logoutBtn);

        add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout) {
            private Image backgroundImage = new ImageIcon("C:\\Users\\CPUT\\Pictures\\JavaPics\\washing3.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // panels for GUIs
        JPanel residencePanel = new JPanel();
        residencePanel.setOpaque(false);


        machineGui machinePanel = new machineGui(LoggedInUser);
        machinePanel.setOpaque(false);


        repairHistoryGui repairPanel = new repairHistoryGui(LoggedInUser);
        repairPanel.setOpaque(false);


        incidentGui incidentPanel = new incidentGui(LoggedInUser);
        incidentPanel.setOpaque(false);


        // Add them to CardLayout with keys
        contentPanel.add(residencePanel, "RESIDENCE");
        contentPanel.add(machinePanel, "MACHINE");
        contentPanel.add(repairPanel, "REPAIR");
        contentPanel.add(incidentPanel, "INCIDENT");

        add(contentPanel, BorderLayout.CENTER);

        // --- Button Actions to Switch Panels ---
        residenceBtn.addActionListener(e -> cardLayout.show(contentPanel, "RESIDENCE"));
        machineBtn.addActionListener(e -> cardLayout.show(contentPanel, "MACHINE"));
        repairBtn.addActionListener(e -> cardLayout.show(contentPanel, "REPAIR"));
        incidentBtn.addActionListener(e -> cardLayout.show(contentPanel, "INCIDENT"));

        // --- Logout Button Action ---
        logoutBtn.addActionListener(e -> {
            dispose();
            new loginGui(LoggedInUser).setVisible(true);
        });


    }
}
