package org.neil.org.neil.game.ui;

import org.neil.game.Position;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class CellImage {
  private Integer cellPixelSize;
  private Integer columns;
  private Integer rows;

  private Set<Cell> cells;
  private Mat image;

  final Scalar WHITE = new Scalar(0.0,0.0,0.0);
  final Scalar BLACK = new Scalar(255.0,255.0,255.0);

  public CellImage(Integer row, Integer column,Integer cellPixelSize) {
    if(row == null || column == null || cellPixelSize == null){
      throw new NullPointerException();
    }
    this.rows = row;
    this.columns = column;
    this.cellPixelSize = cellPixelSize;
    image = new Mat(new Size(imageWidth(),imageHeight()), CvType.CV_64FC4);
    resetImage();
  }

  public void resetImage() {
    new Scalar(0.0,0.0,0.0);
    Imgproc.rectangle(image,topLeftCorner(),bottomRightCorner(),WHITE);
  }

  public Double imageWidth(){
    return Double.valueOf( columns * cellPixelSize );
  }

  public Double imageHeight(){
    return Double.valueOf( rows * cellPixelSize );
  }

  public Point topLeftCorner(){
    return new Point(0,0);
  }

  public Point bottomRightCorner(){
    return new Point(imageWidth().intValue(),imageHeight().intValue());
  }

  public Mat image(){
    return image;
  }

  public void drawLivingCell(Cell c) {

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
