package com.relyativus.matrix;

import java.util.Arrays;

/**
 * @author anatolii vakaliuk
 */
public class Matrix {
    private double[][] content;
    private int rowOffset;
    private int columnOffset;
    private int size;

    public Matrix(double[][] content) {
        this.content = content;
        this.rowOffset = 0;
        this.columnOffset = 0;
        this.size = content.length;
    }

    public Matrix(int size) {
        this.content = new double[size][size];
        this.rowOffset = 0;
        this.columnOffset = 0;
        this.size = size;
    }

    private Matrix(double[][] content, int rowOffset, int columnOffset, int size) {
        this.content = content;
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
        this.size = size;
    }

    public void setCell(final int row, final int column, final double value) {
        this.content[row + rowOffset][column + columnOffset] = value;
    }

    public void sumCell(final int row, final int column, final double value) {
        setCell(row, column, getCell(row, column) + value);
    }

    public double getCell(final int row, final int column) {
        return this.content[row + rowOffset][column + columnOffset];
    }

    public Matrix getBlock(final int newSize, final int rowOffset, final int columnOffset) {
        return new Matrix(content, this.rowOffset + rowOffset, this.columnOffset + columnOffset, newSize);
    }

    public void extendTo(final int newSize) {
        if (newSize > content.length) {
            double[][] newContent = new double[newSize][newSize];
            for (int i = 0; i < content.length; i++) {
                System.arraycopy(content[i], 0, newContent[i], 0, content.length);
            }
            for (int i = content.length; i < newSize; i++) {
                Arrays.fill(newContent[i], 1);
            }
            this.content = newContent;
        }
    }

    public int size() {
        return size;
    }

}
