/*
https://www.acmicpc.net/problem/10942
팰린드롬? Gold III
*** 접근 방법 ***
팰린드롬이란? 회문을 의미함 abba 와 같이 앞에서 뒤에서 읽
었을 때 동일한 문장을 말함 여기선 숫자니까 12321
> 어떤 X가 팰린드롬일 때, X앞뒤로 동일한 숫자가 붙으면 팰린드롬이된다.
즉, SolveByCategory.dp[i,j]가 팰린드롬일때, SolveByCategory.dp[i+1][j-1]이 팰린드롬이고 SolveByCategory.dp[i]==SolveByCategory.dp[j]여야함.
bottom-up으로 진행시에는, 하나일때, 두개일때에 대해 미리 구하고
3개 이상인 경우에 대해 진행한다. 이렇게 하지 않으면 검사에 지장이 잇음
top-down일경우에는 내려가서 체크하고 올라오면 되기 때문에 ㄱㅊ.
*** 알고리즘 자료구조 스킬 ***
SolveByCategory.dp, 문자열
*** 문제 조건 ***
수열 크기 N 1 ~ 2000
수는 100_000 작거나 같은 자연수
질문 개수가 1~1_000_000
 */
package SolveByCategory.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q10942 {
    static boolean[][] dp;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        data = new int[N];
        dp = new boolean[N + 1][N + 1];
        data = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        another_solution(N);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // sb.append(find(start, end) ? 1 : 0).append("\n");
            sb.append(dp[start][end] ? 1 : 0).append("\n");

        }
        System.out.print(sb.toString());
    }

    public static boolean find(int start, int end) {
        if (!dp[start][end]) {
            if (data[start - 1] == data[end - 1]) {
                if (end - start < 2) {
                    return dp[start][end] = true;
                }
                return dp[start][end] = find(data[start + 1], data[end - 1]);
            } else
                return dp[start][end] = false;
        }
        return dp[start][end];
        // if (start >= end)
        // SolveByCategory.dp[start][end] = true;
        // if (SolveByCategory.dp[start][end])
        // return true;
        // if (data[start - 1] != data[end - 1])
        // return SolveByCategory.dp[start][end] = false;
        // return SolveByCategory.dp[start][end] = find(start + 1, end - 1);
    }

    // bottom-up
    public static void another_solution(int N) {
        // 하나일때,
        for (int i = 1; i < N + 1; i++)
            dp[i][i] = true;
        // 두개일때
        for (int i = 1; i < N + 1; i++) {
            if (data[i - 1] == data[i])
                dp[i][i + 1] = true;
        }
        // 나머지
        for (int i = 2; i < N; i++) {
            for (int j = 1; j < N - i + 1; j++) {
                if (data[j] == data[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }

    }
}
