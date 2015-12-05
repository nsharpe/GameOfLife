package org.neil.game.ui;

import org.neil.game.controler.GameEngine;
import org.neil.game.RandomGridSetup;
import org.neil.game.controler.StepRule;
import org.neil.game.controler.TimedGameEngine;
import org.neil.game.model.Position;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/25/15.
 */
public class CellularAutomataJFrame extends JFrame{

  public final static CellularAutomataJFrame MAIN = new CellularAutomataJFrame();

  private JLabel imageContainer = new JLabel();
  private ImageProcessor imageProcessor;
  private Integer rows = 100;
  private Integer columns = 100;
  private Collection<Position> currentPosition;
  private ControlPanel controlPanel;

  private CellularAutomataJFrame(){

    imageProcessor = new CellImage(rows,columns,5);
    controlPanel = new ControlPanel(rows,columns);
    setSize(imageProcessor.imageWidth().intValue()+250,
            imageProcessor.imageHeight().intValue() + 50);
    setLayout(new FlowLayout());
    add(controlPanel);
    add(imageContainer);
    setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    imageContainer.setIcon(new ImageIcon(imageProcessor.toImage(Stream.empty())));

    controlPanel.add(toProcess -> {
      Collection<Position> result = controlPanel.getCurrentEngine().getGameEngine().getPositions();
      if(result != currentPosition) {
        imageContainer.setIcon(
                new ImageIcon(imageProcessor.toImage(result.stream())));
      }
      currentPosition = result;
    });

    controlPanel.randomizeBoard();
  }

  public Collection<Position> getCurrentPosition() {
    return Collections.unmodifiableCollection(currentPosition);
  }

  public void setCurrentPosition(Collection<Position> currentPosition) {
    this.currentPosition = currentPosition;
  }

  private GameEngine defaultGameEngine(){
    return new TimedGameEngine(StepRule.CONWAYS_GAME_OF_LIFE,
            100,
            RandomGridSetup.makeMap(0.5,rows,columns)
                    .collect(Collectors.toSet()));
  }


}
