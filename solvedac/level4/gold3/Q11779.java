package solvedac.level4.gold3;
/*
2022.01.03
문제번호 : Q11779
이름 및 난이도 : 최소비용 구하기 2 Gold III
문제이해 
-----------------
A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용 최소화.
 > shortest Path
 최소비용과 경로를 출력.
 항상 시작점에서 도착점으로의 경로는 존재한다.
접근 방법 :
 shortest Path + one source to one(or all) destination
  >> dijkstra or Bellman ford
   >> positive weight => dijkstra
 + print Path ( path 배열 필요)
제한 조건 : 
 n<= 1000
 m<= 100_000
 0<=w <= 100_000
 최대 거리
  100_000_000 (int형 가능)
*/

import java.io.*;
import java.util.*;

public class Q11779 {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int startCity, targetCity;
    static final int INF = 200_000_000;
    static StringBuilder sb;
    static Stack<Integer> pathStack;
    static int[] dist;
    static int[] pathParent;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void input() throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }
        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        targetCity = Integer.parseInt(st.nextToken());
    }

    public static void solve() {
        // data init
        pathParent = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[startCity] = 0;
        pathParent[startCity] = -1;
        // dijkstra from start
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(startCity, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.weight > dist[cur.idx])
                continue;
            if (cur.idx == targetCity)
                break;
            for (Edge nxt : graph[cur.idx]) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.weight) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.weight;
                    pathParent[nxt.idx] = cur.idx;
                    pq.add(new Edge(nxt.idx, dist[nxt.idx]));
                }
            }
        }
        pathStack = new Stack<Integer>();
        findPath(targetCity);
        sb.append(dist[targetCity]).append('\n');
        sb.append(pathStack.size()).append('\n');
        while (!pathStack.isEmpty()) {
            sb.append(pathStack.pop()).append(" ");
        }
        sb.append('\n');
        System.out.print(sb.toString());

    }

    public static void findPath(int idx) {
        if (idx == -1) {
            return;
        }
        pathStack.push(idx);
        findPath(pathParent[idx]);
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
