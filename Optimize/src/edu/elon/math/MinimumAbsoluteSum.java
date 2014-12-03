/*
 * MinimumAbsoluteSum.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 Spencer Newsom, Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Start each Class or interface with a summary description line
 *
 * @author Snewsom
 * @version 1.0
 *
 */
public class MinimumAbsoluteSum extends Function {

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = -554873907716795143L;

	/**
	 * Default constructor to set initial input values.
	 * 
	 * @throws RemoteException
	 * 
	 */
	public MinimumAbsoluteSum() throws RemoteException {
		this(new double[] { -100, 100, -100, 100, -100, 100, -100, 100, -100,
				100 });
	}

	/**
	 * Constructor initializes initial input point to double[] passed in as a
	 * parameter
	 * 
	 * @param inputs
	 *            double[] representing values for initial design point.
	 * @throws RemoteException
	 */
	public MinimumAbsoluteSum(double[] inputs) throws RemoteException {
		ArrayList<Double> values = new ArrayList<Double>();
		for (double d : inputs) {
			values.add(new Double(d));
		}
		this.setInputValues(values);
		this.setInputNames(createDefaultInputNames());
		this.setMinimize(true);
		this.setTitle("MinimumAbsoluteSum");
	}

	/**
	 * Initializes the names of each input along with its initial value from the
	 * parameters.
	 * 
	 * @param inputValues
	 *            ArrayList<Double> representing values of initial design point.
	 * @param inputNames
	 *            ArrayList<String> representing names of each input parameter
	 * @throws RemoteException
	 */
	public MinimumAbsoluteSum(ArrayList<Double> inputValues,
			ArrayList<String> inputNames) throws RemoteException {
		this.setInputValues(inputValues);
		this.setInputNames(inputNames);
		this.setMinimize(true);
		this.setTitle("MinimumAbsoluteSum");
	}

	private static ArrayList<String> createDefaultInputNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("value1");
		names.add("value2");
		names.add("value3");
		names.add("value4");
		names.add("value5");
		names.add("value6");
		names.add("value7");
		names.add("value8");
		names.add("value9");
		names.add("value10");
		return names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.elon.math.Function#evaluate()
	 */
	@Override
	public Double evaluate() throws RemoteException {
		double mas = 0.0;
		for (Double value : getInputValues()) {
			mas += Math.abs(value.doubleValue());
		}
		this.setOutput(new Double(mas));
		return this.getOutput();
	}

}
