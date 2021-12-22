package solvedac.level4.gold5;
/*
2021.12.09
문제번호 : Q1753
이름 및 난이도 : 최단경로 Gold V
문제이해 
-----------------
방향 그래프에서 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하시오.
모든 간선의 가중치는 10이하의 자연수. 존재하면

접근 방법 :
 1. one source to all destination (dijkstra or bellman ford)
 2. 중복되는 간선의 처리.
    - 데이터 공간에 문제가 있다면 map을 통해 해결 가능하다.
    - 데이터 처리 단에서 정리하지 않게 되면 로직을 돌리는데 몇가지 문제가 발생할 수 있다.
        - 같은 간선의 중복성 문제. (저장된 dist와의 처리)
        - 그렇다면 자신이 가지고 있는 dist를 같이 움직이며 처리해야함.
        - 해당 queue에서 꺼낸 노드가 모든 노드로 relaxation하고나서 visit처리를 해야함.
        - 결국 중복 간선을 처리하지 않는다면 모든 노드를 다 보아야하고, 
          이를 로직을 돌리면서 따로 처리를 해주어야함.
        - Map을 통해 해결 가능하나 일단 로직단에서 처리하는 것으로 작성.(1)
        -흠.....
제한 조건 : 
V <= 20000  E<=300,000 
1부터 V까지의 번호.
서로 다른 두 정점 사이에 여러개의 간선이 존재할 수 있음.
u v w 

Point는 결국 disjktra는 N번 돌면 끝이 나야한다는 거.
dist를 통해 관리할 수도 있고 visit배열을 따로 사용할 수도 있다.
*/

import java.io.*;
import java.util.*;

public class Q1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        // dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        // dist[S] = 0;
        visited[S] = true;
        pq.add(new Edge(S, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.idx] != cur.dist)
                continue;
            if (!visited[cur.idx])
                visited[cur.idx] = true;
            for (Edge nxt : graph[cur.idx]) {
                if (!visited[nxt.idx]) {
                    if (dist[nxt.idx] > cur.dist + nxt.dist) {
                        dist[nxt.idx] = cur.dist + nxt.dist;
                        pq.add(new Edge(nxt.idx, dist[nxt.idx]));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dist.length; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
        }
        System.out.print(sb.toString());

    }

    static class Edge implements Comparable<Edge> {
        int idx;
        int dist;

        Edge(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
