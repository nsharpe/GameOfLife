package org.neil.game.ui;


import org.neil.game.controler.GameEngine;
import org.neil.game.RandomGridSetup;
import org.neil.game.model.Position;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Created by neilsharpe on 11/25/15.
 */
public class ControlPanel extends JPanel {

  private Integer rows, columns;
  private Double randomRatio = 0.5;
  private Collection<EngineControlPanel> gameControlPannels = new ArrayList<>();
  private Collection<Position> currentPosition;

  public ControlPanel( Integer rows, Integer columns) {
    this.rows = rows;
    this.columns = columns;

    add(createGameEngineDropDown());

    add(createRandomSection());
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
  }

  public Component createGameEngineDropDown(){
    JPanel toReturn = new JPanel();
    JComboBox jComboBox = new JComboBox(new DropDownGameEngine[]{
            new DropDownGameEngine("Timed Game Engine",new TimedGameEngineControlPanel()),
            new DropDownGameEngine("Count Game Engine",new CountGameEngineControlPanel())
    });

    jComboBox.addItemListener(x->{
      if (x.getStateChange() == ItemEvent.SELECTED) {
        ((DropDownGameEngine)x.getItem()).panel.setVisible(true);
      } else {
        ((DropDownGameEngine)x.getItem()).panel.setVisible(false);
        ((DropDownGameEngine)x.getItem()).panel.getGameEngine().stop();
      }
    });

    jComboBox.setSelectedIndex(0);
    toReturn.add(jComboBox);
    IntStream.range(0,jComboBox.getItemCount())
            .forEach(x->{
              EngineControlPanel y =((DropDownGameEngine)jComboBox.getItemAt(x)).panel;
              toReturn.add(y);
              gameControlPannels.add(y);
            });
    IntStream.range(1,jComboBox.getItemCount())
            .forEach(x->((DropDownGameEngine)jComboBox.getItemAt(x)).panel.setVisible(false));
    toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.PAGE_AXIS));
    return toReturn;
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

    toReturn.addActionListener(x -> randomizeBoard());
    return toReturn;
  }

  public void randomizeBoard(){
    Set<Position> positions  = RandomGridSetup.makeMap(randomRatio, rows, columns)
            .collect(Collectors.toSet());
    gameControlPannels
            .forEach(y->y.getGameEngine()
                    .setPositions(positions));
  }

  public void add(GameEngine.Processable toProcess) {
    gameControlPannels.forEach(x->x.add(toProcess));
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
      if(!jTextField.getText().matches("\\s*")) {
        Double toSet = Double.parseDouble(jTextField.getText());
        if(toSet > 1){
          throw new IllegalStateException("Value must be less then or equal to 1");
        }
        if(toSet < 0){
          throw new IllegalStateException("Value must be greater then or equal to 0");
        }
        randomRatio = toSet;
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(new JLabel("Error Message"),"The following is not a valid ratio" + jTextField.getText());
      jTextField.setText(String.valueOf(randomRatio.toString()));
    } catch( IllegalStateException ex){
      JOptionPane.showMessageDialog(new JLabel("Error Message"),ex.getMessage());
      jTextField.setText(String.valueOf(randomRatio.toString()));
    }
  }

  private class DropDownGameEngine{
    String name;
    EngineControlPanel panel;
    public DropDownGameEngine(String name,EngineControlPanel panel){
      this.name = name;
      this.panel = panel;
    }
    public String toString(){
      return name;
    }
  }
}
