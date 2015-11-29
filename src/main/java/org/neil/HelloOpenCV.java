package org.neil;

import org.neil.game.ui.CellImage;
import org.neil.game.ui.CellularAutomataJFrame;
import org.opencv.core.Core;

import javax.swing.JFrame;

/**
 * Created by neilsharpe on 11/10/15.
 */
public class HelloOpenCV {
  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

  public static void main(String[] args) {

    CellImage ci = new CellImage(150,150,5);
    ci.setShowGrid(false);

    CellularAutomataJFrame.MAIN.setVisible(true);
    CellularAutomataJFrame.MAIN
            .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
