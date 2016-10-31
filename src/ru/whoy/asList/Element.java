package ru.whoy.asList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by WHOY on 22.10.2016.
 */
public class Element {

    private Long value;
    private Set<Long> possibleValues;
    private boolean resolved;
    private int lineNumber;
    private int rowNumber;

    public Element(Long value) {
        this.value = value;
        possibleValues = new HashSet<>();
        resolved = false;
    }

    public Element(Long value, int lineNumber, int rowNumber) {
        this(value);
        this.rowNumber = rowNumber;
        this.lineNumber = lineNumber;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Set<Long> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<Long> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void addPossibleValue(Long value) {
        if (this.possibleValues == null) {
            possibleValues = new HashSet<>();
        }
        possibleValues.add(value);
    }

    public void removePossibleValue(Long value) {
        if (this.possibleValues == null) {
            possibleValues = new HashSet<>();
        }
        possibleValues.remove(value);
    }

    public void setResolved() {
        this.resolved = true;
    }

    public void clearResolved() {
        this.resolved = false;
    }

    public boolean isResolved() {
        return resolved;
    }

    public boolean checkIsResolved() {
        return possibleValues.size() == 1 && possibleValues.contains(value);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
}
