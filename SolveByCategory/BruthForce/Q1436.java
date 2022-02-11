package SolveByCategory.BruthForce;

import java.util.Scanner;

public class Q1436 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int num = 665;
        int count = 0;
        while (count != N) {
            num++;
            if (isCount(num))
                count++;
        }
        System.out.println(num);
    }

    public static boolean isCount(int num) {
        int Sixcount = 0;
        while (num > 0) {
            if (num % 10 == 6)
                Sixcount++;
            else
                Sixcount = 0;
            if (Sixcount == 3)
                break;
            num /= 10;
        }
        return Sixcount == 3;
    }
}
