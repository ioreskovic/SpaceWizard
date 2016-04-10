package org.lopina.arrays;

public class SquareMatrix<T> extends Matrix<T> {
    public SquareMatrix(T[][] matrix) {
        super(matrix);
    }

    public SquareMatrix(int size) {
        super(size, size);
    }

    public void set(int widthPos, int heightPos, T element) {
        matrix[heightPos][widthPos] = element;
    }
}
