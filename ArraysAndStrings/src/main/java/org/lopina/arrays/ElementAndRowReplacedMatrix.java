package org.lopina.arrays;

import java.util.HashSet;
import java.util.Set;

public class ElementAndRowReplacedMatrix<T> extends Matrix<T> {
    public ElementAndRowReplacedMatrix(T[][] matrix, T replacee, T replacement) {
        super(matrix);
        replaceRowsAndColumns(replacee, replacement);
    }

    private void replaceRowsAndColumns(T replacee, T replacement) {
        Set<Integer> markedRows = new HashSet<>();
        Set<Integer> markedColumns = new HashSet<>();

        search(markedRows, markedColumns, replacee);
        destroy(markedRows, markedColumns, replacement);
    }

    private void search(Set<Integer> markedRows, Set<Integer> markedColumns, T replacee) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (replacee.equals(matrix[i][j])) {
                    markedRows.add(i);
                    markedColumns.add(j);
                }
            }
        }
    }

    private void destroy(Set<Integer> markedRows, Set<Integer> markedColumns, T replacement) {
        int height = matrix.length;
        int width = matrix[0].length;

        for (Integer row : markedRows) {
            for (int j = 0; j < width; j++) {
                matrix[row][j] = replacement;
            }
        }

        for (Integer column : markedColumns) {
            for (int i = 0; i < height; i++) {
                matrix[i][column] = replacement;
            }
        }
    }
}
