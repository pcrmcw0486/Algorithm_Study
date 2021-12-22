package RHS_FC.class08_shortestPath;

/*
2021.10.20
문제번호 : Q1916
이름 및 난이도 : 최소 비용 구하기 Gold V
문제이해 
-----------------
N개의 도시, M개의 버스
A도시 to B도시 비용 최소화.
접근 방법 : one to All shortestPath 
제한 조건 : 
*/
import java.io.*;
import java.util.*;

public class Q1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Bus>[] routes = new ArrayList[N + 1];
        int[] dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            routes[i] = new ArrayList<Bus>(0);
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            routes[u].add(new Bus(v, c));
        }
        st = new StringTokenizer(br.readLine());
        int startStation = Integer.parseInt(st.nextToken());
        int endStation = Integer.parseInt(st.nextToken());

        PriorityQueue<Bus> queue = new PriorityQueue<>((o1, o2)->o1.cost-o2.cost);
        dist[startStation] = 0;
        queue.add(new Bus(startStation, 0));
        while (!queue.isEmpty()) {
            Bus cur = queue.poll();
            if(dist[cur.to] != cur.cost) continue;
            for (Bus nxt : routes[cur.to]) {
                if (dist[cur.to] + nxt.cost < dist[nxt.to]) {
                    dist[nxt.to] = dist[cur.to] + nxt.cost;
                    queue.add(new Bus(nxt.to, dist[nxt.to]));
                }
            }
        }
        System.out.println(dist[endStation]);
    }

    private static class Bus {
        int to;
        int cost;

        Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
