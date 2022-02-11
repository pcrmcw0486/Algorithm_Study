/*
*** KCM Travel  Gold I ***
*** 접근 방법 ***
 > 최단 경로 문제. (feat. 조건)
 > single source -> single destination
 > 짧은 순서대로 탐색해 가면서 비용을 확인해야함.
  > 우선 순위가 (비용 > 시간) 즉, 비용안에서 가장 빠른 시간.
  >이지만, 시간 순으로 정하면서 처음 비용을 만족하는 루트가 선택.

*** 사용 알고리즘 및 자료구조 ***
 > 결국 1>N을 가야함.
 > dijkstra를 쓰되(즉 bfs로 탐색)
 > 꺼내서 먼저 탐색하는 순서를 시간 > 비용 순으로 하여
 > 비용이 만족하지 않으면 update를 하지 않음.

*** 문제 조건과 이해***
 - 문제 이해 
   > 인천 -> LA 가는데, 구글에서는 최대 M원의 비용만 부담해줌.
   > 인천 -> LA를 M원 이하로 사용하면서 도착할 수 있는 가장 빠른길 찾기
   > 각 공한들간 티켓가격과 이동시간이 주어짐.
 - 문제 조건
  > 테스트 케이스 T
 > 공항수(Vertex 2=< <= 100), 지원비용(0<= <=10000)
 > 티켓정보(Edge, 0<= <=10000)
  > u, v(u!=v), 비용(1<=c<=M) 소요시간 (1<=d<=1000)
  인천은 1, LA는 N
  LA 도착 못하면 Poor KCM


  다시보기
 */
package SolveByCategory.graph.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    public int end;
    public int time;
    public int cost;

    public Node(int end, int cost, int time) {
        this.end = end;
        this.cost = cost;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        if (this.time == o.time) {
            return cost - o.cost;
        }
        return time - o.time;
    }
}

public class Q10217 {
    public static final int INF = 1_000_0000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] graph = new ArrayList[V];
            int[][] dp = new int[V][M + 1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<Node>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph[u].add(new Node(v, cost, time));
            }

            // dijkstra
            for (int i = 1; i < V; i++) {
                Arrays.fill(dp[i], INF);
            }

            pq.offer(new Node(0, 0, 0));
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int now_node = cur.end, now_cost = cur.cost, now_time = cur.time;
                if (now_node == V - 1)
                    break;
                if (dp[now_node][now_cost] < now_time)
                    continue;
                for (Node next : graph[now_node]) {
                    int next_node = next.end, next_cost = now_cost + next.cost, next_time = now_time + next.time;
                    if (next_cost > M)
                        continue;
                    if (dp[next_node][next_cost] > next_time) {
                        for (int i = next_cost; i < M + 1; i++) {
                            if (dp[next_node][i] <= next_time)
                                break;
                            dp[next_node][i] = Math.min(next_time, dp[next_node][next_cost]);
                        }
                        pq.offer(new Node(next_node, next_cost, next_time));
                    }
                }
            }

            int result = dp[V - 1][M];
            sb.append(result != INF ? result : "Poor KCM").append("\n");
        }
        System.out.print(sb.toString());
    }
}
