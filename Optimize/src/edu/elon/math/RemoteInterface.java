/*
 * RemoteInterface.java 1.0 November 10, 2014
 *
 * Copyright (c) 2014 Rex Reynolds, Dan Malecki Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */

package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * @author RexIII,DMalecki
 * Interface for usage of methods remotely
 */
public interface RemoteInterface extends Remote {

	/*
	 * evaluates the function
	 */
	public abstract Double evaluate() throws RemoteException;

	/*
	 * gets input values
	 */
	public ArrayList<Double> getInputValues() throws RemoteException;

	/*
	 * get technique used for optimization
	 */
	public String getOptimizationTechnique() throws RemoteException;

	/*
	 * gets output of the function
	 */
	public Double getOutput() throws RemoteException;

	/*
	 * gets the title of the function
	 */
	public String getTitle() throws RemoteException;

	/*
	 * gets an ArrayList of strings of the input names
	 */
	public ArrayList<String> getInputNames() throws RemoteException;

	/*
	 * checks if the function is minimized
	 */
	public boolean isMinimize() throws RemoteException;

	/*
	 * optimizes the function
	 */
	public Double optimize() throws RemoteException;

	/*
	 * sets the input names for the function
	 */
	public void setInputNames(ArrayList<String> inputNames)
			throws RemoteException;

	/*
	 * sets the input values for the function
	 */
	public void setInputValues(ArrayList<Double> inputValues)
			throws RemoteException;

	/*
	 * sets the function as a minimization function
	 */
	public void setMinimize(boolean minimize) throws RemoteException;

	/*
	 * sets the optimization technique to the specified argument
	 */
	public void setOptimizationTechnique(String optimizationTechnique)
			throws RemoteException;

	/*
	 * sets the output
	 */
	public void setOutput(Double output) throws RemoteException;

	/*
	 * sets the title of the function
	 */
	public void setTitle(String title) throws RemoteException;

	/*
	 * registers an observer
	 */
	public void registerObserver(Observer o) throws RemoteException;

	/*
	 * removes an observer
	 */
	public void removeObserver(Observer o) throws RemoteException;

	/*
	 * notifies all observers
	 */
	public void notifyObservers() throws RemoteException;

}
