package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        File outputFile2 = new File(args[2]);
        File outputFile3 = new File(args[3]);
        File imageGrey = new File(args[4]);
        int threshold = Integer.parseInt(args[5]);
        Grey.toGrey(inputFile, imageGrey);
        Difference.apply(imageGrey, outputFile, outputFile2, outputFile3, threshold);
    }
}