package washingmachine;

import java.sql.Connection;
import javax.swing.*;
import  washingMachineGui.loginGui;

public class Main {
    public static void main(String[] args) {

        try {
            if (DBConnection.getConnection() != null) {
                System.out.println("Connected to database");

                // Switch to  GUI
                SwingUtilities.invokeLater(() -> new loginGui("username").setVisible(true));
            }
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }
}


