package solvedac.level4.gold5;
/*
2021.12.16
문제번호 : Q12865
이름 및 난이도 : 평범한 배낭 Gold V
문제이해 
-----------------
배낭 가치있게 
N개의 물건 각물건은 무게 W와 가치 V를 가짐.
최대 K만큼 무게를 넣을 수 있음. 가치의 최댓값?

접근 방법 :
유명한 0/1 knapsack문제이긴함.
조건은 K만큼의 무게. 최대의 V값.
넣으면서 확인할 수 있는 것은 무게이다.

떠오르는 접근 방법은 SolveByCategory.backtracking 또는 SolveByCategory.dp
SolveByCategory.dp[x] = x무게일 때 넣을 수 있는 가장 큰 가치.
(w,v)가 왓을 때
SolveByCategory.dp[x-w] + v 와 SolveByCategory.dp[x]를 비교한다. 그럼 중복체크는? 넣었는지 어케암.

두가지를 모두 사용한다. 하나의 물건을 넣을 때 고려할 것.
 - 무게를 넘었는가?
 - 가치가 더 나가는가?

제한 조건 : 
 1<=N<=100  버틸수 있는 무게 (1<=K<=100_000)
 W(1<=W<=100_000) 가치 (0<=V<=1_000)
 배낭에 넣을 수 있는 물건의 가치합의 최댓값.

*/

import java.io.*;
import java.util.*;

public class Q12865 {
    static int N;
    static int K;
    static int[] value;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve() {
        // SolveByCategory.dp[i]는 무게가 K일 때 가질 수 있는 가장 큰 가치
        int[] dp = new int[K + 1];
        // 물건을 하나씩 먼저 봄으로써 중복으로 들어가는 걸 방지한다.

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= 0; j--) {
                if (j - weight[i] >= 0 && dp[j] < dp[j - weight[i]] + value[i]) {
                    dp[j] = dp[j - weight[i]] + value[i];
                }
            }
        }

        System.out.println(dp[K]);

    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N];
        value = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void solution_prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] products = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            products[i][0] = w;
            products[i][1] = v;
        }
        int[] dp = new int[K + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = K; j - products[i][0] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - products[i][0]] + products[i][1]);
            }
        }
        System.out.println(dp[K]);
    }

}
