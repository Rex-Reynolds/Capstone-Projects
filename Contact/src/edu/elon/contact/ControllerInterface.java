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
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return array list of arrays
   */
  public ArrayList<List<String>> ClearDb(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> Connect(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * 
   */
  public void Exit();

  /**
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> Previous(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * @param tableName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> Next(String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
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
  public ArrayList<List<String>> Add(String firstName, String middleName, String lastName,
          String email, String major, String tableName, String userName, String password,
          String ipAddress, String dbName);

  /**
   * @param tableName
   * @param firstName
   * @param userName
   * @param password
   * @param ipAddress
   * @param dbName
   * @return arraylist of arrays
   */
  public ArrayList<List<String>> Remove(String tableName, String firstName, String userName,
          String password, String ipAddress, String dbName);

  /**
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
  public ArrayList<List<String>> Update(String tableName, String firstName, String middleName,
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
