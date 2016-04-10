package org.lopina.arrays;

public class SquareMatrix<T> {
    protected final T[][] matrix;

    protected SquareMatrix(T[][] matrix) {
        this.matrix = matrix;
    }

    public SquareMatrix(int size) {
        this.matrix = (T[][]) new Object[size][size];
    }

    public void set(int widthPos, int heightPos, T element) {
        matrix[heightPos][widthPos] = element;
    }

    public T[][] getMatrix() {
        return matrix;
    }
}
