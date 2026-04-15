package washingMachineGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.SimpleDateFormat;

public class repairHistoryGui extends JPanel{
    private JPanel cPanel, buttomPanel;
    private JButton saveBtn, deleteBtn;
    private JTable repairTable;
    private DefaultTableModel tableModel;
    private JLabel title;
    private JDateChooser dateChooser;
    private String LoggedInUser;


    public repairHistoryGui(String LoggedInUser) {
        setLayout(new BorderLayout());


        title = new JLabel("Repair History Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);


        String[] cols = {"Repair ID", "Machine ID", "Date", "Reporter", "Description", "Technician"};
        tableModel = new DefaultTableModel(cols, 0);
        repairTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(repairTable);
        add(scrollPane, BorderLayout.CENTER);

/*
        cPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        cPanel.add(new JLabel("Repair ID:"));
        JTextField repairIdField = new JTextField();
        cPanel.add(repairIdField);

        cPanel.add(new JLabel("Machine ID:"));
        JTextField machineIdField = new JTextField();
        cPanel.add(machineIdField);

        cPanel.add(new JLabel("Date:"));
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        cPanel.add(dateChooser);

        cPanel.add(new JLabel("Technician:"));
        cPanel.add(new JLabel(LoggedInUser));

        cPanel.add(new JLabel("Description:"));
        JTextField txtDescrip = new JTextField();
        cPanel.add(txtDescrip);

        add(cPanel, BorderLayout.SOUTH);
*/

        buttomPanel = new JPanel();
        saveBtn = new JButton("Show Repair");
        deleteBtn = new JButton("Delete Repair");
        buttomPanel.add(saveBtn);
        buttomPanel.add(deleteBtn);
        add(buttomPanel, BorderLayout.PAGE_END);


        saveBtn.addActionListener(e -> {

            try (Connection conn = washingmachine.DBConnection.getConnection()) {
                String sql = "SELECT repair_id, machine_id, reporter, technician, repair_date, repair_description FROM repair_history";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                tableModel.setRowCount(0);


                while (rs.next()) {
                    String id = rs.getString("repair_id");
                    String machine = rs.getString("machine_id");
                    String date = rs.getString("repair_date");
                    String description = rs.getString("repair_description");
                    String reporter = rs.getString("reporter");
                    String technician = rs.getString("technician");

                    tableModel.addRow(new Object[]{id, machine, date, reporter, description, technician});
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading repairs: " + ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = repairTable.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete");
            }
        });
    }
}
