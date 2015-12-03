package org.neil.game.ui;

import org.neil.game.controler.CountGameEngine;
import org.neil.game.controler.GameEngine;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Button;

/**
 * Created by neilsharpe on 11/29/15.
 */
public class CountGameEngineControlPanel extends  EngineControlPanel {

  private Integer numberOfIterations = 1000;
  private JLabel numberOfIterationsRemaining = new JLabel();

  public CountGameEngineControlPanel(){
    super();
    resetGameEngine();
    add(createNumberOfIterationsButton());
    add(createNumberOfIterationsTextField());
    add(new JLabel("Number of iterations"));
    add(numberOfIterationsRemaining);
  }

  @Override
  public GameEngine createGameEngine() {
    CountGameEngine toReturn = new CountGameEngine(getGameRulePanel().getGameRule(),
            numberOfIterations);
    numberOfIterationsRemaining.setText( "remaining: "+toReturn.getNumberOfIterationsRemaining());
    toReturn.addListener(x->
    {
      numberOfIterationsRemaining
            .setText("remaining: "+toReturn.getNumberOfIterationsRemaining());
      if(toReturn.getNumberOfIterationsRemaining() <= 0){
        stop();
      }
    });
    return toReturn;
  }

  private Button createNumberOfIterationsButton(){
    Button toReturn = new Button("Number Of Iterations");
    toReturn.addActionListener(x->resetGameEngine());
    return toReturn;
  }

  private JTextField createNumberOfIterationsTextField(){
    JTextField toReturn = new JTextField();
    toReturn.setText(numberOfIterations.toString());
    toReturn.getDocument().addDocumentListener(new DocumentListener() {

      public void removeUpdate(DocumentEvent e) {
        setNumberOfIterations(toReturn);
      }

      public void insertUpdate(DocumentEvent e) {
        setNumberOfIterations(toReturn);
      }

      public void changedUpdate(DocumentEvent e) {
        setNumberOfIterations(toReturn);
      }
    });
    return toReturn;
  }

  private void setNumberOfIterations(JTextField jTextField){
    try {
      if(!jTextField.getText().matches("\\s*")) {
        numberOfIterations = Integer.valueOf(jTextField.getText());
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(new JLabel("Error Message"),"The following is not a valid frame per second" + jTextField.getText());
      jTextField.setText(String.valueOf(numberOfIterations));
    }
  }
}
