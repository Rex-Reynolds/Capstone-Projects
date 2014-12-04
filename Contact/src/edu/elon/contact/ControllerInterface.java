/**
 * ControllerInterface.java 1.0 Nov 23, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface class for the Controller
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public interface ControllerInterface {

  /**
   * Clears the Database of all entries
   * 
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return array list of arrays
   */
  public ArrayList<List<String>> clearDb(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * Connects to the database
   * 
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> connect(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * Exits the application
   */
  public void exit();

  /**
   * Goes to the previous entry in the database and displays it
   * 
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> previous(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * goes to the next item in the database and displays it
   * 
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> next(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * Adds an entry to the database
   * 
   * @param firstName
   * @param middleName
   * @param lastName
   * @param email
   * @param major
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> add(String firstName, String middleName, String lastName,
          String email, String major, String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * removes the entry from the database
   * 
   * @param tableName
   * @param firstName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> remove(String tableName, String firstName, String userName,
          String password, String ipAddress, String dbName);

  /**
   * updates the selected entry in the database
   * 
   * @param tableName
   * @param firstName
   * @param middleName
   * @param lastName
   * @param email
   * @param major
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> update(String tableName, String firstName, String middleName,
          String lastName, String email, String major, String userName, String password,
          String ipAddress, String dbName);

  /**
   * resets components for the view
   */
  public void resetComponents();

  /**
   * resets components for the view
   */
  public void resetAddComponents();

  /**
   * resets components for the view
   */
  public void resetRemoveComponents();

  /**
   * resets components for the view
   */
  public void resetClearComponents();

  /**
   * resets components for the view
   */
  public void setConnectComponents();

  /**
   * resets components for the view
   */
  public void resetOkComponents();

}
