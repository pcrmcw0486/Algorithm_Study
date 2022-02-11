/*
https://www.acmicpc.net/problem/14002
 >> 연결 SolveByCategory.binarysearch/Q14003
*** 가장 긴 증가하는 부분 수열 4 Gold I ***
*** 접근 방법 ***
- LIS를 푸는 방법이 여럿 있지만, 여기서는 DP를 활용하여 풀어본다.
- 기존의 SolveByCategory.dp[i]의 경우 0~i의 가장 긴 수열의 길이를 저장하였다.
- 첫 번째 접근 방법)
   - memoization ( 각 수열이 증가할때, 증가한 index를 기록하는 
                   배열을 사용하여 거꾸로 따라가며 출력)
*** 사용 알고리즘 자룍조 스킬 ***
*** 문제 조건 ***
@param, input
    수열 A의 크기 N : 1<= N <= 1_000
    데이터 x  x : 1<= x <= 1000
@output
    - 가장 긴 증가하는 부분 수열의 길이 출력
    - 증가하는 부분 수열이 여러가지인 경우 아무거나 
     그 수열을 출력한다.
 */
package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q14002 {
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        data = new int[N];
        int[] prev_position = new int[N];
        int[] dp = new int[N];
        int maxLength = 1;
        int maxIndex = 0;
        Arrays.fill(dp, 1);
        Arrays.fill(prev_position, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j])
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prev_position[i] = j;
                    }
            }
            if (maxLength < dp[i]) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxLength).append("\n");
        printSeq(prev_position, maxIndex, sb);
        System.out.println(sb.toString());
    }

    public static void printSeq(int[] prev_position, int index, StringBuilder sb) {
        if (index == -1) {
            return;
        } else {
            printSeq(prev_position, prev_position[index], sb);
            sb.append(data[index]).append(" ");
        }
    }
}
