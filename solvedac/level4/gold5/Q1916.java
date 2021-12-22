package solvedac.level4.gold5;
/*
2021.12.10
문제번호 : Q1916
이름 및 난이도 : 최소 비용 구하기 Gold V
문제이해 
-----------------
N개의 도시 , 한 도시에서 다른 도시 도착 M 버스.
A번째 도시에서 B번째 도시 까지 드는 비용을 최소화한다.

접근 방법 :
 one source to one desitination
제한 조건 : 
 N 1000
 M 100000
 버스 비용은 0보다 크거나 같고 100_000보다 작은 정수.
 버스 형태로 1~1000 (10^3) 모든 비용 10^5 해도 10^9 안쪽 int형 가능.
*/

import java.io.*;
import java.util.*;

public class Q1916 {
    public static void main(String[] args) throws IOException {
        // data Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // Dijkstra algorithm
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        dist[A] = 0;
        pq.add(new Edge(A, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.idx] != cur.weight)
                continue;
            if (cur.idx == B)
                break; // one destination
            for (Edge nxt : graph[cur.idx]) {
                if (dist[nxt.idx] > nxt.weight + cur.weight) {
                    dist[nxt.idx] = nxt.weight + cur.weight;
                    pq.add(new Edge(nxt.idx, dist[nxt.idx]));
                }
            }
        }
        System.out.println(dist[B]);
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        int weight;

        Edge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
