package washingMachineGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

public class incidentGui extends JPanel {

    private JPanel panelc, buttomPanel;
    private JLabel title, footerlbl;
    private JButton saveBtn, clearBtn, resolveBtn, reportBtn;
    private JTextField idField, dateField, descriField;
    private JTable incidentTable;
    private DefaultTableModel tableModel;
    private JComboBox machineDropdown;
    private JDateChooser dateChooser;
    private String LoggedInUser;

    public incidentGui(String loggedInUser) {
        this.LoggedInUser = loggedInUser;
        setLayout(new BorderLayout());

        //  Title
        title = new JLabel("Incident Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Incident List Table
        String[] columnNames = {"Incident ID","Machine ID", "Date", "Description", "Reporter"};
        tableModel = new DefaultTableModel(columnNames, 0);
        incidentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(incidentTable);
        add(scrollPane, BorderLayout.EAST);

        // Load unresolved incidents from DB
        try (Connection conn = washingmachine.DBConnection.getConnection()) {
            String sql = "SELECT incident_id, machine_id, incident_date, description, reporter FROM incident";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("incident_id"),
                        rs.getString("machine_id"),
                        rs.getString("incident_date"),
                        rs.getString("description"),
                        rs.getString("reporter")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading incidents: " + ex.getMessage());
        }
        resolveBtn = new JButton("Start incident");
        reportBtn = new JButton("Report");

        buttomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttomPanel.add(resolveBtn);
        buttomPanel.add(reportBtn);
        add(buttomPanel, BorderLayout.SOUTH);

        resolveBtn.addActionListener(e ->{
            int selectedRow = incidentTable.getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(this, "Select an incident to resolve!");
                return;
            }

            // Get incident values from table
            int incidentId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String machine = tableModel.getValueAt(selectedRow, 1).toString();
            String date = tableModel.getValueAt(selectedRow, 2).toString();
            String descr = tableModel.getValueAt(selectedRow, 3).toString();
            String reporter = tableModel.getValueAt(selectedRow, 4).toString();

            try (Connection conn = washingmachine.DBConnection.getConnection()) {
                conn.setAutoCommit(false);


                String insertSql = "INSERT INTO repair_history (repair_id, machine_id, repair_date, repair_description, reporter, technician) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insertSql);
                ps.setInt(1, incidentId);
                ps.setString(2, machine);
                ps.setString(3, date);
                ps.setString(4, descr);
                ps.setString(5, reporter);
                ps.setString(6, LoggedInUser);
                ps.executeUpdate();


                String deleteSql = "DELETE FROM incident WHERE incident_id = ?";
                PreparedStatement del = conn.prepareStatement(deleteSql);
                del.setInt(1, incidentId);
                del.executeUpdate();

                conn.commit();


                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Incident resolved");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error resolving incident: " + ex.getMessage());
            }
        });

        if (LoggedInUser.equals("gammie@cput.ac.za") || LoggedInUser.equals("beven@cput.ac.za") || LoggedInUser.equals("siya@cput.ac.za") || LoggedInUser.equals("olivier@cput.ac.za")) {
            // Incident
            panelc = new JPanel(new GridLayout(5, 2, 5, 5));
            panelc.setPreferredSize(new Dimension(400, 300));

            idField = new JTextField(10);

            descriField = new JTextField(10);

            //panelc.add(new JLabel("Incident ID:"));
            //panelc.add(idField);

            panelc.add(new JLabel("Machine ID:"));
            machineDropdown = new JComboBox<>();
            panelc.add(machineDropdown);

            loadMachineDetails();

            panelc.add(new JLabel("Date:"));
            dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("yyyy-MM-dd");
            panelc.add(dateChooser);

            panelc.add(new JLabel("Description:"));
            panelc.add(descriField);

            panelc.add(new JLabel("Reported By:"));
            panelc.add(new JLabel(loggedInUser));

            add(panelc, BorderLayout.CENTER);

            // Action Button
            buttomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            saveBtn = new JButton("Save Incident");
            clearBtn = new JButton("Clear");
            buttomPanel.add(saveBtn);
            buttomPanel.add(clearBtn);

            add(buttomPanel, BorderLayout.SOUTH);

            machineDropdown.addActionListener(e ->
                    machineDropdown.getSelectedItem());

            saveBtn.addActionListener(e -> {
                //String Id = idField.getText().trim();
                String machine = (String) machineDropdown.getSelectedItem();
                java.util.Date selectedDate = dateChooser.getDate();
                String date = "";
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = sdf.format(selectedDate);
                String descr = descriField.getText().trim();
                String reporter = loggedInUser.trim();

                if (/*Id.isEmpty() ||*/ date.isEmpty() || descr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields");
                    return;
                }

                try (java.sql.Connection conn = washingmachine.DBConnection.getConnection()) {
                    String sql = "INSERT INTO incident (machine_id, incident_date, description, reporter) VALUES (?, ?, ?, ?)";
                    java.sql.PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    //pstmt.setInt(1, Integer.parseInt(idField.getText()));
                    pstmt.setString(1, machine);
                    pstmt.setString(2, date);
                    pstmt.setString(3, descr);
                    pstmt.setString(4, reporter);

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        ResultSet keys = pstmt.getGeneratedKeys();
                        int generatedId = 0;
                        if (keys.next()) {
                            generatedId = keys.getInt(1);
                        }
                        tableModel.addRow(new Object[]{generatedId, machine, date, descr, reporter});
                        JOptionPane.showMessageDialog(this, "Incident call is logged");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving incident: " + ex.getMessage());
                }


                //idField.setText("");
                dateChooser.setDate(null);
                descriField.setText("");

            });

            clearBtn.addActionListener(e -> {
                int selectedRow = incidentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(this, "Select a row to delete");
                }
            });
        }
    }
    private void loadMachineDetails(){
        machineDropdown.removeAllItems();
        try(Connection conn = washingmachine.DBConnection.getConnection()) {
            String sql = "SELECT machine_id FROM machine";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String machineId = rs.getString("machine_id");
                machineDropdown.addItem(machineId );
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error in loading machines:" + ex.getMessage());
        }
    }
}
