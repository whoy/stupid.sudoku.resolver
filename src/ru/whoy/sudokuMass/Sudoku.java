package ru.whoy.sudokuMass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by WHOY on 22.10.2016.
 */
public class Sudoku {

    private final int MAGIC_NUMBER = 9;
    private final int SHORT_MAGIC_NUMBER = 3;
    private Element[][] elements = new Element[MAGIC_NUMBER][MAGIC_NUMBER];

    public Sudoku(Long[][] field) {
        // // TODO: 22.10.2016  сделать проверку введённого добра
        initializeLocalMassive(field);
        initPossibleValues(elements);
        resolve(elements);
    }

    private void initializeLocalMassive(Long[][] field) {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                elements[i][j] = new Element(field[i][j]);
            }
        }
    }

    public void show() {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                System.out.print(String.format("%6d",elements[i][j].getValue() ));
            }
            System.out.println();
        }
    }

    public void showWithValues() {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                System.out.print(String.format("%6d (%d,%d) values",elements[i][j].getValue(), i, j));
                for(Long value : elements[i][j].getPossibleValues())
                    System.out.print(String.format("%2d", value));
                System.out.println();
            }
            System.out.println();
        }
    }

    private void initPossibleValues(Element[][] elements) {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                fillPossibleValues(elements, i, j);
            }
        }
    }

    private void fillPossibleValues(Element[][] elements, int i, int j) {
        if (elements[i][j].getValue() != null) {
            elements[i][j].addPossibleValue(elements[i][j].getValue());
            elements[i][j].setResolved();
            return;
        }
        Set<Long> fullSet = createFullSet();
        excludeByRow(fullSet, elements, i, j);
        excludeByLine(fullSet, elements, i, j);
        excludeBySquare(fullSet, elements, i, j);
        elements[i][j].setPossibleValues(fullSet);
    }

    private void excludeBySquare(Set<Long> fullSet, Element[][] elements, int i, int j) {
        int lineMultiplayer = i / SHORT_MAGIC_NUMBER;
        int rowMultiplayer = j / SHORT_MAGIC_NUMBER;

        for (int k = lineMultiplayer*SHORT_MAGIC_NUMBER; k < lineMultiplayer*SHORT_MAGIC_NUMBER + SHORT_MAGIC_NUMBER; k++) {
            for (int l = rowMultiplayer*SHORT_MAGIC_NUMBER; l < rowMultiplayer*SHORT_MAGIC_NUMBER + SHORT_MAGIC_NUMBER; l++) {
                if(i == k && j == l)
                    continue;
                fullSet.remove(elements[k][l].getValue());
            }
        }
    }

    private void excludeByLine(Set<Long> fullSet, Element[][] elements, int i, int j) {
        for (int k = 0; k < MAGIC_NUMBER; k++) {
            if (k == i)
                continue;
            fullSet.remove(elements[k][j].getValue());
        }
    }

    private void excludeByRow(Set<Long> fullSet, Element[][] elements, int i, int j) {
        for (int k = 0; k < MAGIC_NUMBER; k++) {
            if (k == j)
                continue;
            fullSet.remove(elements[i][k].getValue());
        }
    }

    private Set<Long> createFullSet() {
        return new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
    }

    void resolve(Element[][] field) {
        Element[][] localField = copyArray(field);
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                if (localField[i][j].getValue() == null) {
                    Iterator<Long> iterator = localField[i][j].getPossibleValues().iterator();
                    while(iterator.hasNext()) {
                        Long value = iterator.next();
                        if (isItPossible(copyArray(field), i, j, value)) {
                            localField[i][j].setValue(value);
                            initPossibleValues(localField);
                            resolve(localField);
                        }
                    }
                }
            }
        }
    }

    private boolean isItPossible(Element[][] elements, int i, int j, Long value) {
        elements[i][j].setValue(value);
        initPossibleValues(elements);
        return !isHasNullPossibleSets(elements);
    }

    private boolean isHasNullPossibleSets(Element[][] elements) {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                if (elements[i][j].getValue() == null && (elements[i][j].getPossibleValues() == null || elements[i][j].getPossibleValues().isEmpty()))
                    return true;
            }
        }
        return false;
    }

    private Element[][] copyArray(Element[][] field) {
        Element[][] copy = new Element[MAGIC_NUMBER][MAGIC_NUMBER];
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                copy[i][j] = field[i][j];
            }
        }
        return copy;
    }


    public static void main(String[] args) {
//        Set<Long> set =  new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
//        set.remove(null);
//        System.out.println();
//        new Sudoku(Test.superHard).showWithValues();
        new Sudoku(Test.superHard).show();
    }

}
