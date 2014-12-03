/**
 * EncryptWriter.java 1.0 Sep 24, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.io;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ALL write methods in EncryptionWriter
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public class EncryptWriterTest {

  PrintWriter pw2;

  char[] testChar = { 'A', 'B', 'C', 'D', 'E', 'F' };
  String testStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  int a;
  StringWriter sWriter = new StringWriter(6);
  int b;
  int c;
  StringBuilder builder1 = new StringBuilder();

  /**
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    pw2 = new PrintWriter(new EncryptWriter(sWriter));

  }

  /**
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    a = 0;
    b = 0;
    c = 0;
    pw2 = null;
    builder1 = null;
    sWriter = null;
    testStr = null;
    testChar = null;

  }

  /**
   * testing for the Write(int c) method
   * 
   * 
   */
  @Test
  public void testWriteInt() {
    c = 66;
    pw2.write(c);
    c--;
    builder1.append((char) c);
    assertEquals(builder1.toString(), sWriter.toString());
  }

  /**
   * testing for Write(char[], int, int) method
   */
  @Test
  public void testWriteCharArrayIntInt() {
    int off = 2;
    int len = 4;
    for (int i = off - 1; i < len + 1; i++) {
      builder1.append(testChar[i]);
    }
    pw2.write(testChar, off, len);
    assertEquals(builder1.toString(), sWriter.toString());

  }

  /**
   * testing for Write(String str, int, int) methodsF
   */
  @Test
  public void testWriteStringIntInt() {
    int off = 2;
    int len = 4;
    pw2.write(testStr.toCharArray().toString(), off, len);
    for (int i = off - 1; i < len + 1; i++) {
      builder1.append(testStr.toCharArray()[i]);
    }
    assertEquals(builder1.toString(), sWriter.toString());

  }

}
