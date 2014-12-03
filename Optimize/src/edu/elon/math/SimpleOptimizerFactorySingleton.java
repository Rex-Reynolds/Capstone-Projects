package edu.elon.math;

/**
 * Singleton.java 1.0 Oct 4, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */

/**
 * Simple Factory for creating an instance of a Function
 *
 * @author RexIII
 * @version 1.0
 *
 */
public class SimpleOptimizerFactorySingleton {

	private volatile static SimpleOptimizerFactory uniqueInstance;

	private SimpleOptimizerFactorySingleton() {

	}

	/**
	 * @return a unique instance of SimpleOptimizerFactory
	 */
	public static SimpleOptimizerFactory getInstance() {
		if (uniqueInstance == null) {
			synchronized (SimpleOptimizerFactorySingleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new SimpleOptimizerFactory();
				}
			}
		}
		return uniqueInstance;
	}

}
