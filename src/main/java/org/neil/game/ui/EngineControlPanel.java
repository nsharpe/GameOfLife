package org.neil.game.ui;

import org.neil.game.controler.GameEngine;
import org.neil.game.model.Position;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by neilsharpe on 11/28/15.
 */
public abstract class EngineControlPanel extends JPanel {
  private volatile GameEngine gameEngine;
  private GameRulePanel gameRulePanel;
  private Collection<Position> currentPositions = Collections.emptyList();
  private AtomicBoolean isRunning = new AtomicBoolean(false);
  private Button startStopButton = new Button();
  private volatile ExecutorService engineProcessor = Executors.newSingleThreadExecutor();

  private Collection<GameEngine.Processable> processables = new ArrayList<>();

  public EngineControlPanel(){
    add(setToStart(startStopButton));
    this.gameRulePanel = new GameRulePanel();
    add(gameRulePanel);
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
  }

  public abstract GameEngine createGameEngine();

  public void resetGameEngine(){
    setGameEngine(createGameEngine());
  }

  protected void setGameEngine(GameEngine gameEngine){
    if(this.gameEngine!=null){
      this.gameEngine.stop();
    }
    this.gameEngine = gameEngine;
    this.gameEngine.addListener(x->currentPositions=x);
    this.gameEngine.setPositions(new HashSet<>(currentPositions));
    processables.forEach(x->gameEngine.addListener(x));
    if(isRunning.get()){
      this.gameEngine.start();
    }
  }

  public void add(GameEngine.Processable processable){
    processable = new ThreadProcessable(processable,this);
    processables.add(processable);
    if(gameEngine!=null) {
      gameEngine.addListener(processable);
    }
  }

  public GameRulePanel getGameRulePanel() {
    return gameRulePanel;
  }

  public GameEngine getGameEngine() {
    return gameEngine;
  }

  public void start(){
    setToStop(startStopButton);
  }

  public void stop(){
    stopAction();
  }

  private Button setToStart(Button button) {
    button.setLabel("Start");
    removeActionListeners(button);
    button.addActionListener(x -> {
      startAction();
    });
    return button;
  }

  private void startAction(){
    getGameEngine().start();
    setToStop(startStopButton);
    isRunning.set( true);
  }

  private Button setToStop(Button button) {
    button.setLabel("Stop");
    removeActionListeners(button);
    button.addActionListener(x -> {
      stopAction();
    });
    return button;
  }

  private void stopAction(){
    getGameEngine().stop();
    setToStart(startStopButton);
    isRunning.set(false);

    engineProcessor.shutdown();
    engineProcessor = Executors.newSingleThreadExecutor();
  }

  private void removeActionListeners(Button button) {
    Arrays.stream(button.getActionListeners())
            .forEach(x -> button.removeActionListener(x));
  }

  public static class ThreadProcessable implements GameEngine.Processable{
    GameEngine.Processable wrapper;
    EngineControlPanel controlPanel;

    public ThreadProcessable(GameEngine.Processable wrapper, EngineControlPanel toRun) {
      this.wrapper = wrapper;
      this.controlPanel = toRun;
    }

    @Override
    public void process(Collection<Position> toProcess) {
      controlPanel.engineProcessor.submit((Runnable) () -> {
        if(!controlPanel.engineProcessor.isShutdown()) {
          wrapper.process(toProcess);
        }
      });
    }
  }
}
