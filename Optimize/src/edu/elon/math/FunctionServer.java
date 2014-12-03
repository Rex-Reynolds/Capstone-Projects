/**
 * FunctionServer.java 1.0 Nov 9, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds, Daniel Malecki. All Rights Reserved
 */
package edu.elon.math;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Server allowing RMI usage of the Function Gui's
 * 
 * @author RexIII, DMalecki
 * @version 1.0
 * 
 */
public class FunctionServer {

	/**
	 * Main class. Binds elements to RMI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			System.out.println("Constructing server implementations...");

			RemoteInterface f1 = new Dell();
			RemoteInterface f2 = new SamsClub();
			RemoteInterface f3 = new MinimumAbsoluteSum();

			System.out.println("Binding server implementations to registry...");

			Context namingContext = new InitialContext();
			namingContext.rebind("rmi:Dell", f1);
			namingContext.rebind("rmi:SamsClub", f2);
			namingContext.rebind("rmi:MinimumAbsoluteSum", f3);

			System.out.println("Waiting for invocations from clients...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
