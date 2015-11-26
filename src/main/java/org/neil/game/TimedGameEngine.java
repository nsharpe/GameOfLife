package org.neil.game;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/21/15.
 */
public class TimedGameEngine extends  GameEngineAbstract {
  private Timer timer;
  private volatile Set<Position> currentPosition;

  public TimedGameEngine(GameRule gameRule,
                         Integer delayInMilliseconds,
                         Set<Position> positions) {
    super(gameRule);
    this.currentPosition = positions;
    timer = new Timer(delayInMilliseconds, timerAction() );
    timer.setRepeats(true);
    timer.setCoalesce(true);
  }

  private ActionListener timerAction(){
    return e -> {
      currentPosition =
              getGameRule().nextStep(currentPosition)
                      .collect(Collectors.toSet());
      process(currentPosition);
    };
  }

  public void start(){
    timer.start();
  }

  public void stop(){
    timer.stop();
  }
}
