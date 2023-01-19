package example.ch07.sec02;

import java.util.Scanner;

public class PowMatrix {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        SquareMatrix A = new SquareMatrix(n);
        A.inputMatrix(scanner);

        SquareMatrix ret = SquareMatrix.powOfMatrix(A, m);
        ret.printAllMatrix();
    }
}
