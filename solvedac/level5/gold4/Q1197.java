package solvedac.level5.gold4;
/*
2022.01.11
문제번호 : Q1197
이름 및 난이도 : 최소 스패닝 트리 Gold IV
문제이해 
-----------------
그래프가 주어졌을 때 최소 스패닝 트리를 구하는 프로그램을 작성하라.
최소 스패닝트리란, 모든 정점들을 연결하는 부분 그래프 중 가중치 합이 최소인 트리.

접근 방법 :
  최소 스패닝 트리 알고리즘
  1. Kruskal  & union-find ( 그리디)
    - Edge 들을 정렬한다.
    - edge relaxation을 한다.(짧은 거리 택)
    - union-find를 이용한 Cycle check를 하면서 cycle이 있다면 넣지 않는다.
    - 반복한다.
  2. Prim
   - 임의의 정점을 선택하여 T에 포함시킨다.
   - T에 있는 노드와 T에 없는 노드 사이 간선 중 가중치가 최소인 간선을 찾는다.
   - 찾은 간선이 연결하는 두 노드 중 T에 없는 노드를 T에 포함시킨다.
제한 조건 : 
  - 간선은 음수일 수도 있다.
  - 양방향 간선임.
*/

import java.io.*;
import java.util.*;

public class Q1197 {
    static int N;

    public static void main(String[] args) throws IOException {
        // prim();
        kruskal();
    }

    static ArrayList<Edge> edgeList;
    static int[] parent;

    public static void kruskal() throws IOException {
        kruskalInput();

        // sort asc
        Collections.sort(edgeList);

        // Spanning tree 간선 N-1개
        int count = 0;
        int ans = 0;
        for (Edge e : edgeList) {
            if (count == N - 1)
                break;
            if (union(e.from, e.to)) {
                count++;
                ans += e.weight;
            }
        }
        System.out.println(ans);
    }

    public static void kruskalInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<Edge>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(u, v, w));
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            parent[i] = i;

    }

    public static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        // same set >> cycle possible
        if (x == y)
            return false;

        parent[x] = y;
        return true;
    }

    static ArrayList<Edge>[] graph;

    public static void prim() throws IOException {
        primInput();
        int ans = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        boolean[] visited = new boolean[N + 1];
        // int[] dist = new int[N + 1];
        // Arrays.fill(dist, 100001);
        pq.add(new Edge(1, 0));
        // dist[1] = 0;
        // visited[1] = true;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;
            visited[cur.to] = true;
            ans += cur.weight;
            for (Edge nxt : graph[cur.to]) {
                if (!visited[nxt.to]) {
                    pq.add(nxt);
                }
            }
            if (++cnt == N)
                break;
        }
        // for (int i = 1; i < N + 1; i++) {
        // ans += dist[i];
        // }
        System.out.println(ans);
    }

    public static void primInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // init graph
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();

        // data input
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
