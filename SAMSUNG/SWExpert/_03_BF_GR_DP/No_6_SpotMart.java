package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.Arrays;
import java.util.Scanner;

/*
좌 > 우 N봉지 과자 나열,
N개의 봉지 i번째 봉지는 Ai개의 조각을 가진다.
추가적으로 M개의 봉지가 더 제공되며 i번째 봉지는 Bi개의 조각을 가진다.

좌에서 우로 나열되어 있는 N봉지 과자 사이에 M개의 봉지를 아무 곳에나 끼워 넣을 수 있다.
이렇게 되면 N+M개의 봉지가 나열된다. 초기의 N봉지는 상대적으로 순서를 유지하고 있다.

만들어 놓은 리스트에서 좌-> 우로 걸어가며 뽑아간다.
고를 수도 있고, 안 고를 수도 있는데, 규칙에 의하면 한봉지를 가져가면 그 다음 과자는 못 가져간다.(연속된 과자는 못 고른다)
가장 많은 과자 조각을 가져갈 수 있는 방법은? >> 최대 몇개를 가져가는가?

제한 조건이 N 3000    Ai 가 100_000  int형으로 가능하다.

문제 1. N봉지 과자 사이에 M개의 봉지를 아무 곳에나 끼워넣을 수 있다.
문제 2. 나열된 리스트에서 가져갈 수 있는 최대 개수는? 대략 1~2초.

해결 1.N봉지 사이에 M개의 봉지를 끼워넣는다. 흠. 이게 문제임.\
    끼워넣는다. 내 맘~대로.. 맘대로 끼워넣어야되요. 어떻게 끼워넣어야 잘 넣었다고 소문이 날까.
    아니면 처음 접근했던대로.
해결 2.
  2번 문제의 경우 DP를 이용하여
  dp[i-1][0]에 그 전 단계의 최댓값이 있음.
  dp[i][0] 안뽑 = Math.max(dp[i-1][0], dp[i-1][1])
  dp[i][1]  뽑 = dp[i-1][0] + cost[i]가 되겠지요.
  O(N+M)에 해결이 가능하다.


  https://blog.uniony.me/swea/8935/...
* */
public class No_6_SpotMart {
    static int[] itemA;
    static int[] itemB;
    static int N, M;
    static int dp[][][][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            itemA = new int[N];
            for (int i = 0; i < N; i++) itemA[i] = sc.nextInt();
            int M = sc.nextInt();
            int[] itemB = new int[M];
            for (int i = 0; i < M; i++) itemB[i] = sc.nextInt();
            Arrays.sort(itemB);

            //초기화화
            dp = new int[N + 1][M + 1][M + 1][2];
            for (int n = 0; n < N + 1; n++) {
                for (int m = 0; m < M + 1; m++) {
                    for (int m2 = 0; m2 < M + 1; m2++) {
                        dp[n][m][m2][0] = -1;
                        dp[n][m][m2][1] = -1;
                    }
                }
            }
            int max = 0;
            for (int l = 0; l <= M; l++) {
                int r = M - l;
                if (Find(N, l, r, 0) > max) max = dp[N][l][r][0];
                if (Find(N, l, r, 1) > max) max = dp[N][l][r][1];
            }
        }
    }

    public static int Find(int idx, int l, int r, int take) {
        if (dp[idx][l][r][take] != -1) return dp[idx][l][r][take];
        if (idx == 0 && l == 0) return dp[idx][l][r][take] = 0;
        if (l + r > M) return dp[idx][l][r][take] = 0;

        int val;
        if (take == 1) {
            //즉 현재상태 idx, l, r인 상태에서 take하려면 그전 값에서 0을 take하고
            // 지금 상태에서는 A를 고르든 B에서 l을 고르든 take한다.(B높은 값)
            int f1 = 0, f2 = 0;
            //idx-1을 take
            if (idx > 0) f1 = Find(idx - 1, l, r, 0) + itemA[idx - 1];
            //l을 take하겟다.
            if (l > 0) f2 = Find(idx, l - 1, r, 0) + itemB[M - l];
            val = Math.max(f1, f2);
        } else {
            //그게 아닌 경우라면
            // 그전에 idx를 골랐던 경우 안골랐던 경우
            // 그 전에 l을 골랐던 경우 안골랐던 경우
            int f1 = 0, f2 = 0, f3 = 0, f4 = 0;
            if (idx > 0) {
                f1 = Find(idx - 1, l, r, 1);
                f2 = Find(idx - 1, l, r, 0);
            }
            if (r > 0){
                f3 = Find(idx, l, r - 1, 0);
                f4 = Find(idx, l, r-1, 1);
            }
            val = Math.max(f1,f2);
            val = Math.max(val, Math.max(f3,f4));
        }
        return dp[idx][l][r][take] = val;
    }
}
