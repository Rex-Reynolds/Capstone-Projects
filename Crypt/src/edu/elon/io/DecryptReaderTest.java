/**
 * EncryptWriter.java 1.0 Sep 24, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

/**
 * Test class for ALL read methods in decryptionReader
 *
 * @author RexIII
 * @version 1.0
 *
 */
public class DecryptReaderTest {
  DecryptReader dReader;
  char[] char1 = { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' };
  int a;
  StringBuilder builder1 = new StringBuilder();
  String expected = "aaaaaaaaaa";

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    dReader = new DecryptReader(new StringReader(expected));
  }

  /**
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    dReader = null;
    char1 = null;
    builder1 = null;
    expected = null;
    a = 0;
  }

  /**
   * Test for the read() method
   */
  @Test
  public void testRead() {
    try {
      while ((a = dReader.read()) >= expected.length()) {
        builder1.append((char) ((int) a - 1));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals(expected, builder1.toString());
  }

  /**
   * test for the read(char[] actual, offset, length) method
   */
  @Test
  public void testReadCharArrayIntInt() {
    char[] actual = new char[10];
    Arrays.fill(actual, 'a');
    assertEquals(expected.length(), this.dReader.read(actual, 0, actual.length));
    assertTrue(Arrays.equals(actual, expected.toCharArray()));

  }

}
