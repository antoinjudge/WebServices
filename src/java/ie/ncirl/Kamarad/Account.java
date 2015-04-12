/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ncirl.Kamarad;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import java.sql.*;

/**
 *
 * @author Antoin
 */
@WebService(serviceName = "Account")
@Stateless()
public class Account {

    private String phoneNumber;
    private String accountName;
    private double topUpAmt;
    private double balance;
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/KamaradCustomers";

    //  Database credentials
    static final String USER = "antoin";
    static final String PASS = "antoin";

    
    
    @WebMethod(operationName = "account")
    public String Account(String numberIn, String nameIn) {
        phoneNumber = numberIn;
        accountName = nameIn;
        balance = 0;
        Connection conn = null;
        Statement stmt = null;
        try{
      //Register JDBC driver
      Class.forName("org.apache.derby.jdbc.ClientDriver");

      // Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      // update database
      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();
      
      String sql = "INSERT INTO CUSTOMERS " + "VALUES ("+numberIn+", '"+nameIn+"',"+balance+","+0+","+0+","+0+","+0+","+0+","+0+","+0+")";
      stmt.executeUpdate(sql);
      System.out.println("Inserted records into the table...");

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Done!!");

        return "Account created for Phone Number  : " + numberIn + " Customer Name : " + nameIn + " Initial Balance :" + balance;
    }

    
}
