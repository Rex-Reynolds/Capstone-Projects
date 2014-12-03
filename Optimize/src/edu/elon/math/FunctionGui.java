/*
 * FunctionGuiApplication.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 Spencer Newsom, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import layout.SpringUtilities;

/**
 * @author snewsom, RexIII, Dan Malecki Gui class for the Optimize package.
 */
public class FunctionGui extends UnicastRemoteObject implements Observer {
	/**
   * 
   */
	private static final long serialVersionUID = 1L;
	private RemoteInterface function;
	private GroupLayout layout;
	private JButton optimizeButton;
	private JButton solveButton;
	private JComboBox<?> comboBox;
	private JScrollPane scrollPane;
	private JPanel scrollPanel;
	private JTextField resultField;
	private JFrame frame;

	/**
   * 
   */
	public static int LAST_X = 100;
	/**
   * 
   */
	public static int LAST_Y = 100;

	private ArrayList<JTextField> fieldList;

	/**
	 * Constructor for FunctionGuiApplication.
	 * 
	 * @param f1
	 *            function to be used for the gui
	 * @throws RemoteException
	 */
	public FunctionGui(RemoteInterface f1) throws RemoteException {
		frame = new JFrame();
		this.function = f1;
		fieldList = new ArrayList<JTextField>();

		function.registerObserver(this);
		readEnvVars();
		comboBox.addActionListener(new ListListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(375, 275);
		frame.setLocation(FunctionGui.LAST_X, FunctionGui.LAST_Y);
		FunctionGui.LAST_X += 50;
		FunctionGui.LAST_Y += 50;
		frame.setTitle(f1.getTitle());
		// mainPanel = new JPanel();
		layout = new GroupLayout(frame.getContentPane());
		frame.setLayout(layout);

		JLabel techniqueLabel = new JLabel("Technique");

		JLabel resultLabel = new JLabel("Result");
		resultField = new JTextField();

		optimizeButton = new JButton("Optimize");
		solveButton = new JButton("Solve");
		solveButton.addActionListener(new SolveListener());
		optimizeButton.addActionListener(new OptimizeListener());

		scrollPanel = new JPanel(new SpringLayout());
		scrollPane = new JScrollPane(scrollPanel);
		for (int i = 0; i < f1.getInputNames().size(); i++) {
			JLabel label = new JLabel(f1.getInputNames().get(i));
			scrollPanel.add(label);
			JTextField textField = new JTextField(20);
			label.setLabelFor(textField);
			textField.setText(f1.getInputValues().get(i).toString());
			fieldList.add(textField);
			scrollPanel.add(textField);
		}
		SpringUtilities.makeCompactGrid(scrollPanel, f1.getInputNames().size(),
				2, 6, 6, 0, 0);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(techniqueLabel)
								.addComponent(comboBox))
				.addGroup(
						layout.createSequentialGroup().addComponent(scrollPane))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(resultLabel)
								.addComponent(resultField))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(solveButton)
								.addComponent(optimizeButton)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(techniqueLabel)
								.addComponent(comboBox))
				.addComponent(scrollPane)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(resultLabel)
								.addComponent(resultField))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(solveButton)
								.addComponent(optimizeButton)));

		frame.setVisible(true);
	}

	private class ListListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = comboBox.getSelectedIndex();
			try {
				function.setOptimizationTechnique(comboBox.getItemAt(index)
						.toString());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void readEnvVars() throws RemoteException {
		String vars = "";
		ArrayList<String> tempList = new ArrayList<String>();
		StringTokenizer st;
		vars = System.getenv("optimizers");
		st = new StringTokenizer(vars, ";");
		while (st.hasMoreTokens()) {
			tempList.add(st.nextToken());
		}
		comboBox = new JComboBox<Object>(tempList.toArray());
		function.setOptimizationTechnique(comboBox.getItemAt(0).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(RemoteInterface o) throws RemoteException {
		this.function = o;

		try {
			for (int i = 0; i < function.getInputValues().size(); i++) {
				fieldList.get(i).setText(
						function.getInputValues().get(i).toString());

			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (function.getOutput() != null) {
				resultField.setText(function.getOutput().toString());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class SolveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Double> tempArray = new ArrayList<Double>();
			try {
				for (int i = 0; i < function.getInputValues().size(); i++) {
					tempArray.add(Double
							.parseDouble(fieldList.get(i).getText()));
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				function.setInputValues(tempArray);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Runnable r = new Runnable() {

				@Override
				public void run() {
					try {
						function.evaluate();
						// resultField.setText(function.getOutput().toString());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}
	}

	private class OptimizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Double> tempArray = new ArrayList<Double>();
			try {
				for (int i = 0; i < function.getInputValues().size(); i++) {
					tempArray.add(Double
							.parseDouble(fieldList.get(i).getText()));
				}
			} catch (NumberFormatException e1) {

				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				function.setInputValues(tempArray);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Runnable r = new Runnable() {

				@Override
				public void run() {
					try {
						function.optimize();
						// resultField.setText(function.getOutput().toString());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			};
			new Thread(r).start();
		}
	}

}
