package algospot.clocksync;

import java.util.Scanner;

public class Main {
    static int[][] switches = {
            {0, 1, 2}, {3, 7, 9, 11}, {4, 10, 14, 15},
            {0, 4, 5, 6, 7}, {6, 7, 8, 10, 12}, {0, 2, 14, 15},
            {3, 14, 15}, {4, 5, 7, 14, 15}, {1, 2, 3, 4, 5}, {3, 4, 5, 9, 13}};
    static int[] pressTimes;
    static int[] clockTimes = new int[16];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        pressTimes = new int[10];
        int minResult;
        for (int i = 0; i < 16; i++) {
             clockTimes[i] = scanner.nextInt();
         }
         minResult = pressSwitches(0);
         if (minResult == Integer.MAX_VALUE) minResult = -1;
         System.out.println(minResult);
    }

    static int pressSwitches(int switchN) {
        int minResult = Integer.MAX_VALUE;
        boolean isAllNoon = true;
        for (int i = 0; i < 16; i++) {
            if (clockTimes[i] != 12) {
                isAllNoon = false;
                break;
            }
        }
        if (isAllNoon) {
            int ret = 0;
            for (int i = 0; i < 10; i++) {
                ret += pressTimes[i];
            }
            System.out.println();
            return ret;
        }

        if (switchN == 10) return minResult;

        int tempRet;
        int[] oneSwitch = switches[switchN];
        int[] temp = {1, 1, 0, 1, 0, 2, 3, 1, 0, 0};
        tempRet = pressSwitches(switchN + 1);
        if (tempRet != Integer.MAX_VALUE && minResult > tempRet) minResult = tempRet;
        for (int i = 1; i <= 3; i++) {
            ++pressTimes[switchN];
            for (int j = 0; j < oneSwitch.length; j++) {
                if (clockTimes[oneSwitch[j]] == 12) clockTimes[oneSwitch[j]] = 3;
                else clockTimes[oneSwitch[j]] += 3;
            }
            tempRet = pressSwitches(switchN + 1);
            if (tempRet != Integer.MAX_VALUE && minResult > tempRet) minResult = tempRet;
        }
        pressTimes[switchN] = 0;
        for (int j = 0; j < oneSwitch.length; j++) {
            if (clockTimes[oneSwitch[j]] == 12) clockTimes[oneSwitch[j]] = 3;
            else clockTimes[oneSwitch[j]] += 3;
        }

        return minResult;
    }
}

/*
        for (int i = 0; i < 10; i++) {
            if (pressTimes[i] < 3) {
                allPressedThirdTimes = false;
                ++pressTimes[i];
                int retNext = pressSwitches(i);
                if (retNext != -1 && minResult > retNext){
                    minResult = retNext;
                }
                --pressTimes[i];
            }
        }*/