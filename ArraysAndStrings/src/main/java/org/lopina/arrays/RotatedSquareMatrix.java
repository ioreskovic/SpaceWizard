package org.lopina.arrays;

public class RotatedSquareMatrix<T> extends SquareMatrix<T> {
    public RotatedSquareMatrix(SquareMatrix<T> original, int rotateTimes90) {
        super(original.matrix);
        rotateTimes90 =  rotateTimes90 % 4;

        rotate(rotateTimes90);
    }

    private void rotate(int rotateTimes90) {
        for (int i = 0; i < rotateTimes90; i++) {
            rotate();
        }
    }

    private void rotate() {
        int size = matrix.length;

        int topLeft = 0;
        int bottomRight = size - 1;

        while (topLeft < bottomRight) {
            rotateDonut(topLeft++, bottomRight--);
        }
    }

    private void rotateDonut(int topLeft, int bottomRight) {
        int sideSize = bottomRight - topLeft + 1;
        int stripSize = sideSize - 1;

        for (int i = 0; i < stripSize; i++) {
            T temp = matrix[topLeft][topLeft + i];
            matrix[topLeft][topLeft + i] = matrix[topLeft + i][bottomRight];
            matrix[topLeft + i][bottomRight] = matrix[bottomRight][bottomRight - i];
            matrix[bottomRight][bottomRight - i] = matrix[bottomRight - i][topLeft];
            matrix[bottomRight - i][topLeft] = temp;
        }
    }

}
