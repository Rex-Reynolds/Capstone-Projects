/**
 * ContactApplication.java 1.0 Aug 21, 2014
 *
 * Copyright (c) 2014 Rex Reynolds. All Rights Reserved
 */
package edu.elon.contact;

import javax.swing.JFrame;

/**
 * Swing application for maintaining and updating a MySQL database of
 * contact information.
 * 
 * @author rreynolds3
 * @version 1.0
 * 
 */
public class ContactApplication extends JFrame {

  /**
   * Generated Serial Id
   */
  private static final long serialVersionUID = 5925095626911453270L;

  /**
   * Java driver to start up program in a JVM
   * 
   * @param args array of string used for command line arguments.
   */
  public static void main(String[] args) {
    ModelInterface model = new WindowModel();
    new WindowController(model);

  }

}
