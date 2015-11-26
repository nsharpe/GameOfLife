package org.neil.org.neil.game.ui;

import org.neil.game.GameEngine;
import org.neil.game.RandomGridSetup;
import org.neil.game.StepRule;
import org.neil.game.TimedGameEngine;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/25/15.
 */
public class CellularAutomataJFrame extends JFrame{

  public final static CellularAutomataJFrame MAIN = new CellularAutomataJFrame();

  private JLabel imageContainer = new JLabel();
  private GameEngine gameEngine;
  private ImageProcessor imageProcessor;
  private Integer rows = 100;
  private Integer columns = 100;

  private CellularAutomataJFrame(){
    gameEngine = defaultGameEngine();
    imageProcessor = new CellImage(rows,columns,5);
    setSize(imageProcessor.imageWidth().intValue()+100,
            imageProcessor.imageHeight().intValue() + 10);
    setLayout(new FlowLayout());
    add(new ControlPanel(gameEngine,rows,columns));
    add(imageContainer);
    setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    imageContainer.setIcon(new ImageIcon(imageProcessor.toImage(Stream.empty())));

    gameEngine.addListener(toProcess ->
      imageContainer.setIcon(
              new ImageIcon(imageProcessor.toImage(toProcess.stream())))
    );
  }

  private GameEngine defaultGameEngine(){
    return new TimedGameEngine(StepRule.CONWAYS_GAME_OF_LIFE,
            100,
            RandomGridSetup.makeMap(0.5,rows,columns)
                    .collect(Collectors.toSet()));
  }


}
