package solvedac.level3;
/*
2021.11.05
문제번호 : Q1463
이름 및 난이도 : 1로 만들기.
문제이해 
-----------------
3으로 나눠지면 3으로 나누기
2로 나눠지면 2로 나누기
아니면 1 빼기
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(N);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == 1)
                break;
            if (cur % 2 == 0 && dp[cur] + 1 < dp[cur / 2]) {
                dp[cur / 2] = dp[cur] + 1;
                queue.add(cur / 2);
            }
            if (cur % 3 == 0 && dp[cur] + 1 < dp[cur / 3]) {
                dp[cur / 3] = dp[cur] + 1;
                queue.add(cur / 3);
            }
            if (dp[cur - 1] > dp[cur] + 1) {
                dp[cur - 1] = dp[cur] + 1;
                queue.add(cur - 1);
            }
        }
        System.out.println(dp[1]);
    }
}
