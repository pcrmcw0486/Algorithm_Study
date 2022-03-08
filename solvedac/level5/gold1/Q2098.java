package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2098
 * @문제이름 : 외판원 순회
 * @난이도 : Gold I
 * @date : 2022-02-28 오후 4:03
 * @author : pcrmcw0486
 * @문제이해
 * 기본 TSP 문제임.
 * 1번부터 N번가지 번호가 매겨져 있는 도시와 도시 들 사이에 길이 있다.
 * 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오려고한다.
 * 이 때 한번간 도시는 갈 수 없다.
 * 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
 * 비용은 대칭적이지 않다 갈수없는 경우는 0임.
 * N과 비용 행렬이 주어질 때 가장 적은 비용을 들이는 외판원 순회 여행 경로를 구하시오.
 * @알고리즘
 * TSP알고리즘 DP
 * 임의의 한 도시에서..
 * @접근방법
 * D[v1][V - {v1}] = min(2<=j<=n) W[1][j] + D[j][V-{v1,vj}
 *
*/
public class Q2098 {
    static int N;
    static int[][] W;
    static final int INF = 987654321;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][(1 << N) - 1];
        StringTokenizer st ;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        //1<<0
        int ans = TSP(0,1);
        for (int[] line : dp) {
            for (int x : line) {
                System.out.print(x==INF?"- ":x + " ");
            }
            System.out.println();
        }
        System.out.println(ans);
    }

    private static int TSP(int node, int visit) {
        if(visit == (1<<N)-1){
            if(W[node][0] ==0)
                return INF;
            return W[node][0];
        }
        if(dp[node][visit] != INF) return dp[node][visit];
        for (int i = 1; i < N; i++) {
            //현재 node에서 i번째 노드를 방문한적이 없고,(visit & (1<<i)) == 0
            //갈 수 있다면, W[node][i] != 0
            if ((visit & (1 << i)) == 0 && W[node][i] != 0) {
                dp[node][visit] = Math.min(dp[node][visit], TSP(i, visit | (1 << i)) + W[node][i]);
            }
        }
        return dp[node][visit];
    }
}
