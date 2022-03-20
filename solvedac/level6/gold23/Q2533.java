package solvedac.level6.gold23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q2533
 * @문제이름 : 사회망 서비스
 * @난이도 : Gold III
 * @date : 2022-03-09 오전 11:00
 * @문제이해 '두 정점으로 이어진 노드'를 친구라고 정의한다.
 * 어떤 아이디어를 퍼뜨리고자 할 때 가능한 최소한의 수로 어리어답터를 확보하여
 * 모든 사람이 받아들이게 해야한다.
 * 그래프의 경우 매우 어렵다는 것이 알려져있기 때문에 트리인 경우에 대해서 풀어보자.
 * @알고리즘 트리 dp
 * @접근방법 '최소한의 얼리어답터'라면 내가 얼리어답터가 아니면 주변의 누군가는 얼리어답터여야한다.
 * 한 노드의 상태는 두가지이다. 얼리어답터 이거나 아니거나
 * 아니라면 주변의 누군가는 얼리어답터여야 한다.
 * 따라서 dp[cur][true] = 연결된 노드들에 대하여 Math.min(dp[next][true],dp[next][false])가 된다.
 * 이 때 dp[현재노드][상태] = 나를 포함한 하위 subtree의 얼리어답터 최소 수
 * 로 정의하고 풀어보자.
 * 문제를 잘못읽어서 죽어ㅓ렸다..
 * 얼리어답터가 아닌 사람은 자신의 모든 친구들이 얼리어답터일때만 받아들인다.
 * 즉, 자신이 얼리어답터가 아니라면 자신의 부모나 자식이 모두 얼리어답터여야 한다.
 * 자신이 얼리어답터라면 자식은 일수도 아닐 수도 있다.
 * 한쪽기준으ㄹ만 본다.
 */
public class Q2533 {
    static ArrayList<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        solve(1, -1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void solve(int cur, int parent) {
        visited[cur] = true;
        dp[cur][1] =1;
        for (int nxt : graph[cur]) {
            if (visited[nxt]) continue;
            solve(nxt, cur);
            dp[cur][0] += dp[nxt][1];
            dp[cur][1] += Math.min(dp[nxt][1], dp[nxt][0]);
        }
    }
}
