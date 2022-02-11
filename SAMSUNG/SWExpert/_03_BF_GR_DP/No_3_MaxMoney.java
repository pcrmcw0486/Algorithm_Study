package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
주어진 숫자판 들 중 두개를 선택하여 자리를 교환.
횟수 만큼 반드시 교환해야한다.
최대 자릿수는 6자리이며, 최대 교환 횟수는 10번이다.

주어진 숫자로 만들 수 있는 경우의수는 720가지. (나올 수 있는 경우의 수)(중복X 가정)
1가지의 경우의 수에서 1번 교환할때 나올 수 있는 가지수 15 가지.
항상 최선을 선택하는 것이 옳음.
총 가지수 10800 가지. 각각의 경우의 수에서 교환의 가지수를 만들어냈을 때 나올 수 있는 가지수.(물론 720가지 상태에서 돌아다님)

앞자리가 제일 클 수록 결국 커진다. (그리디)
하지만 횟수를 반드시 채워야한다.
19981 (가장 뒤에 있는 가장 높은 숫자를 택한다) 없다면 넘어간다?
99991 이고 횟수가 1이라면?


완탐 절대 아님.
https://jaeyoon8783.tistory.com/75
* */
public class No_3_MaxMoney {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            char[] input = sc.next().toCharArray();
            int depth = sc.nextInt();

        }
    }
}
