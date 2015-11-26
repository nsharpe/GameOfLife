package org.neil.org.neil.game.ui;

import org.neil.game.Position;

import java.awt.image.BufferedImage;
import java.util.stream.Stream;

/**
 * Created by neilsharpe on 11/25/15.
 */
public interface ImageProcessor {

  BufferedImage toImage(Stream<Position> positions);

  Double imageWidth();
  Double imageHeight();
}
