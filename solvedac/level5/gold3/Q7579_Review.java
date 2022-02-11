package solvedac.level5.gold3;
/*
2022.01.22
문제번호 : Q7579_Review
이름 및 난이도 : 앱 Gold III
문제이해 
-----------------
각 프로그램 A는 m바이트 메모리를 사용하고, 비활성화시 c 코스트가 든다.
M바이트 이상 확보하는 최소 c코스트 인 방법을 찾으시오.
M의 크기가 너무 크다. 그리고 최소 코스트인데 누적으로 찾을 수가 있나?
아하. 그럼 x코스트일 때 확보할 수 있는 최대 바이트의 크기를 찾는다면?
그럼 만족하는 바이트를 찾으면 첫 만남이 최소 코스트네..

내가 알고 있는건.. 무게 M일때 최대 가치였는데..흠..

제한 조건 : 
접근 방법 :
*/

import java.io.*;
import java.util.*;

public class Q7579_Review {
    static int N, M;
    static int[] cost;
    static int[] memory;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    // 10000000
    public static void solution() {
        int[] dp = new int[10001];
        // x코스트 이상이면 M바이트를 채울 수 있지.
        //
        for (int i = 0; i < N; i++) {
            for (int j = dp.length - 1; j >= cost[i]; j--) {
                dp[j] += Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= M) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}
