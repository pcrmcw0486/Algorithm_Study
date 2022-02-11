package solvedac.level5.gold3;

/*
2022.01.20
문제번호 : Q7579
이름 및 난이도 : 앱 Gold III
문제이해 
-----------------
몇 개를 삭제해야한다. -> 앱의 비활성화 -> 그만큼 cost가 들어감.(비용 필요)
A 프로그램은 m 메모리 사용 비활성화시 드는 비용은 C
추가로 M바이트가 필요할 때 비활성화 비용 c를 최소화하여 M바이트를 확보하자.


제한 조건 : 
 1<= N <= 100
 1<= M <=10,000,000 
 
접근 방법 :
  가장 먼저 '백준 평범한 배낭'을 생각해보자. > 이떄는 '무게'를 맞추고 '가치'의 최대값을 찾아보았다.
  이 문제에서는 어떠한 '바이트'를 먼저 맞추어야하고 이후에 '가치'의 최소값을 찾아야한다.
  dp로 푼다면, 천만번 대해서 N번 돌아야하기 때문에 약 10억번을돌아야함.
  매우 많이 걸린다.

   무게의 합이 M을 초과하는 순간, 더 이상 보지 않아도 됩니다. ( 그 이후에 더해봐야 가치만 더 커짐)
  무게의 합이 어떠한 M이상인 프로그램들을 선택하는 경우의 수(상태) -> 가치의 최소인 경우
   => 조건이 있잖아.

   1. Naiive 완탐 + pruning(가지치기)  (weight > M ) return;
     >> 모든 경우의수를 다 보아야 하는가? >> 일단 이걸로 가지치기 해보겠습니다. >> 시초가 떠버렷다.
   2. binarySearch? X
   3. dp[k] k바이트를 확보할 수 있을 때 최소 가치. (무게가 근데 1억임) (앞에서 부터 채워나가야겟죠) 근데 합이라. 좀 이상함.어려움.
     >> 다시 생각해보자. 만약에 확보 10에 프로그램 10개 가치 1면
     >> 
    dp와 binary serach.
    dp[k] 에서의 최소라면  dp[k + memory[a]]  = dp[k] + cost[a] k -memory까지 더해서.
    기준이 한가지인 최적화 문제. (M 보다 큰 무게일 때 최소 값)

    배낭문제와 비교하여 생각해보자.
    (무게보다 낮게 가치는 높게)
    (메모리보다 크게 가치는 낮게)
     >> 역발상 이 시발거 직관에 의한 혼돈. 추상화하여 봐야함
     a 낮게 b높게
     c높게 d낮게면 같은 문제네 라고 생각을 하고 알아서 바꿔서 했겠지.
     a보다 낮게하면서 b를 높게하시오
     d보다 낮게하면서 c를 높게 하시오.
     >>> X' 가치보다 낮게 하면서 M메모리보다 크게 해보자.
     낮은 가치들 중 M보다 큰 memory를 찾자.
     즉, 비용에 대해 확보할수 있는 최대의 메모리. (최대)
     최소에서 막혔음 관점의 변경. 사실상 근데 같은 문제였다.

*/

import java.io.*;
import java.util.*;

public class Q7579 {
    static int N, M;
    static int[] cost;
    static int[] memory;
    static int minCost;

    public static void main(String[] args) throws IOException {
        input();
        // solution1();
        solution2();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        minCost = Integer.MAX_VALUE;
    }

    public static void solution2() {
        int[] dp = new int[10001]; // 최대 cost
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int k = dp.length - 1; k >= cost[i]; k--) {
                dp[k] = Math.max(dp[k], dp[k - cost[i]] + memory[i]);
            }
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= M) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    public static void solution1() {
        find(0, 0, 0);
        System.out.println(minCost);
    }

    public static void find(int idx, int sum, int weight) {
        if (weight >= M) {
            minCost = Math.min(minCost, sum);
            return;
        }
        if (idx == N) {
            return;
        }

        // idx번지를 넣을 때
        find(idx + 1, sum + cost[idx], weight + memory[idx]);
        // idx번지를 넣지 않을 때 그대로
        find(idx + 1, sum, weight);
    }
}
