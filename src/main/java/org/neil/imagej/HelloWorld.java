package org.neil.imagej;

import ij.IJ;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class HelloWorld {
  public static void main(String[] args) {
    if (IJ.getInstance() == null) {
      throw new IllegalStateException("Could not instantiate");
    }
  }
}
