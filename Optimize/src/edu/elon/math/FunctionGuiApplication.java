/*
 * FunctionApplication.java 1.0 August 20, 2014
 *
 * Copyright (c) 2014 David J. Powell, Spencer Newsom,Rex Reynolds,Dan Malecki Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Application creates guis for a Sams Club maximization problem,
 * MinimumAbsoluteSum problem, and a Dell minimization problem. Though only
 * optimizing these three functions the framework is flexible and the user can
 * easily add more Functions.
 * 
 * @author dpowell2, snewsom, RexIII, DMalecki
 * @version 1.0
 */
public class FunctionGuiApplication {

	/**
	 * Application to optimize an Elon function using a variety of optimization
	 * techniques. Currently the only two working techniques are RandomWalk and
	 * Powell. We will change this in subsequent homeworks and implment
	 * NelderMead in Homework 5
	 * 
	 * @param args
	 *            Command line parameters that are not currently used.
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		try {

			// System.setProperty("java.security.policy", "client.policy");
			// System.setSecurityManager(new SecurityManager());
			String url = "rmi://localhost/";
			if (args.length == 1) {
				url = "rmi://" + args[0] + "/";
			}

			Context namingContext = new InitialContext();
			RemoteInterface f1 = (RemoteInterface) namingContext.lookup(url
					+ "Dell");
			RemoteInterface f2 = (RemoteInterface) namingContext.lookup(url
					+ "SamsClub");
			RemoteInterface f3 = (RemoteInterface) namingContext.lookup(url
					+ "MinimumAbsoluteSum");

			new FunctionGui(f1);
			new FunctionGui(f2);
			new FunctionGui(f3);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
