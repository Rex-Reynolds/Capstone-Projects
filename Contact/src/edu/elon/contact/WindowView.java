/**
 * WindowView.java 1.0 Aug 29, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds III. All Rights Reserved
 */
package edu.elon.contact;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author rreynolds3
 * @version 1.0
 * 
 */
public class WindowView implements Observer {

  /**
   * Width of the initial frame
   */
  public static final int WIDTH = 260;
  /**
   * Height of the initial Frame
   */
  public static final int HEIGHT = 210;
  /**
   * Length of the text fields
   */
  public static final int NUMBER_OF_CHAR = 10;

  private JTextField firstName;
  private JTextField middleName;
  private JTextField lastName;
  private JTextField email;
  private JTextField major;

  private JTextField newFirstName;
  private JTextField newMiddleName;
  private JTextField newLastName;
  private JTextField newEmail;
  private JTextField newMajor;

  private JTextField userName;
  private JTextField password;
  private JTextField ipAddress;
  private JTextField dbName;
  private JTextField tableName;
  private int tempNum2 = 0;
  private JPanel namePanel;
  private JPanel buttonPanel;
  private JMenu fileMenu;
  private JMenu editMenu;
  private JLabel firstNameLabel;
  private JLabel middleNameLabel;
  private JLabel lastNameLabel;
  private JLabel emailLabel;
  private JLabel majorLabel;
  private JMenuBar bar;
  private JButton prevButton;
  private JButton nextButton;
  private JButton okButton2;
  private JMenuItem add;
  private JMenuItem remove;
  private JMenuItem update;
  private JMenuItem clearDb;
  private JMenuItem connect;
  private JLabel tableNameLabel;
  private JLabel userNamelabel;
  private JLabel passwordLabel;
  private JLabel ipAddressLabel;
  private JLabel dbNameLabel;
  private JButton okButton;
  private JMenuItem exit;
  private JButton cancelButton;

  private List<List<String>> results;

  private ModelInterface model = null;
  private ControllerInterface controller;
  private JFrame viewFrame;

  /**
   * Generic constructor
   * 
   * @param controller
   * @param aModel
   * 
   */
  public WindowView(ControllerInterface controller, ModelInterface aModel) {
    // Title for all Swing Gui's
    this.model = aModel;
    this.controller = controller;
    ((Observable) model).addObserver(this);
  }

  /**
   * Creates the initial Gui view
   */
  public void createView() {

    viewFrame = new JFrame("Calculator");

    viewFrame.setSize(WIDTH, HEIGHT);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    viewFrame.setLayout(new FlowLayout());

    // primary text panel
    namePanel = new JPanel();
    namePanel.setLayout(new GridLayout(5, 0));

    // primary button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    // file menu system for gui
    fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    editMenu = new JMenu("Edit");
    editMenu.setMnemonic(KeyEvent.VK_E);

    // labels for database entry and display of entries
    firstNameLabel = new JLabel("First Name");
    firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    middleNameLabel = new JLabel("Middle Name");
    middleNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    lastNameLabel = new JLabel("Last Name");
    lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    emailLabel = new JLabel("Email");
    emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    majorLabel = new JLabel("Major");
    majorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

    // text fields for database entry and display of entries
    firstName = new JTextField(NUMBER_OF_CHAR);
    middleName = new JTextField(NUMBER_OF_CHAR);
    lastName = new JTextField(NUMBER_OF_CHAR);
    email = new JTextField(NUMBER_OF_CHAR);
    major = new JTextField(NUMBER_OF_CHAR);

    // text fields for database access
    // already populated with information for contacting
    // the default database
    userName = new JTextField("b4402acc8331ea", NUMBER_OF_CHAR);
    password = new JTextField("828537a4", NUMBER_OF_CHAR);
    ipAddress = new JTextField("us-cdbr-cb-east-01.cleardb.net", NUMBER_OF_CHAR);
    dbName = new JTextField("cb_contactdjp", NUMBER_OF_CHAR);
    tableName = new JTextField("rar_contacts", NUMBER_OF_CHAR);

    // Initial setup of bars and panels
    bar = new JMenuBar();
    bar.add(fileMenu);
    bar.add(editMenu);
    viewFrame.setJMenuBar(bar);
    namePanel.add(firstNameLabel);
    namePanel.add(firstName);
    namePanel.add(middleNameLabel);
    namePanel.add(middleName);
    namePanel.add(lastNameLabel);
    namePanel.add(lastName);
    namePanel.add(emailLabel);
    namePanel.add(email);
    namePanel.add(majorLabel);
    namePanel.add(major);
    viewFrame.add(namePanel);
    viewFrame.add(buttonPanel);

    // button used to traverse the database entries backwards
    prevButton = new JButton("Previous");
    buttonPanel.add(prevButton);
    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aArg0) {
        if (tempNum2 > 0) {
          tempNum2--;
          results = controller.previous(tableName.getText(), userName.getText(),
                  password.getText(), ipAddress.getText(), dbName.getText());

          firstName.setText(results.get(0).get(tempNum2));
          middleName.setText(results.get(1).get(tempNum2));
          lastName.setText(results.get(2).get(tempNum2));
          email.setText(results.get(3).get(tempNum2));
          major.setText(results.get(4).get(tempNum2));
        } else {
          System.out.println("no more entries");
        }

      }
    });

    // button to traverse database entries chronologically
    nextButton = new JButton("Next");
    buttonPanel.add(nextButton, BorderLayout.SOUTH);
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        if (tempNum2 < results.size()) {

          tempNum2++;
          results = null;
          results = controller.next(tableName.getText(), userName.getText(), password.getText(),
                  ipAddress.getText(), dbName.getText());

          firstName.setText(results.get(0).get(tempNum2));
          middleName.setText(results.get(1).get(tempNum2));
          lastName.setText(results.get(2).get(tempNum2));
          email.setText(results.get(3).get(tempNum2));
          major.setText(results.get(4).get(tempNum2));
        } else {
          System.out.println("no more entries");
        }

      }
    });

    // button used when creating new database entry
    // used in the addButton fileMenuListener to create a
    // new gui for adding entries to the database
    okButton2 = new JButton("OK");
    okButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        controller.add(newFirstName.getText(), newMiddleName.getText(), newLastName.getText(),
                newEmail.getText(), newMajor.getText(), tableName.getText(), userName.getText(),
                password.getText(), ipAddress.getText(), dbName.getText());

        // resetting all the components
        controller.resetComponents();
      }
    });

    // button used to traverse back from the addGUI to the previous
    // active
    // database entry
    cancelButton = new JButton("Cancel");
    okButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {

        // resetting components
        controller.resetComponents();
      }
    });

    // Menu item for adding new database entries
    // uses okButton2
    // uses cancelButton
    add = new JMenuItem("Add");
    add.setMnemonic(KeyEvent.VK_A);
    add.setEnabled(false);
    editMenu.add(add);
    add.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent aE) {

        // resetting components
        controller.resetAddComponents();
      }
    });

    // menuItem used to delete database entries
    remove = new JMenuItem("Remove");
    remove.setMnemonic(KeyEvent.VK_R);
    editMenu.add(remove);
    remove.setEnabled(false);
    remove.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent aE) {
        controller.remove(tableName.getText(), lastName.getText(), userName.getText(),
                password.getText(), ipAddress.getText(), dbName.getText());
        results = controller.connect(tableName.getText(), userName.getText(), password.getText(),
                ipAddress.getText(), dbName.getText());
        firstName.setText(results.get(0).get(0));
        middleName.setText(results.get(1).get(0));
        lastName.setText(results.get(2).get(0));
        email.setText(results.get(3).get(0));
        major.setText(results.get(4).get(0));

        // resetting all components
        controller.resetRemoveComponents();

      }
    });

    // menuItem used to update current database entries
    update = new JMenuItem("Update");
    update.setMnemonic(KeyEvent.VK_U);
    editMenu.add(update);
    update.setEnabled(false);
    update.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        controller.update(tableName.getText(), firstName.getText(), middleName.getText(),
                lastName.getText(), email.getText(), major.getText(), userName.getText(),
                password.getText(), ipAddress.getText(), dbName.getText());
        viewFrame.validate();
        viewFrame.repaint();
      }
    });

    // menuItem used to clear ALL entries from the database
    clearDb = new JMenuItem("Clear DB");
    clearDb.setMnemonic(KeyEvent.VK_C);
    clearDb.setEnabled(false);
    fileMenu.add(clearDb);
    clearDb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        controller.clearDb(tableName.getText(), userName.getText(), password.getText(),
                ipAddress.getText(), dbName.getText());

        // resetting all fields to null to reflect cleared database
        controller.resetClearComponents();

      }
    });

    // menuItem used to connect to a database
    // uses tablenameLabel
    // uses userNameLabel
    // uses passwordLabel
    // uses ipAddressLabel
    // uses dbNameLabel
    // uses okButton
    connect = new JMenuItem("Connect");
    connect.setMnemonic(KeyEvent.VK_T);
    fileMenu.add(connect);
    connect.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent aArg0) {

        controller.setConnectComponents();

        // button used in connecting to the database
        // assigns first database entry to the initial gui.
        okButton = new JButton("OK");
        buttonPanel.add(okButton, BorderLayout.SOUTH);
        okButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent aE) {
            results = controller.connect(tableName.getText(), userName.getText(),
                    password.getText(), ipAddress.getText(), dbName.getText());

            if (results.size() > 0) {
              firstName.setText(results.get(0).get(0));
              middleName.setText(results.get(1).get(0));
              lastName.setText(results.get(2).get(0));
              email.setText(results.get(3).get(0));
              major.setText(results.get(4).get(0));
            } else {
              System.out.println("no entries in the database");
            }

            // reset all fields and buttons
            controller.resetOkComponents();
          }
        });
        viewFrame.validate();
        viewFrame.repaint();
      }
    });

    // Menu Items used to completely exit the application
    exit = new JMenuItem("Exit");
    exit.setMnemonic(KeyEvent.VK_X);
    fileMenu.addSeparator();
    fileMenu.add(exit);
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent aE) {
        controller.exit();

      }

    });
    viewFrame.setVisible(true);
  }

  /**
   * @param query = the varying queries used to insert, delete, or
   *          update the database
   * @throws SQLException
   */

  /*
   * (non-Javadoc)
   * 
   * @see java.util.Observer#update(java.util.Observable,
   * java.lang.Object)
   */
  public void update(Observable aO, Object aArg) {
    results = model.getResults();
    if (!results.isEmpty()) {

      firstName.setText(results.get(0).get(0));
      middleName.setText(results.get(1).get(0));
      lastName.setText(results.get(2).get(0));
      email.setText(results.get(3).get(0));
      major.setText(results.get(4).get(0));
    } else {
      System.out.println("results are empty");
    }

  }

  /**
   * resets components for the view
   */
  public void resetComponents() {
    namePanel.removeAll();
    buttonPanel.removeAll();

    namePanel.add(firstNameLabel);
    namePanel.add(firstName);

    namePanel.add(middleNameLabel);
    namePanel.add(middleName);

    namePanel.add(lastNameLabel);
    namePanel.add(lastName);

    namePanel.add(emailLabel);
    namePanel.add(email);

    namePanel.add(majorLabel);
    namePanel.add(major);

    buttonPanel.add(prevButton);
    buttonPanel.add(nextButton);

    viewFrame.validate();
    viewFrame.repaint();

  }

  /**
   * resets components for the view
   */
  public void resetAddComponents() {
    namePanel.removeAll();
    buttonPanel.removeAll();

    newFirstName = new JTextField(NUMBER_OF_CHAR);
    newMiddleName = new JTextField(NUMBER_OF_CHAR);
    newLastName = new JTextField(NUMBER_OF_CHAR);
    newEmail = new JTextField(NUMBER_OF_CHAR);
    newMajor = new JTextField(NUMBER_OF_CHAR);

    namePanel.add(firstNameLabel);
    namePanel.add(newFirstName);

    namePanel.add(middleNameLabel);
    namePanel.add(newMiddleName);

    namePanel.add(lastNameLabel);
    namePanel.add(newLastName);

    namePanel.add(emailLabel);
    namePanel.add(newEmail);

    namePanel.add(majorLabel);
    namePanel.add(newMajor);

    buttonPanel.add(okButton2);
    buttonPanel.add(cancelButton);

    viewFrame.validate();
    viewFrame.repaint();

  }

  /**
   * resets components for the view
   */
  public void resetRemoveComponents() {
    namePanel.removeAll();
    namePanel.add(firstNameLabel);
    namePanel.add(firstName);
    namePanel.add(middleNameLabel);
    namePanel.add(middleName);
    namePanel.add(lastNameLabel);
    namePanel.add(lastName);
    namePanel.add(emailLabel);
    namePanel.add(email);
    namePanel.add(majorLabel);
    namePanel.add(major);

    viewFrame.validate();
    viewFrame.repaint();
  }

  /**
   * resets components for the view
   */
  public void resetClearComponents() {
    namePanel.removeAll();
    buttonPanel.removeAll();
    newFirstName = new JTextField(NUMBER_OF_CHAR);
    newMiddleName = new JTextField(NUMBER_OF_CHAR);
    newLastName = new JTextField(NUMBER_OF_CHAR);
    newEmail = new JTextField(NUMBER_OF_CHAR);
    newMajor = new JTextField(NUMBER_OF_CHAR);
    namePanel.add(firstNameLabel);
    namePanel.add(newFirstName);
    namePanel.add(middleNameLabel);
    namePanel.add(newMiddleName);
    namePanel.add(lastNameLabel);
    namePanel.add(newLastName);
    namePanel.add(emailLabel);
    namePanel.add(newEmail);
    namePanel.add(majorLabel);
    namePanel.add(newMajor);
    buttonPanel.add(prevButton);
    buttonPanel.add(nextButton);

    viewFrame.validate();
    viewFrame.repaint();
  }

  /**
   * resets components for the view
   */
  public void setConnectComponents() {
    namePanel.removeAll();
    buttonPanel.removeAll();

    tableNameLabel = new JLabel("Table Name");
    tableNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(tableNameLabel);
    namePanel.add(tableName);

    userNamelabel = new JLabel("User Name");
    userNamelabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(userNamelabel);
    namePanel.add(userName);

    passwordLabel = new JLabel("Password");
    passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(passwordLabel);
    namePanel.add(password);

    ipAddressLabel = new JLabel("IP Address");
    ipAddressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(ipAddressLabel);
    namePanel.add(ipAddress);

    dbNameLabel = new JLabel("Database Name");
    dbNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(dbNameLabel);
    namePanel.add(dbName);
  }

  /**
   * resets components for the view
   */
  public void resetOkComponents() {
    namePanel.removeAll();
    buttonPanel.removeAll();
    firstNameLabel = new JLabel("First Name");
    firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(firstNameLabel);
    namePanel.add(firstName);
    middleNameLabel = new JLabel("Middle Name");
    middleNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(middleNameLabel);
    namePanel.add(middleName);
    lastNameLabel = new JLabel("Last Name");
    lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(lastNameLabel);
    namePanel.add(lastName);
    emailLabel = new JLabel("Email");
    emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(emailLabel);
    namePanel.add(email);
    majorLabel = new JLabel("Major");
    majorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    namePanel.add(majorLabel);
    namePanel.add(major);
    buttonPanel.add(prevButton);
    buttonPanel.add(nextButton);
    add.setEnabled(true);
    remove.setEnabled(true);
    update.setEnabled(true);
    clearDb.setEnabled(true);
    viewFrame.validate();
    viewFrame.repaint();
  }

}
