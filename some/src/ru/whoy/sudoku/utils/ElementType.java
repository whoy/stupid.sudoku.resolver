package ru.whoy.sudoku.utils;

/**
 * Created by WHOY on 21.10.2016.
 *
 */
public enum ElementType {

    LINE(0b0000_0000_0000_1111, 0),
    ROW(0b0000_0000_1111_0000, 4),
    SQUARE(0b0000_1111_0000_0000, 8),
    DEFAULT(0b1111_0000_0000_0000, 12);

    // сдвиг чтоыб получить осмысленнео число от 0 до 8
    private int shift;
    // маска чтобы получать осмысленные числа
    private int mask;

    ElementType(int shift, int mask) {
        this.shift = shift;
        this.mask = mask;
    }
}
