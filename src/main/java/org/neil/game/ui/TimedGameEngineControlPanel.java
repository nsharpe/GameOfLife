package org.neil.game.ui;

import org.neil.game.controler.GameEngine;
import org.neil.game.controler.TimedGameEngine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Button;

/**
 * Created by neilsharpe on 11/28/15.
 */
public class TimedGameEngineControlPanel extends EngineControlPanel {
  private Double framesPerSecond = 12.0;

  public TimedGameEngineControlPanel(){
    super();
    setGameEngine(createGameEngine());
    add(createSetFramesPerSecondButton());
    add(createFramesPerSecondTextField());
  }

  private Button createSetFramesPerSecondButton(){
    Button toReturn = new Button("Frames/Second");
    toReturn.addActionListener(x->resetGameEngine());
    return toReturn;
  }

  private JTextField createFramesPerSecondTextField(){
    JTextField toReturn = new JTextField();
    toReturn.setText(framesPerSecond.toString());
    toReturn.getDocument().addDocumentListener(new DocumentListener() {

      public void removeUpdate(DocumentEvent e) {
        setFramesPerSecond(toReturn);
      }

      public void insertUpdate(DocumentEvent e) {
        setFramesPerSecond(toReturn);
      }

      public void changedUpdate(DocumentEvent e) {
        setFramesPerSecond(toReturn);
      }
    });
    return toReturn;
  }

  private void setFramesPerSecond(JTextField jTextField){
    try {
      if(!jTextField.getText().matches("\\s*")) {
        framesPerSecond = Double.parseDouble(jTextField.getText());
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(new JLabel("Error Message"),"The following is not a valid frame per second: " + jTextField.getText());
      jTextField.setText(String.valueOf(framesPerSecond));
    }
  }

  @Override
  public GameEngine createGameEngine(){
    return new TimedGameEngine(getGameRulePanel().getGameRule(),millisecondDelay());
  }

  private Integer millisecondDelay() {
    return (int) (1000.0 / framesPerSecond);
  }
}
