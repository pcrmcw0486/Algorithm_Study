package solvedac.level5.gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Review_2342
 * @문제이름 : Dance Dance Revolution
 * @난이도 : Gold 3
 * @date : 2022-03-08 오전 11:14
 * @문제이해
 * @알고리즘
 * @접근방법
 */
public class Review_2342 {
    static int INF = 444444;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][][] dp = new int[data.length][5][5];
        int size = dp.length-1;
        for (int[][] d : dp)
            for (int[] p : d)
                Arrays.fill(p, INF);

        dp[0][0][0] = 0;
        int ans =INF ;
        int prevPos =0;
        for(int i =1; i<data.length;i++){
            int next = data[i-1];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    int v = dp[i-1][l][r];
                    if (l != next)
                        dp[i][l][next] = Math.min(dp[i][l][next], v + getMove(r, next));
                    if(r != next)
                        dp[i][next][r] = Math.min(dp[i][next][r], v + getMove(l, next));
                }
            }
//            //왼발 고정 오른발 움직이기
//            for (int l = 0; l < 5; l++) {
//                if (next != prevPos)
//                    dp[i][next][prevPos] = Math.min(dp[i][next][prevPos], dp[i - 1][l][prevPos] + getMove(l, next));//왼발 움직인다면
//                if (l != next)
//                    dp[i][l][next] = Math.min(dp[i][l][next], dp[i - 1][l][prevPos] + getMove(prevPos, next)); //오른발 움직인다면
//            }
//            //오른발 고정하고 왼발만 움직이기
//            for (int r = 0; r < 5; r++) {
//                if (next != r)
//                    dp[i][next][r] = Math.min(dp[i][next][r], dp[i - 1][prevPos][r] + getMove(prevPos, next));//왼발 움직이기
//                if (prevPos != next)
//                    dp[i][prevPos][next] = Math.min(dp[i][prevPos][next], dp[i - 1][prevPos][r] + getMove(r, next));
//            }
//            prevPos = next;
        }
//        for (int i = 0; i < 5; i++) {
//            ans = Math.min(ans, dp[size][prevPos][i]);
//            ans = Math.min(ans, dp[size][i][prevPos]);
//        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[dp.length-1][i][j]);
            }
        }
        System.out.println(ans);
    }

    private static int getMove(int a, int b) {
        if (a == 0 || b == 0) return 2;
        if (a == b) return 1;
        else if (((a+1) % 4) == b-1) return 4;
        return 3;
    }
}
