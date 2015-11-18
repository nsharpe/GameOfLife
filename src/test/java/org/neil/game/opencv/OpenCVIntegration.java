package org.neil.game.opencv;

import org.junit.Test;
import org.opencv.core.Core;

import static org.junit.Assert.assertNotNull;

/**
 * Created by neilsharpe on 11/17/15.
 */
public class OpenCVIntegration {
  @Test
  public void testOpenCVCoreIntegration(){
    assertNotNull(Core.NATIVE_LIBRARY_NAME);
  }
}
