/**
 * WindowController.java 1.0 Nov 23, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the MVC
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public class WindowController implements ControllerInterface {
  private ModelInterface model;
  private WindowView view;
  private ArrayList<List<String>> results;
  /**
   * user name for connecting to the database
   */
  public static final String DATABASE_USER = "user";

  /**
   * password for connecting to the database
   */
  public static final String DATABASE_PASSWORD = "password";

  /**
   * @param aModel
   */
  public WindowController(ModelInterface aModel) {
    this.model = aModel;
    view = new WindowView(this, model);
    view.createView();

  }

  public void exit() {
    System.exit(0);

  }

  public ArrayList<List<String>> previous(String tableName, String userName, String password,
          String ipAddress, String dbName) {
    results = model.databaseOperation("SELECT * FROM " + tableName, userName, password, ipAddress,
            dbName);
    return results;
  }

  public ArrayList<List<String>> next(String tableName, String userName, String password,
          String ipAddress, String dbName) {

    results = model.databaseOperation("SELECT * FROM " + tableName, userName, password, ipAddress,
            dbName);
    return results;

  }

  public ArrayList<List<String>> clearDb(String tableName, String userName, String password,
          String ipAddress, String dbName) {
    results = model.databaseOperation("DELETE FROM " + tableName, userName, password, ipAddress,
            dbName);

    return results;

  }

  public ArrayList<List<String>> connect(String tableName, String userName, String password,
          String ipAddress, String dbName) {
    results = model.databaseOperation("SELECT * FROM " + tableName, userName, password, ipAddress,
            dbName);

    return results;

  }

  public ArrayList<List<String>> add(String firstName, String middleName, String lastName,
          String email, String major, String tableName, String userName, String password,
          String ipAddress, String dbName) {

    results = model.databaseOperation("INSERT INTO " + tableName
            + "(First_Name, Middle_Name, Last_name, Email, Major) VALUES('" + firstName + "','"
            + middleName + "','" + lastName + "','" + email + "','" + major + "');", userName,
            password, ipAddress, dbName);

    return results;

  }

  public ArrayList<List<String>> remove(String tableName, String lastName, String userName,
          String password, String ipAddress, String dbName) {
    results = model.databaseOperation("DELETE FROM " + tableName + " WHERE Last_Name= '" + lastName
            + "'", userName, password, ipAddress, dbName);

    return results;

  }

  public ArrayList<List<String>> update(String tableName, String firstName, String middleName,
          String lastName, String email, String major, String userName, String password,
          String ipAddress, String dbName) {
    results = model.databaseOperation("UPDATE " + tableName + " SET First_Name= '" + firstName
            + "',Middle_Name='" + middleName + "',Last_Name='" + lastName + "',Email='" + email
            + "',Major='" + major + "' WHERE Email = '" + email + "';", userName, password,
            ipAddress, dbName);

    return results;

  }

  public void resetComponents() {
    view.resetComponents();

  }

  public void resetAddComponents() {
    view.resetAddComponents();
  }

  public void resetRemoveComponents() {
    view.resetRemoveComponents();
  }

  public void resetClearComponents() {
    view.resetClearComponents();
  }

  public void setConnectComponents() {
    view.setConnectComponents();
  }

  public void resetOkComponents() {
    view.resetOkComponents();
  }
}
