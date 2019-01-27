package com.relyativus.matrix.operations.multiplication.def;

import com.relyativus.matrix.Matrix;
import com.relyativus.matrix.operations.multiplication.MultiplicationStrategy;

/**
 * @author anatolii vakaliuk
 */
public class DefaultMultiplicationStrategy implements MultiplicationStrategy {

    @Override
    public void multiply(Matrix a, Matrix b, Matrix result) {
        final int size = a.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    final double product = a.getCell(i, k) * b.getCell(k, j);
                    result.sumCell(i, j, product);
                }
            }
        }
    }

}
