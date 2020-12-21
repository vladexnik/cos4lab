package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Difference {

    private static int WIDTH, HEIGHT = 0;
    private static BufferedImage src = null;
    private static BufferedImage out1, out2, out3 = null;

    private static void createImage(File image) throws IOException {
        src = ImageIO.read(image);
        WIDTH = src.getWidth();
        HEIGHT = src.getHeight();
        out1 = new BufferedImage(WIDTH, HEIGHT, src.getType());
        out2 = new BufferedImage(WIDTH, HEIGHT, src.getType());
        out3 = new BufferedImage(WIDTH, HEIGHT, src.getType());
    }

    static void apply(File image, File output, File output2, File output3, int threshold) throws IOException {
        createImage(image);
        int[][] pixels = new int[2][2];
        int[] pixelsY = new int[2];
        int[] pixelsX = new int[2];
        int gx;
        int gy;
        for (int i = 0; i < HEIGHT - 1; i++) {
            for (int j = 0; j < WIDTH - 1; j++) {
                for (int col = i; col < i + 2; col++) {
                    for (int row = j; row < j + 2; row++) {
                        Color color = new Color(src.getRGB(row, col));
                        pixels[col - i][row - j] = color.getBlue();
                        pixelsX[col - i] = color.getBlue();
                        pixelsY[row - j] = color.getBlue();
                    }
                }
                gx = (pixels[1][1] - pixels[0][0]) <= threshold ? pixels[1][1] - pixels[0][0] : pixels[0][0];
                gy = (pixels[1][0] - pixels[0][1]) <= threshold ? pixels[1][0] - pixels[0][1] : pixels[0][1];
                int COLOR = (int) Math.sqrt(Math.pow(gx, 2) + Math.pow(gy, 2));
                if (COLOR > 255) COLOR = 255;
                Color newColor = new Color(COLOR, COLOR, COLOR);
                out1.setRGB(j, i, newColor.getRGB());
                out2.setRGB(j, i, fill(gx, pixelsX).getRGB());
                out3.setRGB(j, i, fill(gy, pixelsY).getRGB());
            }
        }
        ImageIO.write(out1, "bmp", output);
        ImageIO.write(out2, "bmp", output2);
        ImageIO.write(out3, "bmp", output3);
    }

    private static Color fill(int g, int[] pixels) {
        g = pixels[1] - pixels[0];
        if (g > 255) g = 255;
        if (g < 0) g = 0;
        return new Color(g, g, g);
    }
}