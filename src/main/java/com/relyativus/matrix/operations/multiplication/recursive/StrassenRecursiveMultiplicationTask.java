package com.relyativus.matrix.operations.multiplication.recursive;

import com.relyativus.matrix.Matrix;
import com.relyativus.matrix.operations.multiplication.MultiplicationStrategy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static com.relyativus.matrix.operations.MatrixAddition.adder;
import static com.relyativus.matrix.operations.MatrixSubtraction.subtractor;

/**
 * @author anatolii vakaliuk
 */
public class StrassenRecursiveMultiplicationTask extends RecursiveAction {

    private MultiplicationStrategy multiplicationStrategy;
    private Matrix a;
    private Matrix b;
    private Matrix c;
    private int size;
    private int threshold;

    public StrassenRecursiveMultiplicationTask(MultiplicationStrategy multiplicationStrategy, Matrix a, Matrix b,
                                               Matrix c, int size, int threshold) {
        this.multiplicationStrategy = multiplicationStrategy;
        this.a = a;
        this.b = b;
        this.c = c;
        this.size = size;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (size <= threshold) {
            multiplicationStrategy.multiply(a, b, c);
            return;
        }
        final int halfSize = size / 2;
        final Matrix a11 = a.getBlock(halfSize, 0, 0);
        final Matrix a12 = a.getBlock(halfSize, 0, halfSize);
        final Matrix a21 = a.getBlock(halfSize, halfSize, 0);
        final Matrix a22 = a.getBlock(halfSize, halfSize, halfSize);

        final Matrix b11 = b.getBlock(halfSize, 0, 0);
        final Matrix b12 = b.getBlock(halfSize, 0, halfSize);
        final Matrix b21 = b.getBlock(halfSize, halfSize, 0);
        final Matrix b22 = b.getBlock(halfSize, halfSize, halfSize);

        final Matrix c11 = c.getBlock(halfSize, 0, 0);
        final Matrix c12 = c.getBlock(halfSize, 0, halfSize);
        final Matrix c21 = c.getBlock(halfSize, halfSize, 0);
        final Matrix c22 = c.getBlock(halfSize, halfSize, halfSize);

        final Matrix p1 = new Matrix(halfSize);
        final Matrix p2 = new Matrix(halfSize);
        final Matrix p3 = new Matrix(halfSize);
        final Matrix p4 = new Matrix(halfSize);
        final Matrix p5 = new Matrix(halfSize);
        final Matrix p6 = new Matrix(halfSize);
        final Matrix p7 = new Matrix(halfSize);

        Deque<RecursiveAction> actions = new LinkedList<>();
        actions.addLast(createSubTask(adder().sum(a11, a22), adder().sum(b11, b22), p1, halfSize));
        actions.addLast(createSubTask(adder().sum(a21, a22), b11, p2, halfSize));
        actions.addLast(createSubTask(a11, subtractor().sub(b12, b22), p3, halfSize));
        actions.addLast(createSubTask(a22, subtractor().sub(b21, b11), p4, halfSize));
        actions.addLast(createSubTask(adder().sum(a11, a12), b22, p5, halfSize));
        actions.addLast(createSubTask(subtractor().sub(a21, a11), adder().sum(b11, b12), p6, halfSize));
        actions.addLast(createSubTask(subtractor().sub(a12, a22), adder().sum(b21, b22), p7, halfSize));
        ForkJoinTask.invokeAll(actions);

        calculateC11(c11, p1, p4, p5, p7);
        adder().sum(p3, p5, c12);
        adder().sum(p2, p4, c21);
        calculateC22(c22, p1, p2, p3, p6);
    }

    private void calculateC11(Matrix c11, Matrix p1, Matrix p4, Matrix p5, Matrix p7) {
        adder().sum(p1, p4, c11);
        adder().sum(c11, p7, c11);
        subtractor().sub(c11, p5, c11);
    }

    private void calculateC22(Matrix c22, Matrix p1, Matrix p2, Matrix p3, Matrix p6) {
        subtractor().sub(p1, p2, c22);
        adder().sum(c22, p3, c22);
        adder().sum(c22, p6, c22);
    }

    private StrassenRecursiveMultiplicationTask createSubTask(final Matrix subMatrixA, final Matrix subMatrixB,
                                                              final Matrix subMatrixC, final int size) {
        return new StrassenRecursiveMultiplicationTask(this.multiplicationStrategy, subMatrixA, subMatrixB, subMatrixC,
                size, this.threshold);
    }
}
