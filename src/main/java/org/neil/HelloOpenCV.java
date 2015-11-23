package org.neil;

import org.neil.game.GameEngine;
import org.neil.game.Position;
import org.neil.game.RandomGridSetup;
import org.neil.game.StepRule;
import org.neil.game.TimedGameEngine;
import org.neil.org.neil.game.ui.CellImage;
import org.neil.org.neil.game.ui.OpenCVisualiser;
import org.opencv.core.Core;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/10/15.
 */
public class HelloOpenCV {
  static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

  public static void main(String[] args) {

    CellImage ci = new CellImage(150,150,5);

    OpenCVisualiser openCVisualiser =
            new OpenCVisualiser(ci.imageWidth(),ci.imageHeight());

    GameEngine gameEngine =
            new TimedGameEngine(StepRule.CONWAYS_GAME_OF_LIFE,
                    100,
                    RandomGridSetup.makeMap(0.5,150,150)
                            .collect(Collectors.toSet()));
    gameEngine.addListener(toProcess -> {
      ci.drawImage(toProcess.stream());
      openCVisualiser.displayImage(ci.image());
    });
  }
}
