/*
 * NelderMead.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell, Spencer Newsom, Rex Reynolds Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import flanagan.math.Minimisation;
import flanagan.math.MinimisationFunction;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Nelder Mead also known as direct simplex method is a widely used nonlinear
 * unconstrained optimization technique. The goSimplex code is only partially
 * implemented and always returns a optimal value of 0.0. This class functions
 * as an adapter class for the Flanagan package
 * 
 * @author dpowell2, snewsom, rreynolds3
 * @version 1.0
 */
public class NelderMead implements Technique, MinimisationFunction {

	private int size;
	private double[] startPoint;
	private double[][] vertices;
	private double ftol = 1e-15;
	private double[] step;
	private Function function;

	/**
	 * Default constructor to satisfy coding best practices
	 */
	public NelderMead() {
		// intentionally empty
	}

	/**
	 * Arrays will start at 1 instead of 0 so we need to allocate 1 more space
	 * than anticipated.
	 * 
	 * @param nDim
	 *            the number of elements in the input
	 */
	public void allocateArrays(int nDim) {
		startPoint = new double[nDim + 1];
		vertices = new double[nDim + 2][nDim + 1];
	}

	/**
	 * 
	 * Leaves optimal design as the value of the parameter, function instance,
	 * field called inputValues;
	 * 
	 * @param function
	 *            Function instance containing function starting point and
	 *            evaluation logic
	 * @return Double value for optimal design.
	 */
	public double evalute(Function function) {
		// assign the function to a useful variable
		this.function = function;

		// Create instance of Minimisation
		Minimisation min = new Minimisation();

		// get the startpoint from the function
		try {
			startPoint = convertArrayListToDouble(function.getInputValues());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// get the # of start values from the function
		try {
			size = function.getInputValues().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// similarity check
		getElonCopyrightVertices();
		createInitialSetOfPoints();

		// constructor for the nelder mead/flanagan class
		// given the function(NelderMead implements Minimisation Function)
		// given the startpoint of the function
		// given the step sizes for the startpoints
		// given ftol (static #)
		min.nelderMead(this, startPoint, step, ftol);

		// returns minimum result from NelderMead/Flanagan
		return min.getMinimum();
	}

	@SuppressWarnings("unused")
	private void amoeba() {
		Vector<Double> pSum = new Vector<Double>();
		pSum.add(10.0); // first element is a dummy placeholder
	}

	@SuppressWarnings("unused")
	private void amoeba(double[][] p, double[] y, int elondim, float ftol,
			Integer nFunk) {
		// not implemented
	}

	/*
	 * creates initial points of interest
	 */
	private void createInitialSetOfPoints() {
		step = new double[getSize()];
		for (int i = 0; i < size; i++) {
			if (Math.abs(startPoint[i]) < 1.0) {
				step[i] = 1.0;
			} else {
				step[i] = 0.5 * startPoint[i];
			}
		}
	}

	private double[][] getElonCopyrightVertices() {
		return vertices;
	}

	private int getSize() {
		return size;
	}

	@SuppressWarnings("unused")
	private double[] getStartPoint() {
		return startPoint;
	}

	/**
	 * Converts an arraylist of Double into an array of doubles
	 * 
	 * @param aStartingPoint
	 *            arraylist of Double representing an input point for an
	 *            optimization problem
	 * @return double array of input point represented as a 1D vector
	 */
	private double[] convertArrayListToDouble(ArrayList<Double> aStartingPoint) {
		int length = aStartingPoint.size();
		double[] inputArray = new double[length];
		for (int i = 0; i < length; i++) {
			inputArray[i] = aStartingPoint.get(i);
		}
		return inputArray;
	}

	/**
	 * Converts a input point repesented as a one dimensional array of doubles
	 * into an arraylist of double
	 * 
	 * @param aInputArray
	 *            input array of double values
	 * @return input arraylist of Double values
	 */
	private ArrayList<Double> convertDoubleArrayToArrayList(double[] aInputArray) {
		ArrayList<Double> bestInputPoint = new ArrayList<Double>();
		for (double d : aInputArray) {
			bestInputPoint.add(d);
		}
		return bestInputPoint;
	}

	/*
	 * Adapter class to convert from the given
	 * function(Powell,SamsClub,MinimumAbsoluteSum) to Minimization Function.
	 * Updates inputValues and accounts for maximum or minimum function.
	 * 
	 * @see flanagan.math.MinimisationFunction#function(double[])
	 */
	@Override
	public double function(double[] aParam) {
		double result = 0;
		try {
			function.setInputValues(convertDoubleArrayToArrayList(aParam));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (function.isMinimize() == false) {
				result = function.evaluate() * -1;
			} else {
				result = function.evaluate();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
