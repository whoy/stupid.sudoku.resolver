package ru.whoy.sudoku.utils;

import java.util.Set;

/**
 * Created by sbt-sergunin-sv on 20.10.2016.
 */
public class Element {

    private Long value;
    private Set<Long> posibleValues;
    //private ElementGroup

    public static void main(String[] args) {

        int defaultMask = 0b0000_1111_1111_1111;
        int squareMask = 0b1111_1111_0000_0000;
        int lineMask = 0b1111_0000_1111_0000;
        int rowMask = 0b1111_0000_0000_1111;

        System.out.println(Integer.toBinaryString(defaultMask | squareMask));

    }

}
