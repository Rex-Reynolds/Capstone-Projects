/*
 * Function.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell, Spencer Newsom Elon, Rex Reynolds, Dan Malecki University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Standard interface for consistency in the Elon evaluation process. Each
 * concrete Elon Function must extend this class.
 * 
 * @author dpowell2, RexIII, DMalecki
 * @version 1.0
 */
public abstract class Function extends UnicastRemoteObject implements
		RemoteInterface {

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = 7082313975810810238L;

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * constant to represent new line
	 */
	public static final String EOL = "\n";

	/**
	 * constant to represent one blank space
	 */
	public static final String SPACE = " ";

	private ArrayList<String> inputNames;

	private ArrayList<Double> inputValues;

	// true if minimization function and false if maximization
	private boolean minimize;

	private String optimizationTechnique = "edu.elon.math.RandomWalk";

	private Technique technique;

	private Double output;

	private String title;

	/**
	 * Default constructor
	 */
	public Function() throws RemoteException {
		// empty
	}

	/**
	 * Evaluates the current set of input values to calculate the function
	 * value. We currently consider one output value for a function. If the
	 * function has multiple output values then the function must have these
	 * combined into a single value.
	 * 
	 * @return Double of function result from evaluation at current point.
	 */
	public abstract Double evaluate() throws RemoteException;

	/**
	 * Returns an ArrayList of String for the names of each input parameter.
	 * This allows the class creator to make the names meaningful to a user
	 * instead of X1, X2, ...
	 * 
	 * @return ArrayList<String> of names for each input parameter
	 */
	public ArrayList<String> getInputNames() throws RemoteException {
		return inputNames;
	}

	/**
	 * Returns the current value of each input for the function. All function
	 * inputs are treated as Double
	 * 
	 * @return ArrayList<Double> of values representing current point.
	 */
	public ArrayList<Double> getInputValues() throws RemoteException {
		return inputValues;
	}

	/**
	 * Gets the full package qualified classname of the currently set
	 * optimization technique
	 * 
	 * @return String representing package qualified classname of optimization
	 *         technique
	 */
	public String getOptimizationTechnique() throws RemoteException {
		return optimizationTechnique;
	}

	/**
	 * Gets the function output value resulting from the evaluation of the
	 * current input set.
	 * 
	 * @return Double representing function result
	 */
	public Double getOutput() throws RemoteException {
		return output;
	}

	/**
	 * Gets the name of the function
	 * 
	 * @return String representing the user friendly name of the function.
	 */
	public String getTitle() throws RemoteException {
		return title;
	}

	/**
	 * Gets the direction of the optimization problem. If true then we have a
	 * minimization problem otherwise a maximization problem
	 * 
	 * @return boolean value of true if minimization
	 */
	public boolean isMinimize() throws RemoteException {
		return minimize;
	}

	/**
	 * Optimizes function. The optimizer is required to leave the best design
	 * point and function value as the current state of the function in
	 * <code>inputValues</code> and <code>output</code>
	 * 
	 * @return Double representing best achieved function value.
	 */
	public Double optimize() throws RemoteException {
		SimpleOptimizerFactory optiFact = SimpleOptimizerFactorySingleton
				.getInstance();
		Function function = this;
		Double optimalValue = null;

		technique = optiFact.selectOptimizer(function);
		optimalValue = technique.evalute(function);
		function.getInputValues();
		return optimalValue;
	}

	/**
	 * Set the current set of input names for the input parameters to the
	 * inputNames passed as a parameter.
	 * 
	 * @param inputNames
	 *            ArrayList<String> of names for set of input parameters for the
	 *            function.
	 */
	public void setInputNames(ArrayList<String> inputNames)
			throws RemoteException {
		this.inputNames = inputNames;
	}

	/*
	 * Generic constructor
	 */
	public void update() {

	}

	/**
	 * Sets the current value of the input set for the function.
	 * 
	 * @param inputValues
	 *            ArrayList<Double> representing the value of each input
	 *            parameter. The input set is assumed to be all Doubles.
	 */
	public void setInputValues(ArrayList<Double> inputValues)
			throws RemoteException {
		this.inputValues = inputValues;
		this.notifyObservers();
	}

	/**
	 * Sets function to be a minimization or a maximization
	 * 
	 * @param minimize
	 *            boolean of true if minimization
	 */
	public void setMinimize(boolean minimize) throws RemoteException {
		this.minimize = minimize;
	}

	/**
	 * Sets internal field to the value of the passed parameter which represents
	 * the package qualified class name of the optimization technique to use.
	 * 
	 * @param optimizationTechnique
	 *            String representing package and class name of the optimizer to
	 *            use for the problem.
	 */
	public void setOptimizationTechnique(String optimizationTechnique)
			throws RemoteException {
		this.optimizationTechnique = optimizationTechnique;
	}

	/**
	 * Sets the value of the function result.
	 * 
	 * @param output
	 *            Double instance of function result
	 */
	public void setOutput(Double output) throws RemoteException {
		this.output = output;
		this.notifyObservers();
	}

	/**
	 * Sets the user friendly name of the function
	 * 
	 * @param title
	 *            String representing function name
	 */
	public void setTitle(String title) throws RemoteException {
		this.title = title;
	}

	/**
	 * User friendly representation of the function state and configuration.
	 * Shows the function name, input variable names and input variable values
	 * 
	 * @return String representing state of function.
	 */
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		try {
			s.append("Function: " + this.getTitle() + EOL);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < getInputValues().size(); i++) {
				s.append(getInputNames().get(i) + SPACE
						+ getInputValues().get(i) + EOL);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.elon.math.RemoteInterface#registerObserver(edu.elon.math.Observer)
	 */

	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.elon.math.RemoteInterface#removeObserver(edu.elon.math.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.elon.math.RemoteInterface#notifyObservers()
	 */

	@Override
	public void notifyObservers() throws RemoteException {
		for (Observer ob : observers) {
			ob.update(this);
		}
	}
}
