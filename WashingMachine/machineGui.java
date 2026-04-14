package washingMachineGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class machineGui extends JPanel{

    private JTextField statusField, modelField, makeField, serialField;
    private JButton saveBtn;
    private JComboBox residenceDropdown, machineDropdown;
    private String LoggedInUser;

    public machineGui(String LoggedInUser) {
        this.LoggedInUser = LoggedInUser;

        setLayout(new GridLayout(6, 2, 1, 5));

        add(new JLabel("Residence Name:"));
        residenceDropdown = new JComboBox<>();
        loadResidencesForUser();
        add(residenceDropdown);

        add(new JLabel("Machine ID:"));
        machineDropdown = new JComboBox<>();
        add(machineDropdown);

        add(new JLabel("Machine make:"));
        makeField = new JTextField();
        makeField.setEditable(false);
        add(makeField);

        add(new JLabel("Machine Model:"));
        modelField = new JTextField();
        modelField.setEditable(false);
        add(modelField);

        add(new JLabel("SerialNo:"));
        serialField = new JTextField();
        serialField.setEditable(false);
        add(serialField);

        add(new JLabel("Status:"));
        statusField = new JTextField();
        statusField.setEditable(false);
        add(statusField);


        residenceDropdown.addActionListener(e -> loadMachinesForResidence());

        machineDropdown.addActionListener(e -> loadMachineDetails());

    }
    private void loadResidencesForUser(){
        if (LoggedInUser.equals("gammie@cput.ac.za") || LoggedInUser.equals("beven@cput.ac.za")) {
            residenceDropdown.addItem("Rise Student Living");
            residenceDropdown.addItem("New Market Junction");
            residenceDropdown.addItem("Freedom Square 1");
            residenceDropdown.addItem("Freedom Square 2");
            residenceDropdown.addItem("City Edge");
        } else if (LoggedInUser.equals("siya@cput.ac.za")) {
            residenceDropdown.addItem("City Edge");
            residenceDropdown.addItem("New Market Junction");
        } else if (LoggedInUser.equals("olivier@cput.ac.za")) {
            residenceDropdown.addItem("Rise Student Living");
            residenceDropdown.addItem("Freedom Square 1");
        } else {
            residenceDropdown.addItem("No Access");
        }
    }

    private void loadMachinesForResidence(){
        machineDropdown.removeAllItems();
        String selectedResidence = (String) residenceDropdown.getSelectedItem();

        if(selectedResidence == null || selectedResidence.equals("No access"))
            return;
        try(Connection conn = washingmachine.DBConnection.getConnection()) {
            String sql = "SELECT machine_id FROM machine WHERE residence_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, selectedResidence);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String machineId = rs.getString("machine_id");
                machineDropdown.addItem(machineId );


            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error in loading machines:" + ex.getMessage());
        }
    }

    private void loadMachineDetails(){
        String selectedMachine = (String) machineDropdown.getSelectedItem();
        if (selectedMachine == null)
            return;
        String machineId = selectedMachine.split("-")[0];

        try(Connection conn = washingmachine.DBConnection.getConnection()) {
            String sql = "SELECT machine_make, machine_model, serial_number, status FROM machine WHERE machine_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, machineId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                makeField.setText(rs.getString("machine_make"));
                modelField.setText(rs.getString("machine_model"));
                serialField.setText(rs.getString("serial_number"));
                statusField.setText(rs.getString("status"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error in loading machines:" + ex.getMessage());
        }
    }


}
