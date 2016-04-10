package org.lopina.arrays;

public class Matrix<T> {
    protected final T[][] matrix;

    public Matrix(T[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int height, int width) {
        this.matrix = (T[][]) new Object[height][width];
    }

    public T[][] getMatrix() {
        return matrix;
    }

}
