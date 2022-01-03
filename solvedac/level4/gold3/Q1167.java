package solvedac.level4.gold3;
/*
2021.12.27
문제번호 : Q1167
이름 및 난이도 : 트리의 지름 Gold III
문제이해 
-----------------
트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다.
정점은 1부터 V까지 매겨져 있다. 
a b w1 c w2 의 거리.

접근 방법 :
    트리에서의 지름이란 펼쳐서 가장 먼 길이를 가짐.
    즉, 한 곳에서 가장 먼 곳으로 도착 시 그 곳이 지름임.
제한 조건 : 
 주어지는 거리는 모두 10_000이하의 자연수.
 V <= 100_000
*/

import java.io.*;
import java.util.*;

public class Q1167 {
    static int ans;
    static int onePoint;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // input & init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ans = 0;
        onePoint = -1;
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1)
                    break;
                int w = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, w));
            }
        }

        // find One Point
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);

        // find Dist through find another diameter node
        visited = new boolean[N + 1];
        visited[onePoint] = true;
        dfs(onePoint, 0);

        System.out.println(ans);
    }

    /*
     * @Param (int N, int dist) : selected node and dist
     * 
     * @description : dfs find most far node from start node
     */
    public static void dfs(int cur, int dist) {
        if (dist > ans) {
            ans = dist;
            onePoint = cur;
        }
        for (Edge nxt : graph[cur]) {
            if (!visited[nxt.idx]) {
                visited[nxt.idx] = true;
                dfs(nxt.idx, dist + nxt.weight);
            }
        }
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
