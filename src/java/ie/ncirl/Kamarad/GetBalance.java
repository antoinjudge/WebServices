/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ncirl.Kamarad;

import static ie.ncirl.Kamarad.Account.DB_URL;
import static ie.ncirl.Kamarad.TopUpWS.DB_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Antoin
 */
@WebService(serviceName = "GetBalance")
@Stateless()
public class GetBalance {

    private String phoneNumber;
    private double balance;
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/KamaradCustomers";

    //  Database credentials
    static final String USER = "antoin";
    static final String PASS = "antoin";

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "balance")
    public String balance(String numberIn) {
        phoneNumber = numberIn;
        Connection conn = null;
        Statement stmt = null;
        try {
            //Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            // update database
            System.out.println("Checking Customers details");
            stmt = conn.createStatement();

            String sql = "SELECT balance FROM CUSTOMERS WHERE PHONENUMBER = " + numberIn + "";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String myBalance = rs.getString(1);
                System.out.println(myBalance);
            }
            

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Done!!" + balance);

        return "Your balance is   " + balance;
    }
}
