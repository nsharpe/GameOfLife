package org.neil;

import org.neil.game.RandomGridSetup;
import org.neil.org.neil.game.ui.CellImage;
import org.neil.org.neil.game.ui.OpenCVisualiser;
import org.opencv.core.Core;

/**
 * Created by neilsharpe on 11/10/15.
 */
public class HelloOpenCV {
  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

  public static void main(String[] args) {

    CellImage ci = new CellImage(150,150,5);

    OpenCVisualiser openCVisualiser =
            new OpenCVisualiser(ci.imageWidth(),ci.imageHeight());

    ci.drawImage(RandomGridSetup.makeMap(0.5,150,150));

    openCVisualiser.displayImage(ci.image());
  }
}
