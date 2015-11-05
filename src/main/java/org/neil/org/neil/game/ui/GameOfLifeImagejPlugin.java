package org.neil.org.neil.game.ui;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class GameOfLifeImagejPlugin implements PlugIn {

  @Override
  public void run(String arg) {
    ImagePlus imp = IJ.getImage();
    IJ.run(imp, "Invert", "");
    IJ.wait(1000);
    IJ.run(imp, "Invert", "");
    CellImage image = new CellImage();
  }
}
