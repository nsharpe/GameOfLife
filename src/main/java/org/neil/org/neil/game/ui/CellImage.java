package org.neil.org.neil.game.ui;

import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import org.neil.game.Position;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class CellImage {
  Integer cellPixelSize = 25;
  Integer numberOfCells = 500;

  Set<Cell> cells;
  ImageProcessor imageProcessor = new ColorProcessor(cellPixelSize * numberOfCells, cellPixelSize * numberOfCells);

  public CellImage() {
    cells = Cell.createGrid(numberOfCells).collect(Collectors.toSet());
    resetImage();
  }

  public void resetImage() {
    imageProcessor.setColor(Color.WHITE);
    imageProcessor.drawRect(0, 0, cellPixelSize * numberOfCells, cellPixelSize * numberOfCells);
  }

  public void drawLivingCell(Cell c) {
    imageProcessor.setColor( c.isAlive ? Color.BLACK : Color.WHITE);
    imageProcessor.drawRect(c.x * cellPixelSize, c.y * cellPixelSize, cellPixelSize, cellPixelSize);
  }

  public void drawLivingCells(Stream<Cell> cells) {
    cells.filter(x -> x.isAlive).forEach(x -> drawLivingCell(x));
  }

  public Stream<Position> getAlivePositions(){
    return cells.stream().filter(Cell::isAlive).map(Cell::toPosition);
  }

  public void matchPositions(Set<Position> positions){
    cells.stream().forEach(x -> positions.contains(x.toPosition()));
  }
}
