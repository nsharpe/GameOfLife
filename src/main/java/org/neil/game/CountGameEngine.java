package org.neil.game;

import org.neil.game.model.Position;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/22/15.
 */
public class CountGameEngine extends GameEngineAbstract {
  private AtomicBoolean continueProcessing = new AtomicBoolean(true);
  private AtomicInteger iterations;

  public CountGameEngine(GameRule gameRule,
                         Collection<Position> positions,
                         Integer iterations) {
    super(gameRule);
    this.iterations = new AtomicInteger(iterations);
    setPositions(positions.stream().collect(Collectors.toSet()));
  }

  public Set<Position> runGame(){
    while(continueProcessing.get() && iterations.getAndDecrement() >= 0){
      setPositions(getGameRule().nextStep(getPositions())
              .collect(Collectors.toSet()));
    }
    return getPositions();
  }


  @Override
  public void start() {
    continueProcessing.set(true);
    runGame();
  }

  @Override
  public void stop() {
    continueProcessing.set(false);
  }
}
