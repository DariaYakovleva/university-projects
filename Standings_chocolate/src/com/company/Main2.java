package com.company;

import java.io.FileNotFoundException;

/**
 * Created by Daria on 11.12.2016.
 */
public class Main2 {
    public static void main(String argv[]) {
        Chocolate chocolate = new Chocolate();
        try {
            chocolate.downloadStanding();
            chocolate.countDifference();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
