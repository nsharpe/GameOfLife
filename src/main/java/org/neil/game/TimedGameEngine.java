package org.neil.game;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/21/15.
 */
public class TimedGameEngine implements GameEngine {
  private final GameRule gameRule;
  private final List<Processable> toProcess =
          Collections.synchronizedList(new ArrayList<>());
  private Timer timer;
  private volatile Set<Position> currentPosition;

  public TimedGameEngine(GameRule gameRule,
                         Integer delayInMilliseconds,
                         Set<Position> positions) {
    this.currentPosition = positions;
    this.gameRule = gameRule;
    timer = new Timer(delayInMilliseconds, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currentPosition =
                gameRule.nextStep(currentPosition)
                        .collect(Collectors.toSet());
        toProcess.stream().forEach(x->x.process(currentPosition));
      }
    });
    timer.setRepeats(true);
    timer.setCoalesce(true);
    start();
  }

  public void start(){
    timer.start();
  }

  public void stop(){
    timer.stop();
  }

  @Override
  public void addListener(Processable toProcess) {
    this.toProcess.add(toProcess);
  }
}
