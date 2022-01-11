package solvedac.level4.gold5;
/*
2022.01.04
문제번호 : Q2166
이름 및 난이도 : 다각형의 면적 Gold V
문제이해 
-----------------
2차원 평면상에 N개의 점으로 이루어진 다각형.
이 다각형의 면적을 구하시오. 

접근 방법 :
제한 조건 : 
N <= 10_000 다각형을 이루는 순서대로 N개의 점 x,y좌표가 주어짐.
*/

import java.io.*;
import java.util.*;

public class Q2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] x = new long[N + 1];
        long[] y = new long[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        x[N] = x[0];
        y[N] = y[0];
        // 신발끈 공식
        long sum_A = 0;
        long sum_B = 0;
        for (int i = 0; i < N; i++) {
            sum_A += x[i] * y[i + 1];
            sum_B += x[i + 1] * y[i];
        }

        System.out.println(String.format("%.1f", (Math.abs(sum_A - sum_B) / 2.0)));
    }
}
