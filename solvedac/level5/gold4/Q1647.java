package solvedac.level5.gold4;
/*
2022.01.11
문제번호 : Q1647
이름 및 난이도 : 도시 분할 계획 Gold IV
문제이해 
-----------------
N개 집, M개 길. 길은 어느방향(양방향) 길 유지비용(가중치)

두개로 분리된 마을로 분할 계획.
분리된 마을 안에 서로 연결되도록 분할.(임의의 두 집 사이에 경로가 항상 존재)
마을에는 집이 하나 이상.

분리된 두 마을 사이에 있는 길들을 없앨 수 있음
분리된 마을 안에서도 임의의 두집 사이에 경로가 항상 존재하ㅔ하면서 길을 없앨 수 있다.
위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을
최소로 하고 싶다.

>> 각 마을을 모두 연결하는 어떠한 그래프 집합에서 어떻게 잘라도 연결이 된다.
 즉, 최소의 길의 합으로 모든 마을을 연결하는 그래프를 만든다.
  스패닝 트리
  문제 조건에 따라, 없애고 남은 길 유지비 합의 최소 값. 이므로
  스패닝 트리에서 가장 긴 길을 잘라내면 된다.

  >> 가장 긴 값은 어차피 마지막에 나오네 n-1번째가 가장 긴.
   kruskal 사용시! 어차피 나눠지는 구나.
   필요없는 연산이였네.
접근 방법 :

제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1647 {
    public static void main(String[] args) throws IOException {
        // prim();
        kruskal();
    }

    public static void prim() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Edge>[] graph;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Edge>();
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        int ans = 0;
        int max = Integer.MIN_VALUE; // 가장 긴 길.
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;
            visited[cur.to] = true;
            ans += cur.weight;
            max = Math.max(max, cur.weight);
            for (Edge nxt : graph[cur.to]) {
                if (!visited[nxt.to]) {
                    pq.add(nxt);
                }
            }
        }
        System.out.println(ans - max);
    }

    static int[] parent;

    public static void kruskal() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            parent[i] = i;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }

        int cnt = 0;
        int ans = 0;
        int max = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            if (cnt == N - 1)
                break;
            Edge cur = pq.poll();
            if (union(cur.from, cur.to)) {
                cnt++;
                ans += cur.weight;
                max = Math.max(max, cur.weight);
            }
        }
        System.out.println(ans - max);
    }

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return false;
        parent[x] = y;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        // for prim
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        // for kruskal
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
