package DayByDay._0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q11085
 * @문제이름 :군사 이동
 * @난이도 : Gold III
 * @date : 2022-03-24 오후 4:38
 * @문제이해 BW - CW  p개의 지점과 wㄱ의 길로 표현된다.
 * 모든 길은 양방향, 길마다 너비가 존재하여 비례하는 수의 군사가 지나갈 수 있다.
 * BW : 뭉치는 것이 유ㅜ리하다, 경로는 정해두고, 경로로만 모든 군사를 보냄.
 * BW : 경로 상 길 중 가장 너비가 좁은 길의 너비를 최대화 하는 경로.
 * c ,v 가 주어질 떄, c->v로 가는 경로 중 가장 좁은 너비가 가장 크도록 하는 경로.
 * V(1000) E(50000) edge가 5만개, V가 1000개.
 * @알고리즘
 * @접근방법
 */
public class Q11085 {
    //static ArrayList<Edge>[] graph;
    static ArrayList<Edge>[] graph;
    static int[] dp;
    static int P, W, C, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList[P];
        for (int i = 0; i < P; i++) graph[i] = new ArrayList<>();

        dp = new int[P];
        dp[C] = Integer.MAX_VALUE;
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, d));
            graph[v].add(new Edge(u, d));
        }
        int[] dp = new int[P];
        dp[C] = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(C);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int min;
            for (Edge e : graph[cur]) {
                min =e.dist;
                if(dp[cur] != 0) min = Math.min(dp[cur], min);
                if (dp[e.to] < min) {
                    dp[e.to] = min;
                    queue.add(e.to);
                }
            }
        }
        System.out.println(dp[V]);
    }


    private static class Edge {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
