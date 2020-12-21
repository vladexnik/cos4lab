package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Grey {
    static void toGrey(File image, File output) throws IOException {
        BufferedImage si = null;
        si = ImageIO.read(image);
        int width = si.getWidth();
        int height = si.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(si.getRGB(i, j));
                int red = (int) (color.getRed() * 0.299);
                int green = (int) (color.getGreen() * 0.587);
                int blue = (int) (color.getBlue() * 0.114);
                si.setRGB(i, j, new Color(red + green + blue, red + green + blue, red + green + blue).getRGB());
            }
        }
        ImageIO.write(si, "bmp", output);
    }
}