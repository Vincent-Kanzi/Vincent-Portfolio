package washingMachineGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import washingmachine.DBConnection;
import washingMachineGui.mainMenuGui;

public class loginGui extends JFrame {
    private JPanel panelC;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton loginBtn;
    private JLabel title;
    private String LoggedInUser;


    public loginGui(String LoggedInUser) {
        this.LoggedInUser = LoggedInUser;

        setTitle("Washing Machine System");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        title = new JLabel("Washing Machine Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Center panel
        panelC = new JPanel(new GridLayout(3, 2, 10, 10));
        panelC.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelC.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panelC.add(usernameField);

        panelC.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panelC.add(passwordField);

//        form.add(new JLabel("Role:"));
//        roleBox = new JComboBox<>(new String[]{"Residence Parent", "Technician"});
//        form.add(roleBox);

        add(panelC, BorderLayout.CENTER);

        // Bottom button
        loginBtn = new JButton("Login");
        add(loginBtn, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
    }


    private void authenticate() {
        String username = usernameField.getText();
        usernameField.setText("");
        String password = new String(passwordField.getPassword());
        passwordField.setText("");


        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
               // JOptionPane.showMessageDialog(this, "Login Successful "/*+ username*/);
                String LoggedInUser = username;
                SwingUtilities.invokeLater(() -> new mainMenuGui(LoggedInUser).setVisible(true));
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}
