package example.ch07.sec01;

import java.util.Scanner;

public class SquareMatrix {
    private int n;
    int getN() {
        return n;
    }
    private int[][] squareMatrix;
    SquareMatrix(int _n) {
        n = _n;
        squareMatrix = new int[n][n];
    }
    void inputMatrix(Scanner scanner) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                squareMatrix[i][j] = scanner.nextInt();
            }
        }
    }

    void printAllMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(squareMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static SquareMatrix multipleMatrix(SquareMatrix A, SquareMatrix B) {
        if (A.getN() != B.getN()) return null;

        int n = A.getN();
        SquareMatrix ret = new SquareMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = 0;
                for (int k = 0; k < n; k++) {
                    temp += A.getOneElement(i, k) * B.getOneElement(k, j);
                }
                ret.setOneElement(i, j, temp);
            }
        }

        return ret;
    }

    static private SquareMatrix identity(int n) {
        SquareMatrix ret = new SquareMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) ret.setOneElement(i, j, 1);
            }
        }
        return ret;
    }

    static SquareMatrix powOfMatrix(SquareMatrix A, int m) {
        if (m == 0) return SquareMatrix.identity(A.getN());
        if (m % 2 != 0) return multipleMatrix(powOfMatrix(A, m-1), A);
        SquareMatrix half = powOfMatrix(A, m/2);
        return multipleMatrix(half, half);
    }

    int  getOneElement(int i, int j) {
        return squareMatrix[i][j];
    }

    private void setOneElement(int i, int j, int ret) {
        squareMatrix[i][j] = ret;
    }



}
