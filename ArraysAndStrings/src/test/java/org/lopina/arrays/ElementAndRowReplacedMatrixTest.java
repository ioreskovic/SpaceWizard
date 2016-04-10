package org.lopina.arrays;

import org.junit.Test;

import java.util.Arrays;

public class ElementAndRowReplacedMatrixTest {

    @Test
    public void matrixNotContainingReplaceesShouldRemainUnchanged() throws Exception {
        Integer[][] matrix = new Integer[][] {
                {1, 2, 3, 4, 5},
                {6, 6, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };

        Matrix<Integer> original = new Matrix<Integer>(matrix);
        printMatrix(original.getMatrix());

        ElementAndRowReplacedMatrix<Integer> reduced = new ElementAndRowReplacedMatrix<>(original.getMatrix(), 0, 20);
        printMatrix(reduced.getMatrix());
    }

    @Test
    public void matrixContainingReplaceesOnEdgesShouldReplaceOuterRing() throws Exception {
        Integer[][] matrix = new Integer[][] {
                {1, 2, 3, 4, 1},
                {6, 6, 8, 9, 10},
                {1, 12, 13, 14, 1}
        };

        Matrix<Integer> original = new Matrix<Integer>(matrix);
        printMatrix(original.getMatrix());

        ElementAndRowReplacedMatrix<Integer> reduced = new ElementAndRowReplacedMatrix<>(original.getMatrix(), 1, 0);
        printMatrix(reduced.getMatrix());
    }

    @Test
    public void matrixContainingReplaceesInTheMiddleShouldReplaceMiddleCross() throws Exception {
        Integer[][] matrix = new Integer[][] {
                {1, 2, 3, 4, 1},
                {6, 6, 0, 9, 10},
                {1, 12, 13, 14, 1}
        };

        Matrix<Integer> original = new Matrix<Integer>(matrix);
        printMatrix(original.getMatrix());

        ElementAndRowReplacedMatrix<Integer> reduced = new ElementAndRowReplacedMatrix<>(original.getMatrix(), 0, 1);
        printMatrix(reduced.getMatrix());
    }

    @Test
    public void matrixContainingReplaceesBeingReplacedByThemselvesShouldNotBeRecursive() throws Exception {
        Integer[][] matrix = new Integer[][] {
                {0, 2, 3, 4, 0},
                {2, 2, 2, 2, 2},
                {6, 6, 1, 9, 10},
                {7, 7, 7, 7, 7},
                {0, 12, 13, 14, 0}
        };

        Matrix<Integer> original = new Matrix<Integer>(matrix);
        printMatrix(original.getMatrix());

        ElementAndRowReplacedMatrix<Integer> reduced = new ElementAndRowReplacedMatrix<>(original.getMatrix(), 1, 1);
        printMatrix(reduced.getMatrix());
    }

    private void printMatrix(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }
}
