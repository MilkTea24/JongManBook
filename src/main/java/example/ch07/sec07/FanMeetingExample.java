package example.ch07.sec07;

import java.util.LinkedList;
import java.util.Scanner;

public class FanMeetingExample {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        String members = scanner.nextLine();
        String fans = scanner.nextLine();
        System.out.println(hugs(members, fans));
    }
     static int hugs(String members, String fans) {
         int N = members.length(); int M = fans.length();
         LinkedList<Integer> A = new LinkedList<>();
         LinkedList<Integer> B = new LinkedList<>();

         for (int i = 0; i < N; i++) A.add(i,(members.charAt(i) == 'M') ? 1 : 0);
         for (int i = 0; i < M; i++) B.add(i, (fans.charAt(M-i-1) == 'M') ? 1 : 0);

         LinkedList<Integer> C = karatsuba(A, B);
         int allHugs = 0;
         for (int i = N-1; i < M; ++i) {
             if (C.get(i) == 0)
                    ++allHugs;
         }
         return allHugs;
     }

    static LinkedList<Integer> karatsuba(LinkedList<Integer> a, LinkedList<Integer> b) {
        int an = a.size(), bn = b.size();
        if (an < bn) return karatsuba(b, a);
        if (an == 0 || bn == 0) return new LinkedList<>();
        if (an <= 50) return multiply(a, b);
        int half = an / 2;

        int minValue;
        if (half > b.size()) minValue = b.size();
        else minValue = half;
        LinkedList<Integer> a0 = new LinkedList<>(a.subList(0, half));
        LinkedList<Integer> a1 = new LinkedList<>(a.subList(half, a.size()));
        LinkedList<Integer> b0 = new LinkedList<>(b.subList(0, minValue));
        LinkedList<Integer> b1 = new LinkedList<>(b.subList(minValue, b.size()));

        LinkedList<Integer> z2 = karatsuba(a1, b1);
        LinkedList<Integer> z0 = karatsuba(a0, b0);
        addTo(a0, a1, 0);   addTo(b0, b1, 0);
        LinkedList<Integer> z1 = karatsuba(a0, b0);
        subFrom(z1, z0);
        subFrom(z1, z2);
        LinkedList<Integer> ret = new LinkedList<>();
        addTo(ret, z0, 0);
        addTo(ret, z1, half);
        addTo(ret, z2, half + half);
        return ret;
    }

    static void addTo(LinkedList<Integer> a, LinkedList<Integer> b, int k) {
        for (int i = 0; i < k; i++) {
            b.addFirst(0);
        }

        int ind = 0;
        while (ind != a.size() && ind != b.size()) {
            a.set(ind, a.get(ind) + b.get(ind));
            ind++;
        }

        while (ind != b.size()) {
            a.addLast(b.get(ind));
            ind++;
        }
        normalize(a);
    }

    static void subFrom(LinkedList<Integer> a, LinkedList<Integer> b) {
        int ind = 0;
        while (ind != b.size()) {
            a.set(ind, a.get(ind) - b.get(ind));
            ind++;
        }
        normalize(a);
    }

    static void normalize(LinkedList<Integer> num) {
        for (int i = 0; i+1 < num.size(); i++) {
            if (num.get(i) < 0) { //자릿수가 음수인 경우 내림 처리
                int borrow = (-num.get(i) + 9) / 10;
                num.set(i+1, num.get(i+1) - borrow);
                num.set(i, num.get(i) + borrow * 10);
            }
            else { //자릿수가 양수인 경우 올림 처리
                num.set(i+1, num.get(i+1) + num.get(i) / 10); //초과된 숫자는 다음 자릿수로 넘김
                num.set(i, num.get(i) % 10);
            }
        }
        //while (num.size() > 1 && num.getLast() == 0) num.removeLast();
    }

    static LinkedList<Integer> multiply(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> c = new LinkedList<>();
        for (int i = 0; i< a.size() + b.size() + 1; i++) {
            c.add(0);
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                int result = c.get(i+j) + a.get(i) * b.get(j);
                c.set(i + j, result);
            }
        }
        normalize(c);
        return c;
    }
}
