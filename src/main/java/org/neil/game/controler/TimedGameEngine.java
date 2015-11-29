package org.neil.game.controler;

import org.neil.game.model.Position;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/21/15.
 */
public class TimedGameEngine extends  GameEngineAbstract {
  private Timer timer;

  public TimedGameEngine(GameRule gameRule,
                         Integer delayInMilliseconds,
                         Set<Position> positions) {
    super(gameRule);
    setPositions(positions);
    timer = new Timer(delayInMilliseconds, timerAction() );
    timer.setRepeats(true);
    timer.setCoalesce(true);
  }

  private ActionListener timerAction(){
    return e -> {
      setPositions(
              getGameRule().nextStep(getPositions())
                      .collect(Collectors.toSet()));
    };
  }

  public void start(){
    timer.start();
  }

  public void stop(){
    timer.stop();
  }
}
