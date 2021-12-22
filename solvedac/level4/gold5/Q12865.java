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

떠오르는 접근 방법은 backtracking 또는 dp
dp[x] = x무게일 때 넣을 수 있는 가장 큰 가치.
(w,v)가 왓을 때
dp[x-w] + v 와 dp[x]를 비교한다. 그럼 중복체크는? 넣었는지 어케암.

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

    public static void main(String[] args) throws IOException {
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
