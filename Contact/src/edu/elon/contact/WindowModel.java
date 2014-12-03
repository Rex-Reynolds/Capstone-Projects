/**
 * WindowModel.java 1.0 Nov 23, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Start each Class or interface with a summary description line
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public class WindowModel extends Observable implements ModelInterface {

  private ArrayList<List<String>> results;
  /**
   * user name for connecting to the database
   */
  public static final String DATABASE_USER = "user";

  /**
   * password for connecting to the database
   */
  public static final String DATABASE_PASSWORD = "password";

  private JFrame error;

  public ArrayList<List<String>> DatabaseOperation(String query, String username, String password,
          String ipaddress, String dbName) {
    Connection conn = null;
    ResultSet rs = null;

    // arraylist of lists
    results = new ArrayList<List<String>>();

    // arraylists of strings of all the database entries
    List<String> firstNameResults = new ArrayList<String>();
    List<String> middleNameResults = new ArrayList<String>();
    List<String> lastNameResults = new ArrayList<String>();
    List<String> emailResults = new ArrayList<String>();
    List<String> majorResults = new ArrayList<String>();

    // make db connection and get the result set
    try {
      Properties connProperties = new java.util.Properties();
      connProperties.put(DATABASE_USER, username);
      connProperties.put(DATABASE_PASSWORD, password);
      conn = DriverManager.getConnection("jdbc:mysql://" + ipaddress + ":3306/" + dbName,
              connProperties);

      // create the java statement
      PreparedStatement preparedStmt = conn.prepareStatement(query);

      // execute the query, and get a java resultset
      if (query.toString().contains("SELECT")) {
        rs = preparedStmt.executeQuery();

        // iterate through the java resultset
        while (rs.next()) {
          firstNameResults.add(rs.getString(1));

          middleNameResults.add(rs.getString(2));

          lastNameResults.add(rs.getString(3));

          emailResults.add(rs.getString(4));

          majorResults.add(rs.getString(5));

          results.add(firstNameResults);
          results.add(middleNameResults);
          results.add(lastNameResults);
          results.add(emailResults);
          results.add(majorResults);

        }
      } else {
        preparedStmt.executeUpdate();

      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(error, "You did not correctly specify db parameters",
              "DB Settings", JOptionPane.ERROR_MESSAGE);

      System.out.println("YOU DID NOT SPECIFY DB SETTINGS");

    } finally {
      try {
        conn.close();
      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    setChanged();
    notifyObservers();
    return results;

  }

}
