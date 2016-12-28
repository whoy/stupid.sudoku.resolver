package ru.whoy.sudokuMass;

/**
 * Created by WHOY on 22.10.2016.
 */
public class Test {

    public static Long[][] superHard = {{8L,   null, null, null, null, null, null, null, null},
                                        {null, null,   3L,   6L, null, null, null, null, null},
                                        {null,   7L, null, null,   9L, null,   2L, null, null},
                                        {null,   5L, null, null, null,   7L, null, null, null},
                                        {null, null, null, null,   4L,   5L,   7L, null, null},
                                        {null, null, null,   1L, null, null, null,   3L, null},
                                        {null, null,   1L, null, null, null, null,   6L,   8L},
                                        {null, null,   8L,   5L, null, null, null,   1L, null},
                                        {null,   9L, null, null, null, null,   4L, null, null}};

    public static Long[][] superNorm = {{  2L, null,   4L,   6L,   3L, null, null, null, null},
                                        {  7L,   1L, null,   5L,   9L, null, null, null, null},
                                        {null, null, null, null,   7L, null,   2L, null, null},
                                        {  1L,   5L,   7L, null, null, null, null,   6L,   3L},
                                        {null,   9L, null, null,   6L,   3L,   5L, null,   1L},
                                        {null, null, null, null,   4L, null, null,   8L,   9L},
                                        {null,   4L,   5L,   9L,   1L,   7L, null,   3L, null},
                                        {null,   2L, null, null, null, null,   8L, null, null},
                                        {  6L, null, null,   4L, null,   8L, null,   9L, null}};


    private static Long[][] field = superHard;

    public static void main(String[] args) {
        new Sudoku(Test.field).show();
    }


}
