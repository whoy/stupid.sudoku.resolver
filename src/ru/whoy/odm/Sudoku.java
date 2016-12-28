package ru.whoy.odm;

import java.util.*;

/**
 * Created by WHOY on 22.10.2016.
 */
public class Sudoku {

    private final int MAGIC_NUMBER = 9;
    private static final int SHORT_MAGIC_NUMBER = 3;
    private Element[] startMassive = new Element[MAGIC_NUMBER*MAGIC_NUMBER];

    public Sudoku(Long[][] field) {
        // // TODO: 22.10.2016  сделать проверку введённого добра
        initializeLocalMassive(field);
        initPossibleValues(startMassive);
    }

    private void initializeLocalMassive(Long[][] field) {
        for (int i = 0; i < MAGIC_NUMBER; i++) {
            for (int j = 0; j < MAGIC_NUMBER; j++) {
                startMassive[i*MAGIC_NUMBER + j] = new Element(field[i][j], i, j);
            }
        }
    }

    public void show(Element[] elements) {
        int i = 0;
        for (Element element : elements) {
            System.out.print(String.format("%6d", element.getValue()));
            if (++i % MAGIC_NUMBER == 0)
                System.out.println();
        }
    }

    public void showWithValues(Element[] elements) {
        int i = 0;
        for (Element element : elements) {
            System.out.print(String.format("%6d (%d,%d) values",element.getValue(), element.getLineNumber(), element.getRowNumber()));
            for(Long value : element.getPossibleValues())
                System.out.print(String.format("%2d", value));
            System.out.println();
            if (++i % MAGIC_NUMBER == 0)
                System.out.println();
        }
    }


    public void showCurrentWithValues() {
        showWithValues(this.startMassive);
    }

    private void initPossibleValues(Element[] elements) {
        for(int i = 0; i < elements.length; i++) {
            if (elements[i].getValue() != null) {
                elements[i].addPossibleValue(elements[i].getValue());
                elements[i].setResolved();
            } else {
                // // TODO: 30.10.2016 вот тут
                Set<Long> fullSet = createFullSet();
                excludeByRow(fullSet, elements, elements[i]);
                excludeByLine(fullSet, elements, elements[i]);
                excludeBySquare(fullSet, elements, elements[i]);
                elements[i].setPossibleValues(fullSet);
            }
        }
    }

    private void excludeBySquare(Set<Long> fullSet, Element[] elements, Element element) {
        int lineMultiplayer = element.getLineNumber() / Sudoku.SHORT_MAGIC_NUMBER;
        int rowMultiplayer = element.getRowNumber() / Sudoku.SHORT_MAGIC_NUMBER;

        for (int k = lineMultiplayer*Sudoku.SHORT_MAGIC_NUMBER; k < lineMultiplayer*Sudoku.SHORT_MAGIC_NUMBER + Sudoku.SHORT_MAGIC_NUMBER; k++) {
            for (int l = rowMultiplayer*Sudoku.SHORT_MAGIC_NUMBER; l < rowMultiplayer*Sudoku.SHORT_MAGIC_NUMBER + Sudoku.SHORT_MAGIC_NUMBER; l++) {
                if(element.getLineNumber() == k && element.getRowNumber() == l)
                    continue;
                fullSet.remove(elements[k*MAGIC_NUMBER + l].getValue());
            }
        }
    }

    private void excludeByLine(Set<Long> fullSet, Element[] elements, Element element) {
        for (int k = 0; k < MAGIC_NUMBER; k++) {
            if (k == element.getRowNumber())
                continue;
            fullSet.remove(elements[element.getLineNumber() * MAGIC_NUMBER + k].getValue());
        }
    }

    private void excludeByRow(Set<Long> fullSet, Element[] elements, Element element) {
        for (int k = 0; k < MAGIC_NUMBER; k++) {
            if (k == element.getLineNumber())
                continue;
            fullSet.remove(elements[k * MAGIC_NUMBER + element.getRowNumber()].getValue());
        }
    }

    private Set<Long> createFullSet() {
        return new HashSet<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
    }


    public void tryResolve(Element[] elements) {
        Element[] answer = resolve(copyArray(elements));
        if (isNotEmpty(answer))
            show(answer);
    }

    private Element[] resolve(Element[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if(elements[i].getValue() == null) {
                for(Long value : elements[i].getPossibleValues()) {
                    Element[] localElements = createAndInitNew(copyArray(elements), elements[i], value, i);
                    if(isNotEmpty(localElements)){
                        Element[] win = resolve(localElements);
                        if(isWin(win)) {
                            return win;
                        }
                    }
                }
            }
        }
        return null;
    }

    private Element[] createAndInitNew(Element[] elements, Element element, Long value, int i) {
        elements[i] = new Element(element, value);
        initPossibleValues(elements);
        return elements;
    }

    private boolean isWin(Element[] win) {
        for (Element el : win) {
            if (el.getValue() == null && (el.getPossibleValues() == null || el.getPossibleValues().isEmpty())) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotEmpty(Element[] answer) {
        return answer != null && answer.length > 0;
    }

    private Element[] copyArray(Element[] elements) {
        return Arrays.copyOf(elements, elements.length);
    }


    public static void main(String[] args) {
        new Sudoku(Test.superHard).showCurrentWithValues();
    }

}
