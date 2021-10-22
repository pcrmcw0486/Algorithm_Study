package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q1149
이름 및 난이도 : RGB 거리
문제이해 
-----------------
N개의 집이 있고 거리는 선분으로 나타낼 수 있음 1~N 집 순서
집은 빨강 초록 파랑 중 하나의 색깔 빨/초/파 칠하는 비용이 주어질 때
N번집의 색은 N-1 N+1 번의 색과 같지 않아야함.
접근 방법 :
N번째의 집에서 결정할 수 있는 상황 상태는 다음과 같음.
N번째 집을 빨강으로 칠한다/ 파랑으로 칠한다/ 초록으로 칠한다.
D[N][x] N번째 집을 x로 칠했을 때의 최소 비용.
D[N][0] += Math.min(D[N-1][1], D[N-1][2]) + 빨강 비용
D[N][1] += Math.min(D[N-1][0], D[N-1][2]) + 초록 비용
D[N][2] += Math.min(D[N-1][1], D[N-1][0]) + 파랑 비용
정답은 Math.min(D[n][x] 0<=x<4)
제한 조건 : 
N<=1000 
집칠하는 비용 1000
int형 가능.
*/

import java.io.*;
import java.util.*;

public class Q1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];
        int[][] colorCost = new int[N][3];

        // init array
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        colorCost[0][0] = r;
        colorCost[0][1] = g;
        colorCost[0][2] = b;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            colorCost[i][0] = r + Math.min(colorCost[i - 1][1], colorCost[i - 1][2]);
            colorCost[i][1] = g + Math.min(colorCost[i - 1][0], colorCost[i - 1][2]);
            colorCost[i][2] = b + Math.min(colorCost[i - 1][0], colorCost[i - 1][1]);
        }
        System.out.println(Math.min(colorCost[N - 1][0], Math.min(colorCost[N - 1][1], colorCost[N - 1][2])));
    }

    // solution ohHo... 저장공간 최소화
    /*
     * int N = Integer.parseInt(br.readLine()); int dp[][] = new int[2][3]; int prev
     * = 0; int next = 1; int temp = 1; for (int i = 0; i < N; i++) {
     * StringTokenizer st = new StringTokenizer(br.readLine()); int R =
     * Integer.parseInt(st.nextToken()); int G = Integer.parseInt(st.nextToken());
     * int B = Integer.parseInt(st.nextToken()); dp[next][0] = Math.min(dp[prev][1],
     * dp[prev][2]) + R; dp[next][1] = Math.min(dp[prev][0], dp[prev][2]) + G;
     * dp[next][2] = Math.min(dp[prev][0], dp[prev][1]) + B; temp = next; next =
     * prev; prev = temp; } System.out.println(Math.min(dp[prev][0],
     * Math.min(dp[prev][1], dp[prev][2])));
     */
    /*
     * 변수 6개로 퉁치기. int N = read(), R = read(), G = read(), B = read();
     * 
     * for (int i = 1; i < N; i++) {
     * 
     * int r = read() + (G < B ? G : B), g = read() + (B < R ? B : R), b = read() +
     * (R < G ? R : G);
     * 
     * R = r; G = g; B = b;
     * 
     * }
     * 
     * N = R < G ? R : G; N = N < B ? N : B;
     * 
     * System.out.print(N);
     */
}
