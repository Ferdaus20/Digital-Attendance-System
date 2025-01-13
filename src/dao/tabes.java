/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Ferdaws Ahmed
 */
public class tabes { // Match the class name with the file name

    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;

        try {
            // Establish the database connection
            con = ConnectionProvider.getCon();
            // Initialize the Statement object
            st = con.createStatement();

            // Check and create the userdetails table
            if (!tableExists(st, "userdetails")) {
                st.executeUpdate("CREATE TABLE userdetails ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "name VARCHAR(255) NOT NULL, "
                        + "gender VARCHAR(50) NOT NULL, "
                        + "email VARCHAR(255) NOT NULL, "
                        + "contact VARCHAR(20) NOT NULL, "
                        + "uniquergid VARCHAR(100) NOT NULL, "
                        + "imagename VARCHAR(100), "
                        + "session VARCHAR(20) NOT NULL, "
                        + "year INT NOT NULL, "
                        + "semester VARCHAR(20) NOT NULL, "
                        + "subcode VARCHAR(20) NOT NULL"
                        + ")");
            }

            // Check and create the userattendance table
            if (!tableExists(st, "userattendance")) {
                st.executeUpdate("CREATE TABLE userattendance ("
                        + "userid INT not null, "
                        + "date DATE not null, "
                        + "checkin DATETIME, "
                        + "checkout DATETIME, "
                        + "classduration VARCHAR(100), "
                        + "subcode VARCHAR(20)"
                        + ")");
            }

            JOptionPane.showMessageDialog(null, "Tables Checked/Created Successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static boolean tableExists(Statement st, String tableName) throws Exception {
        // Check if the table exists in the database
        ResultSet resultSet = st.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
        return resultSet.next();
    }
}
