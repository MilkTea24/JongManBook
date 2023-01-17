package algospot.tsp1;

import java.util.Scanner;

public class Main {
    public static int n;
    public static double[][] dist;
    public static boolean[] visited;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        n = scanner.nextInt();
        dist = new double[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = scanner.nextDouble();
            }
        }

        double minLength = Double.MAX_VALUE;
        for (int i = 0; i < n; i++){
            double TSPLength = TSP(i, 0.0);
            if (TSPLength < minLength) minLength = TSPLength;
        }

        System.out.printf("%.10f\n", minLength);
    }

    public static double TSP(int now, double pastlength) {
        boolean allTraveled = true;
        double minlength = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                allTraveled = false;
                visited[i] = true;
                double ilength = TSP(i, pastlength + (dist[now][i]));
                if (ilength < minlength) minlength = ilength;
                visited[i] = false;
            }
        }

        if (allTraveled) {
            return pastlength;
        }

        return minlength;
    }
}
