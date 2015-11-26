package org.neil.org.neil.game.ui;


import org.neil.game.GameEngine;
import org.neil.game.RandomGridSetup;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Button;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by neilsharpe on 11/25/15.
 */
public class ControlPanel extends JPanel{

  private GameEngine gameEngine;
  private Integer rows, columns;

  public ControlPanel(GameEngine gameEngine,Integer rows, Integer columns){
    if(gameEngine==null){
      throw new NullPointerException("gameEngine");
    }
    this.rows = rows;
    this.columns = columns;
    this.gameEngine = gameEngine;
    add(setToStart(new Button()));
    add(createRandomizeButton());

    setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
  }

  private Button setToStart(Button button){
    button.setLabel("Start");
    removeActionListeners(button);
    button.addActionListener(x->{
      gameEngine.start();
      setToStop(button);
    });
    return button;
  }

  private Button setToStop(Button button){
    button.setLabel("Stop");
    removeActionListeners(button);
    button.addActionListener(x->{
      gameEngine.stop();
      setToStart(button);
    });
    return button;
  }

  private Button createRandomizeButton(){
    Button toReturn = new Button("Randomize");
    toReturn.addActionListener( x -> gameEngine.setPositions(RandomGridSetup.makeMap(0.5,rows,columns)
            .collect(Collectors.toSet()) ));
    return toReturn;
  }

  private void removeActionListeners(Button button){
    Arrays.stream(button.getActionListeners())
            .forEach(x->button.removeActionListener(x));
  }



}
