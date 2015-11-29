package org.neil.game.ui;

import org.neil.game.controler.TimedGameEngine;

import java.util.Collections;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class TimedGameEngineControlPanel extends EngineControlPanel {
  private Integer framesPerSecond = 12;

  public TimedGameEngineControlPanel(){
    super();
    setGameEngine(new TimedGameEngine(getGameRulePanel().getGameRule(),300, Collections.EMPTY_SET));
  }

  private Integer millisecondDelay() {
    return 1000 / framesPerSecond;
  }
}
