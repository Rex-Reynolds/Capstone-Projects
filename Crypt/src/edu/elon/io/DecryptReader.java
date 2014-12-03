/**
 * DecryptReader.java 1.0 Sep 24, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.io;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Reads in encrypted characters and outputs decrypted characters to a
 * PrintWriter
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public class DecryptReader extends FilterReader {
  private Reader in;

  /**
   * @param in FileReader generic constructor
   */
  protected DecryptReader(Reader in) {
    super(in);
    this.in = in;
  }

  /**
   * reads in a single character and decrypts it
   */
  @Override
  public int read() throws IOException {
    int c = in.read();
    if (c != -1) {
      c++;
    }
    return c;
  }

  /**
   * @param cbuf array of characters to be decrypted and written
   * @param offset initial starting and final ending for reading
   *          characters
   * @param length of char[] reads in an array of characters, decrypts
   *          them, and returns the length of the char[]
   */
  @Override
  public int read(char[] cbuf, int off, int len) {
    int num = 0;
    try {
      num = in.read(cbuf, off, len);
    } catch (IOException e) {
      System.out.println("error in read char[]");
      e.printStackTrace();
    }
    for (int i = off; i < off + num; i++) {
      try {
        cbuf[i] = (char) this.read();
      } catch (IOException e) {
        System.out.println("error in read char[] this.read()");
        e.printStackTrace();
      }
    }
    return len;
  }

}
