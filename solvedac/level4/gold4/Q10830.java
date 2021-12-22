package solvedac.level4.gold4;
/*
2021.12.21
문제번호 : Q10830
이름 및 난이도 : 행렬 제곱 Gold IV
문제이해 
-----------------
크기가 N*N인 행렬 A가 주어질 때 A의 B제곱을 구하는 프로그램을 작성.
각 원소는 1000으로 나눈 나머지를 출력한다.
2<=N<=5 1<=B<= 100000000000

접근 방법 :
분할정복 a^(B) = a^(2/B) * a^(2/B) if B%2 == 0 
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q10830 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }
        StringBuilder sb = new StringBuilder();
        int[][] ans = solve(A, B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    public static int[][] solve(int[][] M, long pow) {
        if (pow == 1) {
            return M;
        }
        int[][] ret = new int[N][N];
        int[][] res = solve(M, pow / 2);
        ret = matrix(res, res);
        if (pow % 2 == 1) {
            ret = matrix(M, ret);
        }
        return ret;
    }

    public static int[][] matrix(int[][] A, int[][] B) {
        int[][] ret = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    ret[i][j] += (A[i][k] * B[k][j]) % 1000;
                }
                ret[i][j] = ret[i][j] % 1000;
            }
        }
        return ret;
    }
}
