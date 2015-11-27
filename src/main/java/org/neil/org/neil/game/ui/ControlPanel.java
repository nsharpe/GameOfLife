package org.neil.org.neil.game.ui;


import org.neil.game.GameEngine;
import org.neil.game.RandomGridSetup;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Button;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Created by neilsharpe on 11/25/15.
 */
public class ControlPanel extends JPanel {

  private GameEngine gameEngine;
  private Integer rows, columns;
  private Double randomRatio = 0.5;

  public ControlPanel(GameEngine gameEngine, Integer rows, Integer columns) {
    if (gameEngine == null) {
      throw new NullPointerException("gameEngine");
    }
    this.rows = rows;
    this.columns = columns;
    this.gameEngine = gameEngine;
    add(setToStart(new Button()));
    add(createRandomSection());

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
  }

  private Button setToStart(Button button) {
    button.setLabel("Start");
    removeActionListeners(button);
    button.addActionListener(x -> {
      gameEngine.start();
      setToStop(button);
    });
    return button;
  }

  private Button setToStop(Button button) {
    button.setLabel("Stop");
    removeActionListeners(button);
    button.addActionListener(x -> {
      gameEngine.stop();
      setToStart(button);
    });
    return button;
  }

  private JPanel createRandomSection() {
    JPanel toReturn = new JPanel();
    toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.PAGE_AXIS));
    toReturn.add(createRandomizeButton());
    toReturn.add(getRandomRatioTextField());
    return toReturn;
  }

  private Button createRandomizeButton() {
    Button toReturn = new Button("Randomize");
    toReturn.addActionListener(x -> gameEngine.setPositions(
            RandomGridSetup.makeMap(randomRatio, rows, columns)
                    .collect(Collectors.toSet())));
    return toReturn;
  }

  private JTextField getRandomRatioTextField(){
    JTextField toReturn = new JTextField();
    toReturn.setText("0.5");
    toReturn.getDocument().addDocumentListener(new DocumentListener() {

      public void removeUpdate(DocumentEvent e) {
        setRandomRatio(toReturn);
      }

      public void insertUpdate(DocumentEvent e) {
        setRandomRatio(toReturn);
      }

      public void changedUpdate(DocumentEvent e) {
        setRandomRatio(toReturn);
      }
    });
    return toReturn;
  }

  private void removeActionListeners(Button button) {
    Arrays.stream(button.getActionListeners())
            .forEach(x -> button.removeActionListener(x));
  }

  private void setRandomRatio(JTextField s){
    try {
      randomRatio = Double.parseDouble(s.getText());
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(new JLabel("Error Message"),"The following is not a valid ratio" + s.getText());
      s.setText(String.valueOf(randomRatio));
    }
  }

}
