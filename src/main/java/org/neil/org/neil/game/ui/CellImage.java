package org.neil.org.neil.game.ui;

import org.neil.game.model.Position;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class CellImage implements ImageProcessor{
  private Integer cellPixelSize;
  private Integer columns;
  private Integer rows;
  private Boolean showGrid = true;
  private OpenCVisualiser openCVisualiser;

  private Mat image;

  final Scalar BLACK = new Scalar(0, 0, 0);
  final Scalar WHITE = new Scalar(255, 255, 255);

  public CellImage(Integer row, Integer column, Integer cellPixelSize) {
    if (row == null || column == null || cellPixelSize == null) {
      throw new NullPointerException();
    }
    this.rows = row;
    this.columns = column;
    this.cellPixelSize = cellPixelSize;
    openCVisualiser = new OpenCVisualiser();
    resetImage();
  }

  private Mat createNewMat(){
    return new Mat(new Size(imageWidth()+1, imageHeight()+1), CvType.CV_8UC3);
  }


  public void resetImage() {
    image = createNewMat();
    IntStream.range(0,rows).asDoubleStream()
            .forEach(x->{
              IntStream.range(0,columns).asDoubleStream()
                      .forEach(y->drawSquare(x,y,WHITE));
            });
    drawGrid();
  }

  @Override
  public BufferedImage toImage(Stream<Position> positions) {
    return openCVisualiser.toBufferedImage(drawImage(positions));
  }

  public Mat drawImage(Stream<Position> positions){
    image = createNewMat();
    resetImage();
    positions.forEach(x->draw(x));
    return image;
  }

  @Override
  public Double imageWidth() {
    return Double.valueOf(columns * cellPixelSize);
  }

  @Override
  public Double imageHeight() {
    return Double.valueOf(rows * cellPixelSize);
  }

  public Double cellPixelSize() {
    return Double.valueOf(cellPixelSize);
  }

  public Mat image() {
    return image;
  }

  public Boolean getShowGrid() {
    return showGrid;
  }

  public void setShowGrid(Boolean showGrid) {
    this.showGrid = showGrid;
  }

  private void drawGrid() {
    if(!showGrid){
      return;
    }
    IntStream.range(0, rows+1)
            .asDoubleStream()
            .forEach(x -> {
              Imgproc.line(image,
                      new Point(0.0, x * cellPixelSize()),
                      new Point(imageWidth(), x * cellPixelSize()),
                      BLACK);
            });
    IntStream.range(0, columns+1)
            .asDoubleStream()
            .forEach(x -> {
              Imgproc.line(image,
                      new Point(x * cellPixelSize(), 0.0),
                      new Point(x * cellPixelSize(), imageHeight()),
                      BLACK);
            });
  }

  public void draw(Position p){
    drawSquare(p.axis(0),p.axis(1),BLACK);
  }

  public void drawSquare(Integer columns, Integer row, Scalar color){
    drawSquare(columns.doubleValue(),row.doubleValue(),color);
  }

  public void drawSquare(Double column,Double row, Scalar color) {
    Point[] points = new Point[]{new Point(row * cellPixelSize(), column * cellPixelSize()),
            new Point((1 + row) * cellPixelSize(), column * cellPixelSize()),
            new Point((1 + row) * cellPixelSize(), (1 + column) * cellPixelSize()),
            new Point(row * cellPixelSize(), (1 + column) * cellPixelSize())};
    MatOfPoint matOfPoint = new MatOfPoint(points);
    Imgproc.fillPoly(image,Arrays.asList(matOfPoint),color);
  }
}
