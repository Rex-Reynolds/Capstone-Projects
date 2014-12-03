/*
 * Technique.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 Spencer Newsom Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;

/**
 * 
 * Interface for Optimization Techniques.
 * 
 * @author snewsom
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Technique {
	/**
	 * 
	 * Leaves optimal design as the value of the parameter, function instance,
	 * field called inputValues;
	 * 
	 * @param function
	 *            Function instance containing function starting point and
	 *            evaluation logic
	 * @return Double value for optimal design.
	 * @throws RemoteException
	 */
	public abstract double evalute(Function function) throws RemoteException;

}