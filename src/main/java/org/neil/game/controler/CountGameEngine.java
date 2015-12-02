package org.neil.game.controler;

import org.neil.game.model.Position;

import java.awt.Button;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by neilsharpe on 11/22/15.
 */
public class CountGameEngine extends GameEngineAbstract {
  private AtomicBoolean continueProcessing = new AtomicBoolean(true);
  private AtomicInteger iterations;
  private ExecutorService executorService;

  public CountGameEngine(GameRule gameRule,
                         Integer iterations){
    this(gameRule, Collections.emptySet(),iterations);
  }

  public CountGameEngine(GameRule gameRule,
                         Collection<Position> positions,
                         Integer iterations) {
    super(gameRule);
    this.iterations = new AtomicInteger(iterations);
    setPositions(positions.stream().collect(Collectors.toSet()));
  }

  public Set<Position> runGame(){
    while(continueProcessing.get() && iterations.getAndDecrement() > 0){
      setPositions(getGameRule().nextStep(getPositions())
              .collect(Collectors.toSet()));
    }
    return getPositions();
  }

  public Integer getNumberOfIterationsRemaining(){
    return iterations.get();
  }

  @Override
  public void start() {
    stop();
    continueProcessing.set(true);
    executorService = Executors.newSingleThreadExecutor();
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        runGame();
      }
    });
  }

  @Override
  public void stop() {
    if(executorService!=null){
      executorService.shutdown();
    }
    continueProcessing.set(false);
  }
}
