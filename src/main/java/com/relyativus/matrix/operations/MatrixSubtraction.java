package com.relyativus.matrix.operations;

import com.relyativus.matrix.Matrix;

/**
 * @author anatolii vakaliuk
 */
public class MatrixSubtraction {
    private static final MatrixSubtraction INSTANCE = new MatrixSubtraction();

    public static MatrixSubtraction subtractor() {
        return INSTANCE;
    }

    public Matrix sub(final Matrix a, final Matrix b) {
        final Matrix matrix = new Matrix(a.size());
        sub(a, b, matrix);
        return matrix;
    }

    public void sub(final Matrix a, Matrix b, Matrix c) {
        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < c.size(); j++) {
                c.setCell(i, j, a.getCell(i, j) - b.getCell(i, j));
            }
        }
    }
}
