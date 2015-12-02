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

/**
 * Created by neilsharpe on 11/28/15.
 */
public abstract class EngineControlPanel extends JPanel {
  private GameEngine gameEngine;
  private GameRulePanel gameRulePanel;
  private Collection<Position> currentPositions = Collections.emptyList();
  private Boolean isRunning = false;
  private Button startStopButton = new Button();

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
    if(isRunning){
      this.gameEngine.start();
    }
  }

  public void add(GameEngine.Processable processable){
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
    setToStart(startStopButton);
  }

  private Button setToStart(Button button) {
    button.setLabel("Start");
    removeActionListeners(button);
    button.addActionListener(x -> {
      getGameEngine().start();
      setToStop(button);
      isRunning = true;
    });
    return button;
  }

  private Button setToStop(Button button) {
    button.setLabel("Stop");
    removeActionListeners(button);
    button.addActionListener(x -> {
      getGameEngine().stop();
      setToStart(button);
      isRunning = false;
    });
    return button;
  }

  private void removeActionListeners(Button button) {
    Arrays.stream(button.getActionListeners())
            .forEach(x -> button.removeActionListener(x));
  }
}
