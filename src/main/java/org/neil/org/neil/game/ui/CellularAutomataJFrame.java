package org.neil.org.neil.game.ui;

import org.neil.game.GameEngine;
import org.neil.game.RandomGridSetup;
import org.neil.game.StepRule;
import org.neil.game.TimedGameEngine;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/25/15.
 */
public class CellularAutomataJFrame extends JFrame{

  public final static CellularAutomataJFrame MAIN = new CellularAutomataJFrame();

  private JLabel imageContainer = new JLabel();
  private GameEngine gameEngine = defaultGameEngine();
  private ImageProcessor imageProcessor;

  private CellularAutomataJFrame(){
    imageProcessor = new CellImage(150,150,5);
    setSize(imageProcessor.imageWidth().intValue(),
            imageProcessor.imageHeight().intValue());
    setLayout(new FlowLayout());
    add(new ControlPanel(gameEngine));
    add(imageContainer);

    gameEngine.addListener(toProcess -> {
      imageContainer.setIcon(
              new ImageIcon(imageProcessor.toImage(toProcess.stream())));
    });
  }

  private static GameEngine defaultGameEngine(){
    return new TimedGameEngine(StepRule.CONWAYS_GAME_OF_LIFE,
            100,
            RandomGridSetup.makeMap(0.5,150,150)
                    .collect(Collectors.toSet()));
  }


}
