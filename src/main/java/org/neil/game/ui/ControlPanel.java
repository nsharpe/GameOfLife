package org.neil.game.ui;


import org.neil.game.controler.GameEngine;
import org.neil.game.RandomGridSetup;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Button;
import java.util.stream.Collectors;


/**
 * Created by neilsharpe on 11/25/15.
 */
public class ControlPanel extends JPanel {

  private Integer rows, columns;
  private Double randomRatio = 0.5;
  private EngineControlPanel gameEngineControlPanel;

  public ControlPanel( Integer rows, Integer columns) {
    this.rows = rows;
    this.columns = columns;
    gameEngineControlPanel = new TimedGameEngineControlPanel();

    add(gameEngineControlPanel);
    add(createRandomSection());

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
    toReturn.addActionListener(x -> gameEngineControlPanel.getGameEngine().setPositions(
            RandomGridSetup.makeMap(randomRatio, rows, columns)
                    .collect(Collectors.toSet())));
    return toReturn;
  }

  public void add(GameEngine.Processable toProcess) {
    gameEngineControlPanel.add(toProcess);
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

  private void setRandomRatio(JTextField jTextField){
    try {
      randomRatio = Double.parseDouble(jTextField.getText());
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(new JLabel("Error Message"),"The following is not a valid ratio" + jTextField.getText());
      jTextField.setText(String.valueOf(randomRatio));
    }
  }

}
