package org.neil.game.ui;

import org.neil.game.controler.GameRule;
import org.neil.game.controler.StepRule;

import javax.swing.JPanel;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class GameRulePanel extends JPanel {
  private GameRule gameRule = StepRule.CONWAYS_GAME_OF_LIFE;

  public GameRule getGameRule(){
    return gameRule;
  }
}
