package solvedac.level4.gold3;
/*
2022.01.06
문제번호 : Q1238
이름 및 난이도 : 파티
문제이해 
-----------------
N개의 숫자로 구분된 각 마을에 한명의 학생.
N명의 학생 X번 마을에 모여서 파티.
M개의 단방향 도로(directed graph)  i번째 길은 i의 시간 소요

학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야함.
최단시간에 오고가고 싶음.

N명의 학생들 중 오고 가는데 가장 많은 시간이 걸리는 학생?

접근 방법 :
 1 . floyd warshall
    1~N의 학생들이 K 마을에 갔다가 다시 자신에게 돌아와야하는 모든 값
    X의 학생은 dist[x][k] + dist[k][x] 의 거리를 걷게 됨.
    이 때 dist[x][k] 는 최단거리여야함.
    all source to all destination ->> Floyd warshall
 2. dijkstra
 어쩐지 풀면서 너무 멍때리고 공식처럼 푸는 느낌이였는데
  x의 관점에서  x->a x->b의 가까운 거리를 한번 구하고 dijkstra
  a->x를 구해야하는데, 간선의 방향을 바꾸어 구하면된다.
   무슨말이냐하면, a->x의 거리를 뒤집으면 x->a의 거리
   map을 뒤집어서 관점을 변경하는 거임.
   a에서 x로 가야하는 간선들을 x가 와라 라고 생각하면
   x->a 거리나 a->x 거리나 같다는 뜻. 

제한 조건 : 
 A->B 도시 최대 개수 1개
 N 최댓값 1000
 각 간선 시간 최댓값 100
 총 시간 최댓값 100_000
*/

import java.io.*;
import java.util.*;

public class Q1238 {
    static int N, M, X;
    static int[][] graph;
    static ArrayList<Edge>[] city;
    static ArrayList<Edge>[] reverseCity;

    final static int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        // solveFirst();
        solveNew();
    }

    public static void solveNew() throws IOException {
        inputNew();
        solutionNew();
    }

    public static void inputNew() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        city = new ArrayList[N + 1];
        reverseCity = new ArrayList[N + 1];
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            city[i] = new ArrayList<Edge>();
            reverseCity[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            city[u].add(new Edge(v, t));
            reverseCity[v].add(new Edge(u, t));
        }
    }

    public static void solutionNew() {
        // dijkstra
        int[] cost = dijkstra(city);
        // dijkstra
        int[] reverseCost = dijkstra(reverseCity);

        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            ans = Math.max(ans, cost[i] + reverseCost[i]);
        }
        System.out.println(ans);
    }

    public static int[] dijkstra(ArrayList<Edge>[] map) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> (a.time - b.time));
        int[] dist = new int[N + 1];
        // boolean[] visited = new boolean[N+1];
        // visited[X] = true;
        Arrays.fill(dist, INF);
        dist[X] = 0;
        pq.add(new Edge(X, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.idx] < cur.time)
                continue;
            for (Edge nxt : map[cur.idx]) {
                if (dist[nxt.idx] > dist[cur.idx] + nxt.time) {
                    dist[nxt.idx] = dist[cur.idx] + nxt.time;
                    pq.add(new Edge(nxt.idx, dist[nxt.idx]));
                }
            }
        }

        return dist;
    }

    static class Edge {
        int idx;
        int time;

        Edge(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void solveFirst() throws IOException {
        inputFirst();
        solutionFirst();
    }

    public static void inputFirst() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[u][v] = t;
        }
    }

    public static void solutionFirst() {
        // floyd warshall
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        // solutino for problem
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            ans = Math.max(ans, graph[i][X] + graph[X][i]);
        }
        System.out.println(ans);
    }
}
