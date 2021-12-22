package RHS_FC.class08_shortestPath;

/*
2021.10.20
문제번호 : Q1753
이름 및 난이도 : 최단경로 Gold V
문제이해 
-----------------
방향 그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는
프로그램
가장 대표적인 Dijkstra 기본 문제
접근 방법 : dijkstra
제한 조건 : 
 V 20_000 // E 300_000 
*/
import java.io.*;
import java.util.*;

public class Q1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int startPoint = Integer.parseInt(br.readLine());
        ArrayList<Bus>[] graph = new ArrayList[V + 1];
        int[] dist = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<Bus>();
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Bus(v, c));
        }

        PriorityQueue<Bus> pq = new PriorityQueue<Bus>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Bus(startPoint, 0));
        dist[startPoint] = 0;
        while (!pq.isEmpty()) {
            Bus cur = pq.poll();
            if (dist[cur.idx] != cur.cost)
                continue;
            for (Bus nxt : graph[cur.idx]) {
                if (dist[cur.idx] + nxt.cost >= dist[nxt.idx])
                    continue;
                dist[nxt.idx] = dist[cur.idx] + nxt.cost;
                pq.add(new Bus(nxt.idx, dist[nxt.idx]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V + 1; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n');
        }

        System.out.print(sb);

    }

    private static class Bus {
        int idx;
        int cost;

        Bus(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
