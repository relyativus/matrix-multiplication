package com.relyativus.matrix;

import com.relyativus.matrix.operations.multiplication.def.DefaultMultiplicationStrategy;
import com.relyativus.matrix.operations.multiplication.recursive.ParallelRecursiveMultiplicationStrategy;

/**
 * @author anatolii vakaliuk
 */
public class Demo {

    public static void main(String[] args) {
        int matrixSize = 2049;
        Matrix a = new Matrix(matrixSize);
        Matrix b = new Matrix(matrixSize);
        MatrixHelper.prefilRandomValues(a, b);
        final int newSize = calculateNewSize(Math.max(a.size(), b.size()));
        a.extendTo(newSize);
        b.extendTo(newSize);

        Matrix result = new Matrix(newSize);

        final var recursiveMultiplicationTask = new ParallelRecursiveMultiplicationStrategy(
                new DefaultMultiplicationStrategy(), 64);
        final long start = System.currentTimeMillis();
        recursiveMultiplicationTask.multiply(a, b, result);
        final long diffInMillis = System.currentTimeMillis() - start;
        System.out.printf("Execution time=%f %n", diffInMillis / 1000.0);

//        System.out.println("Result matrix:");
//        MatrixHelper.printMatrix(result);
    }

    private static int calculateNewSize(final int oldSize) {
        return 1 << (int) Math.ceil(Math.log(oldSize) / Math.log(2));
    }

}
