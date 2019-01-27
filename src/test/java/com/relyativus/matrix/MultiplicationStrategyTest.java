package com.relyativus.matrix;

import com.relyativus.matrix.operations.multiplication.MultiplicationStrategy;
import com.relyativus.matrix.operations.multiplication.def.DefaultMultiplicationStrategy;
import com.relyativus.matrix.operations.multiplication.recursive.ParallelRecursiveMultiplicationStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author anatolii vakaliuk
 */
@RunWith(Parameterized.class)
public class MultiplicationStrategyTest {

    @Parameter
    public MultiplicationStrategy multiplicationStrategy;

    @Parameters(name = "{0}")
    public static Set<MultiplicationStrategy> strategies() {
        Set<MultiplicationStrategy> strategies = new HashSet<>();
        strategies.add(new DefaultMultiplicationStrategy());
        strategies.add(new ParallelRecursiveMultiplicationStrategy(new DefaultMultiplicationStrategy(), 2));
        return strategies;
    }

    @Test
    public void multiplyProducesMatrixWithCorrectValues() {
        final Matrix a = new Matrix(new double[][]{{1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8}, {1, 2, 3, 4, 5, 6, 7, 8},
                {1, 2, 3, 4, 5, 6, 7, 8}});
        final Matrix b = new Matrix(new double[][]{{8, 7, 6, 5, 4, 3, 2, 1}, {8, 7, 6, 5, 4, 3, 2, 1}, {8, 7, 6, 5, 4, 3, 2, 1},
                {8, 7, 6, 5, 4, 3, 2, 1}, {8, 7, 6, 5, 4, 3, 2, 1}, {8, 7, 6, 5, 4, 3, 2, 1}, {8, 7, 6, 5, 4, 3, 2, 1},
                {8, 7, 6, 5, 4, 3, 2, 1}});
        final Matrix result = new Matrix(a.size());

        multiplicationStrategy.multiply(a, b, result);

        final Matrix expected = new Matrix(new double[][]{
                {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36}, {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36},
                {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36}, {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36},
                {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36}, {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36},
                {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36}, {288.0, 252.0, 216.0, 180.0, 144.0, 108.0, 72.0, 36}}
        );
        assertTrue(MatrixHelper.equals(expected, result));
    }
}