package example.ch07.sec03;

import java.util.Scanner;

public class QuadtreeExample {
    static int ind = 0;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        String s = scanner.nextLine();
        ind = 0;
        String result = reverse(s);
        System.out.println(result);
    }

    static String reverse(String s) {
        char head = s.charAt(ind); //Java는 Call by Value로 이루어지므로 ind의 static 선언 필요
        ++ind;
        if (head == 'b' || head == 'w') {
            return "" + head;
        }
        String upperLeft = reverse(s);
        String upperRight = reverse(s);
        String lowerLeft = reverse(s);
        String lowerRight = reverse(s);
        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }
}
