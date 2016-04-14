package org.lopina.arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(BlockJUnit4ClassRunner.class)
public class RotateSquareMatrixTest {

    @Test
    public void matrixShouldBeRotated90DegreesPositively() throws Exception {
        Integer[][] matrix = new Integer[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = i * 5 + j + 1;
            }
        }

        printMatrix(matrix);

        SquareMatrix<Integer> original = new SquareMatrix<Integer>(matrix);
        RotatedSquareMatrix<Integer> rotatedSquareMatrix = new RotatedSquareMatrix<>(original, 1);

        printMatrix(rotatedSquareMatrix.getMatrix());
    }

    @Test
    public void rotatingMatrix4TimesShouldResultInSameMatrix() throws Exception {
        Integer[][] matrix = new Integer[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = i * 4 + j + 1;
            }
        }

        printMatrix(matrix);

        SquareMatrix<Integer> original = new SquareMatrix<Integer>(matrix);
        RotatedSquareMatrix<Integer> rotated90 = new RotatedSquareMatrix<>(original, 1);
        printMatrix(rotated90.getMatrix());

        RotatedSquareMatrix<Integer> rotated180 = new RotatedSquareMatrix<>(rotated90, 1);
        printMatrix(rotated180.getMatrix());

        RotatedSquareMatrix<Integer> rotated270 = new RotatedSquareMatrix<>(rotated180, 1);
        printMatrix(rotated270.getMatrix());

        RotatedSquareMatrix<Integer> rotated360 = new RotatedSquareMatrix<>(rotated270, 1);
        printMatrix(rotated360.getMatrix());
    }


    private void printMatrix(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }
}
