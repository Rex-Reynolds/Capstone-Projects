/**
 * EncryptWriter.java 1.0 Sep 24, 2014
 *
 * Copyright (c) 2014 Rex A. Reynolds. All Rights Reserved
 */
package edu.elon.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Writes an int, string, or char[] to a printWriter and ultimately a
 * file.
 * 
 * @author RexIII
 * @version 1.0
 * 
 */
public class EncryptWriter extends FilterWriter {
  protected Writer out;

  /**
   * @param Writer takes a printWriter as an argument
   * 
   *          generic constructor
   */
  protected EncryptWriter(Writer out) {
    super(out);
    this.out = out;

  }

  /**
   * @param c character to be written
   * 
   *          Writes a single, encrypted character
   */
  @Override
  public void write(int c) {
    try {
      out.write(c - 1);
    } catch (IOException e) {
      System.out.println("Broken in Write(int)");
      e.printStackTrace();
    }

  }

  /**
   * @param s string to be encrypted and written
   * @param off offset on when to begin reading the string
   * @param len length of the string
   * 
   * 
   *          Writes a string of encrypted characters
   */
  @Override
  public void write(String s, int off, int len) {
    char[] sCharArray = s.toCharArray();

    this.write(sCharArray, off, len);
  }

  /**
   * @param cbuf array to be encrypted and written
   * @param off offset on when to begin reading the string
   * @param len length of the string
   * 
   * 
   *          Writes a char[] of encrypted characters
   */
  @Override
  public void write(char[] cbuf, int off, int len) {
    for (int i = off; i < off + len; i++) {
      int value = cbuf[i];
      cbuf[i] = (char) (value - 1);
    }
    try {
      out.write(cbuf, off, len);
    } catch (IOException e) {
      System.out.println("Broken in Write(char[],int,int)");
      e.printStackTrace();
    }
  }

}
