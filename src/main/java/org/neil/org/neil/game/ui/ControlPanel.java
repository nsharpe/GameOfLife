package org.neil.org.neil.game.ui;


import org.neil.game.GameEngine;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Button;
import java.util.Arrays;


/**
 * Created by neilsharpe on 11/25/15.
 */
public class ControlPanel extends JPanel{

  private GameEngine gameEngine;

  public ControlPanel(GameEngine gameEngine){
    if(gameEngine==null){
      throw new NullPointerException("gameEngine");
    }
    this.gameEngine = gameEngine;
    add(setToStart(new Button()));

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

  private void removeActionListeners(Button button){
    Arrays.stream(button.getActionListeners())
            .forEach(x->button.removeActionListener(x));
  }

}
