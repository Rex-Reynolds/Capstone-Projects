/**
 * ModelInterface.java 1.0 Nov 23, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for the model
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public interface ModelInterface {

  /**
   * performs a given operation on the database
   * 
   * @param query
   * @param username
   * @param password
   * @param ipaddress
   * @param dbName
   * @return arraylist of lists of strings
   */
  public ArrayList<List<String>> databaseOperation(String query, String username, String password,
          String ipaddress, String dbName);

  /**
   * 
   * @return arraylist of lists
   */
  public ArrayList<List<String>> getResults();

}
