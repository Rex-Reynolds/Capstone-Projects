/**
 * SimpleOptimizerFactory.java 1.0 Oct 4, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;

/**
 * Factory for selecting which optimizer to use.
 *
 * @author RexIII
 * @version 1.0
 *
 */
public class SimpleOptimizerFactory {
	private Class<?> optiTechique;
	private Technique technique;

	/**
	 * @param function
	 * @return technique for the function optimization
	 * @throws RemoteException
	 */
	public Technique selectOptimizer(Function function) throws RemoteException {

		try {
			optiTechique = Class.forName(function.getOptimizationTechnique());
		} catch (ClassNotFoundException e1) {
			System.out.println("Class not found!");
			e1.printStackTrace();
		}
		try {

			technique = (Technique) optiTechique.newInstance();
		} catch (InstantiationException e) {
			System.out.println("Problems in instantiation of technique");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Problems accessing technique");
			e.printStackTrace();
		}

		return technique;

	}

}
