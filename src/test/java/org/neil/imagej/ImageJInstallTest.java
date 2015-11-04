package org.neil.imagej;

import ij.IJ;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class ImageJInstallTest {
  @Test
  public void testInstall() {
    assertNotNull(IJ.getInstance());
  }
}
