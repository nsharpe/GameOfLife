package org.neil.org.neil.game.ui;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by neilsharpe on 11/18/15.
 */
public class OpenCVisualiser {

  private JLabel imageContainer = new JLabel();

  public void displayImage(Mat m){
    imageContainer.setIcon(new ImageIcon(toBufferedImage(m)));
  }

  public BufferedImage toBufferedImage(Mat m) {
    int type = BufferedImage.TYPE_BYTE_GRAY;

    if ( m.channels() >= 1 ) {
      type = BufferedImage.TYPE_3BYTE_BGR;

    }

    byte [] b = new byte[m.channels()*m.cols()*m.rows()];

    m.get(0,0,b); // get all the pixels

    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);

    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

    System.arraycopy(b, 0, targetPixels, 0, b.length);

    return image;
  }
}
