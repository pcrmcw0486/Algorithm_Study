package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int MAX = 100;
        int test_count = Integer.parseInt(br.readLine());
        for (int i = 0; i < test_count; i++) {
            // data input
            int col_num = Integer.parseInt(br.readLine());
            int[][] sticker_arr = new int[2][col_num];
            int[][] dp = new int[MAX][3];
            for (int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < col_num; k++) {
                    sticker_arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            // status : 0 왼쪽에 뗀거 하나도 없을 때
            // status : 1 왼쪽 중 위에꺼 제거
            // status : 2 왼쪽 중 아래꺼 제거
            // Bottom-up 방법으로 dp[c][status] c: c열까지 스티커 뗏을 때 c열의 상태 status 0열(X) 1열부터
            // 값들과 dp index 차이를 인지 및 문제 중요는 status를 알아내는 것.
            for (int j = 0; j < col_num; j++) {
                dp[j + 1][0] = Math.max(dp[j][0], Math.max(dp[j][1], dp[j][2]));
                dp[j + 1][1] = Math.max(dp[j][0], dp[j][2]) + sticker_arr[1][j];
                dp[j + 1][2] = Math.max(dp[j][0], dp[j][1]) + sticker_arr[0][j];
            }
            System.out.println(Math.max(dp[col_num][0], Math.max(dp[col_num][1], dp[col_num][2])));

        }

    }
}
