/*
*** 특정한 최단 경로 Gold IV ***
*** 접근 방법 ***
- 최단 경로 문제
- 1 ->a a -> b  b -> N 의 최단 경로 
   VS  1->b b->a a->N의  최단경로 중 가장 짧은 것이 된다.
- Dijkstra 알고리즘이 인접리스트 활용시 O(N log N)이므로 3번 돌려서 dist를 구한 뒤
 단순 계산을 한다.
*** 활용 알고리즘 및 활용 방법 ***
-  dijkstra?
- graph는 인접리스트로 구현
- dist는 총 3*V 배열
- dijkstra는 함수로 만들어서 배열 반환 시킨다.
*** 문제의 조건 ***
- 방향성이 없는 그래프
- 1번 -> N번 정점으로 최단 거리 이동
- 한번 이동했던 정점과 한번 이동했던 간선 다시 이용 가능.(but 최단 경로)
- 주어진 두 정점을 반드시 거치면서 최단경로로 이동.
- 1 > ( a > b >) > N
*/
package graph.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1504 {
    static int V;
    static int E;
    static ArrayList<Edge>[] graph;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // data input
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // 그래프 생성
        graph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, weight));
            graph[v].add(new Edge(u, weight));
        }
        int[][] dist = new int[3][];

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()) - 1;
        int b = Integer.parseInt(st.nextToken()) - 1;
        dist[0] = dijkstra(graph, 0);
        dist[1] = dijkstra(graph, a);
        dist[2] = dijkstra(graph, b);

        int result = INF;
        // 마지막 경로 구하기
        if (dist[0][a] != INF && dist[1][b] != INF && dist[2][V - 1] != INF)
            result = Math.min(result, dist[0][a] + dist[1][b] + dist[2][V - 1]);
        if (dist[0][b] != INF && dist[2][a] != INF && dist[1][V - 1] != INF)
            result = Math.min(result, dist[0][b] + dist[2][a] + dist[1][V - 1]);
        System.out.println(result == INF ? "-1" : result);

    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        boolean[] check = new boolean[V];
        Arrays.fill(dist, INF);

        pq.add(new Edge(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Edge nowNode = pq.poll();
            int now_vertex = nowNode.v;
            int length = nowNode.weight;
            if (!check[now_vertex]) {
                check[now_vertex] = true;
                for (Edge next : graph[now_vertex]) {
                    if (length + next.weight < dist[next.v]) {
                        dist[next.v] = length + next.weight;
                        pq.offer(new Edge(next.v, dist[next.v]));
                    }
                }
            }
        }
        return dist;
    }

}

class Edge implements Comparable<Edge> {
    public int weight;
    public int v;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
      
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}
