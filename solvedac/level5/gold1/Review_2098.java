package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.IntFunction;

/**
 * @문제번호 : Review_2098
 * @문제이름 : TSP
 * @난이도 : Gold I
 * @date : 2022-03-07 오후 1:50
 * @author : pcrmcw0486
 * @문제이해
 * 외판원 순회 알고리즘의 구현.
 * @알고리즘

 * @접근방법

*/
public class Review_2098 {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], INF);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //자신만 visit
        int ans = TSP(0, 1);
        System.out.println(ans);
    }
    public static int TSP(int num, int visited) {
        if (visited == ((1 << N) - 1)) {
            if(W[num][0] == 0) return INF;
            return W[num][0];
        }
        if (dp[num][visited] != INF) return dp[num][visited];
        for (int i = 1; i < N; i++){
            if ((visited & (1 << i)) == 0 && W[num][i] != 0) {
                dp[num][visited] = Math.min(dp[num][visited], TSP(i, visited | (1 << i)) + W[num][i]);
            }
        }
        return dp[num][visited];
    }
}
