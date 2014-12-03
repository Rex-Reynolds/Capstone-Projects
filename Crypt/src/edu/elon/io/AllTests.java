/**
 * EncryptWriter.java 1.0 Sep 24, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.io;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Runs ALL JUNIT tests for read() and write() methods
 *
 * @author RexIII
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ DecryptReaderTest.class, EncryptWriterTest.class })
public class AllTests {

}
