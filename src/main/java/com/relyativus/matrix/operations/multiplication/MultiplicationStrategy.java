package com.relyativus.matrix.operations.multiplication;

import com.relyativus.matrix.Matrix;

/**
 * @author anatolii vakaliuk
 */
public interface MultiplicationStrategy {
    void multiply(final Matrix a, final Matrix b, final Matrix c);
}
