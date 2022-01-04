package solvedac.level4.gold3;
/*
2021.12.27
문제번호 : Q1865
이름 및 난이도 : 웜홀 Gold III
문제이해 
-----------------
N개의 지점이 있고 M개의 도로와 W개의 웜홀이 있다.(도로는 방향 X, 웜홀은 방향 O)
웜홀 : 시작위치에서 도착위치로 가는 하나의 경로 (시간이 뒤로 가게 된다.)
한 지점에서 출발하여 시간 여행 시, 다시 출발한 위치로 올 때,
출발한 시간보다 시간이 되돌아가 있는 경우가 있는가?
 >> 음수 사이클 찾기 문제였네...그냥.

접근 방법 :
어떠한 웜홀 (b,a, w)가 있을 때
shortest path a->b 의 거리가 d라고 하면
 d + w < 0 이 되면 된다.
 shortest path를 적용하는데, 이 때 음수 간선이 존재하므로 이를 고려하여야 한다.
 bellman ford O(VE)
  - 음수 사이클이 존재하는가?
  - 사이클이 존재하지 않는다면 도착 최단 거리는?
제한 조건 : 
 지점 수 N <= 500
 도로의 개수 M <= 2500
 웜홀의 개수 W <= 200
 두 지점을 연결하는 도로는 한 개 이상이다.
 지점의 번호는 1부터 N까지.
*/

import java.io.*;
import java.util.*;

public class Q1865 {
    static int N;
    static int M;
    static int W;
    static ArrayList<Edge>[] graph;
    static int[] dist;
    static boolean[] visited;
    static boolean flag;
    static BufferedReader br;
    static final int INF = 25000000; // 최대거리

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            flag = bellmanFord(1);
            if (flag) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.print(sb.toString());
    }

    // @param (int N) one source
    // @description : one source to all destination
    public static boolean bellmanFord(int s) {
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        for (int step = 0; step < N; step++) {
            // all edge relaxation
            for (int from = 1; from < N + 1; from++) {
                for (Edge e : graph[from]) {
                    if (dist[e.idx] > dist[from] + e.weight) {
                        dist[e.idx] = dist[from] + e.weight;
                        if (step == N - 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, -w));
        }
        dist = new int[N + 1];
        visited = new boolean[N + 1];
    }

    static class Edge {
        int idx;
        int weight;

        Edge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
