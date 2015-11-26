package org.neil.org.neil.game.ui;

import org.neil.game.GameEngine;
import org.neil.game.RandomGridSetup;
import org.neil.game.StepRule;
import org.neil.game.TimedGameEngine;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/25/15.
 */
public class CellularAutomataJFrame extends JFrame{

  public final static CellularAutomataJFrame MAIN = new CellularAutomataJFrame();

  private GameEngine gameEngine = defaultGameEngine();

  private CellularAutomataJFrame(){
    setLayout(new FlowLayout());

    //Prevents public instantiation

  }

  private static GameEngine defaultGameEngine(){
    return new TimedGameEngine(StepRule.CONWAYS_GAME_OF_LIFE,
            100,
            RandomGridSetup.makeMap(0.5,150,150)
                    .collect(Collectors.toSet()));
  }


}
