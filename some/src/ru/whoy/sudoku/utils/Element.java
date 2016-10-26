package ru.whoy.sudoku.utils;

import java.util.Set;

/**
 * Created by sbt-sergunin-sv on 20.10.2016.
 */
public class Element {

    private Long value;
    private Set<Long> posibleValues;
    //private ElementPosition position;

    // собирается из 2х чсисел 0-8
    private int position;

    // // TODO: 22.10.2016 валидация введённых значений
    public Element(Long value, int rowNumber, int lineNumber) {
        this.value = value;
        position = calculatePosition(rowNumber, lineNumber);
    }

    private int calculatePosition(int rowNumber, int lineNumber) {
        // // TODO: 22.10.2016 заменить проверку после введения валидации
        if (rowNumber < 0 || rowNumber > 8 || lineNumber < 0 || lineNumber > 8)
            throw new IllegalArgumentException();
        return 0;
    }
}