package com.relyativus.matrix;

import java.util.Random;

/**
 * @author anatolii vakaliuk
 */
public class MatrixHelper {

    public static void printMatrix(Matrix result) {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                System.out.print(result.getCell(i, j));
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void prefilRandomValues(Matrix a, Matrix b) {
        final Random random = new Random();
        for (int i = 1; i <= a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                a.setCell(i - 1, j, random.nextDouble());
                b.setCell(i - 1, j, random.nextDouble());
            }
        }
    }

    public static boolean equals(final Matrix a, final Matrix b) {
        return equals(a, b, 0.0001);
    }

    public static boolean equals(final Matrix a, final Matrix b, double precession) {
        boolean result = true;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                final boolean cellEqual = Math.abs(a.getCell(i, j) - b.getCell(i, j)) <= precession;
                if (!cellEqual) {
                    System.out.printf("Cell value is different. A[%d][%d]=%f, B[%d][%d]=%f %n", i, j, a.getCell(i, j),
                            i, j, b.getCell(i, j));
                }
                result = result && cellEqual;
            }
        }
        return result;
    }
}
