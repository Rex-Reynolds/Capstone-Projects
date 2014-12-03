/*
 * Observer.java 1.0 November 10, 2014
 *
 * Copyright (c) 2014 Rex Reynolds, Dan Malecki Elon University
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */

package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * Interface for Observers.
 * 
 * @author RexIII, DMalecki
 * @version 1.0
 * @since 1.0
 * 
 */
public interface Observer extends Remote {

	/*
	 * updates observers
	 */
	public void update(RemoteInterface o) throws RemoteException;

}
