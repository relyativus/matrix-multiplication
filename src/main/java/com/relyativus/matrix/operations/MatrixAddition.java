package com.relyativus.matrix.operations;

import com.relyativus.matrix.Matrix;

/**
 * @author anatolii vakaliuk
 */
public class MatrixAddition {
    private static final MatrixAddition INSTANCE = new MatrixAddition();

    public static MatrixAddition adder() {
        return INSTANCE;
    }

    public Matrix sum(Matrix a, Matrix b) {
        Matrix matrix = new Matrix(a.size());
        sum(a, b, matrix);
        return matrix;
    }

    public void sum(Matrix a, Matrix b, Matrix c) {
        final int size = a.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                c.setCell(i, j, a.getCell(i, j) + b.getCell(i, j));
            }
        }
    }
}
