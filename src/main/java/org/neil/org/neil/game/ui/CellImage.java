package org.neil.org.neil.game.ui;

import org.neil.game.Position;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/3/15.
 */
public class CellImage {
  private Integer cellPixelSize;
  private Integer columns;
  private Integer rows;

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
    image = new Mat(new Size(imageWidth()+1, imageHeight()+1), CvType.CV_8UC3);
    resetImage();
  }

  public void resetImage() {
    IntStream.range(0,rows).asDoubleStream()
            .forEach(x->{
              IntStream.range(0,columns).asDoubleStream()
                      .forEach(y->drawSquare(x,y,WHITE));
            });
    drawGrid();
  }

  public void drawImage(Stream<Position> positions){
    resetImage();
    positions.forEach(x->draw(x));
  }

  public Double imageWidth() {
    return Double.valueOf(columns * cellPixelSize);
  }

  public Double imageHeight() {
    return Double.valueOf(rows * cellPixelSize);
  }

  public Double cellPixelSize() {
    return Double.valueOf(cellPixelSize);
  }

  public Mat image() {
    return image;
  }

  private void drawGrid() {
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
