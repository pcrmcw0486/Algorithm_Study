package solvedac.level4.gold4;
/*
2021.12.19
문제번호 : Q1504
이름 및 난이도 : 특정한 최단 경로 Gold IV
문제이해 
-----------------
방향성 없는 그래프 
1번 정점에서 N번 정점으로의 이동.
두가지 조건 (임의로 주어진 두 정점은 반드시 통과해야함)
중복 가능하다. 1번 정점에서 N번 정점으로 이동할 때 주어진 두 정점을 반드시
거치면서 최단 경로로 이동하는 프로그램.

접근 방법 :
undirected graph임. a->b나 b->a나 같다는 뜻. a->b == b->a == a<->b
1 <-> (a,b) <-> N    (1-a + b-N) 과 (1-b a-N) 중 작은 것을 골라야함.
one source to all destination ( 사실 목적지에 도착시 멈추면됨)
dijkstra(1) 한번
dijkstra(a) 한번 (a->b b->a) 같으니까 공통 부분
dijkstra(N) 한번 (a->N == N->a && b->N == N->b)
총 dijkstra를 3번 돌리면된다.
제한 조건 : 
정점개수 N이 800 간선의 가중치가 1000이라 최대 길어봐야
800000 int형으로 가능하다.

** 그러한 경로가 없을 때는 -1을 출력한다.
*/

import java.io.*;
import java.util.*;

public class Q1504 {
    static ArrayList<Edge>[] graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // graph input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
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
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // dijkstra int[] 배열 반환
        // dijkstra 1
        int[] startDist = dijkstra(1);
        // dijkstra a (공통 부분) (a는 항상 작은 것으로 설정한다)
        int[] middleDist = dijkstra(a);
        ;
        // dijkstra N
        int[] endDist = dijkstra(N);

        // find Min
        // (a<->b) + Math.min(( 1->a b->N) , 1->b + a->N)
        // dist = middleDist[b] + Math.min(startDist[a]+endDist[b],
        // startDist[b]+endDist[a])
        int ans = -1;
        if (middleDist[b] == Integer.MAX_VALUE || (startDist[a] == Integer.MAX_VALUE && endDist[b] == Integer.MAX_VALUE)
                || (startDist[b] == Integer.MAX_VALUE && endDist[a] == Integer.MAX_VALUE))
            ans = -1;
        else
            ans = middleDist[b] + Math.min(startDist[a] + endDist[b], startDist[b] + endDist[a]);
        System.out.println(ans);

    }

    public static int[] dijkstra(int x) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(x, 0));
        dist[x] = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.idx] < cur.dist)
                continue;
            for (Edge nxt : graph[cur.idx]) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.dist) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.dist;
                    pq.add(new Edge(nxt.idx, dist[nxt.idx]));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        int dist;

        Edge(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return this.dist - o.dist;
        };
    }
}
