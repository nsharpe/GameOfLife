package org.neil.game.ui;

import org.neil.game.controler.GameEngine;
import org.neil.game.controler.GameRule;
import org.neil.game.model.Position;

import javax.swing.JPanel;
import java.awt.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by neilsharpe on 11/28/15.
 */
public abstract class EngineControlPanel extends JPanel {
  private GameEngine gameEngine;
  private GameRulePanel gameRulePanel;
  private Collection<Position> currentPositions = Collections.emptyList();

  public EngineControlPanel(){
    add(setToStart(new Button()));
    this.gameRulePanel = new GameRulePanel();
    add(gameRulePanel);
  }

  private Collection<GameEngine.Processable> processables = new ArrayList<>();

  protected void setGameEngine(GameEngine gameEngine){
    if(this.gameEngine!=null){
      gameEngine.stop();
    }
    this.gameEngine = gameEngine;
    this.gameEngine.addListener(x->currentPositions=x);
    this.gameEngine.setPositions(new HashSet<>(currentPositions));
    processables.forEach(x->gameEngine.addListener(x));
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

  private Button setToStart(Button button) {
    button.setLabel("Start");
    removeActionListeners(button);
    button.addActionListener(x -> {
      getGameEngine().start();
      setToStop(button);
    });
    return button;
  }

  private Button setToStop(Button button) {
    button.setLabel("Stop");
    removeActionListeners(button);
    button.addActionListener(x -> {
      getGameEngine().stop();
      setToStart(button);
    });
    return button;
  }

  private void removeActionListeners(Button button) {
    Arrays.stream(button.getActionListeners())
            .forEach(x -> button.removeActionListener(x));
  }
}
