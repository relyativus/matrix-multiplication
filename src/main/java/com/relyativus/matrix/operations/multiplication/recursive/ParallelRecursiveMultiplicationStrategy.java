package com.relyativus.matrix.operations.multiplication.recursive;

import com.relyativus.matrix.Matrix;
import com.relyativus.matrix.operations.multiplication.MultiplicationStrategy;

import java.util.concurrent.ForkJoinPool;

/**
 * @author anatolii vakaliuk
 */
public class ParallelRecursiveMultiplicationStrategy implements MultiplicationStrategy {
    private static final ForkJoinPool EXECUTOR = ForkJoinPool.commonPool();

    private final MultiplicationStrategy delegate;
    private final int threshold;

    public ParallelRecursiveMultiplicationStrategy(MultiplicationStrategy delegate, int threshold) {
        this.delegate = delegate;
        this.threshold = threshold;
    }

    @Override
    public void multiply(Matrix a, Matrix b, Matrix c) {
        var task = new StrassenRecursiveMultiplicationTask(delegate, a, b, c, c.size(), threshold);
        EXECUTOR.invoke(task);
    }
}
