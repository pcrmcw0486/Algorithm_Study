package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q2887
 * @문제이름 :
 * @난이도 :
 * @date : 2022-02-28 오후 9:26
 * @문제이해 왕국은 N개의 행성으로 이루어진다.
 * A(x,y,z)와 B(a,b,c) 의 연결 최소 비용은 mi(|x-a| , |y-b| , |z-c|)
 * N-1개의 행성을 연결해보자.
 * @알고리즘 Minimum spannig tree
 * kruskal
 * @접근방법
 *  * N 100,000 >> NC2 = 100000*100001/2 = 50억
 *  모든 edge들을 보기에는 너무 많다.
 *  굳이 다 보아야할까?
 *  보아야할것만 생각해보자.
 *  각 행성마다 갈 수 있는 최소 간선만 고려해본다면, x로 갈 수 있는 최소, y,z 총 3가지의 행성이 '선택'되게 된다.
 *  행성이 다른 모든 행성들에 대해 다 고려해야하는가?
 *  우리가 spanning tree를 구성하면서 선택하는 간선들에 대해 생각해보자.
 *  kruskal알고리즘을 통해서 '그리디'하게 최소 거리의 간선을 택하게 된다. 굳이 멀리 있는 간선까지 포함하여 생각할 필요가 없다.
 *  즉, 총 3개의 그래프에서
 */
public class Q2887 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        Planet[] planets = new Planet[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(i,x, y, z);
        }
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.sort(planets, Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < N - 1; i++) {
            Planet a = planets[i], b = planets[i + 1];
            pq.add(new Edge(a.idx, b.idx, Math.abs(a.x-b.x)));
        }
        Arrays.sort(planets,Comparator.comparingInt(o->o.y));
        for (int i = 0; i < N - 1; i++) {
            Planet a = planets[i], b = planets[i + 1];
            pq.add(new Edge(a.idx, b.idx, Math.abs(a.y-b.y)));
        }
        Arrays.sort(planets,Comparator.comparingInt(o->o.z));
        for (int i = 0; i < N - 1; i++) {
            Planet a = planets[i], b = planets[i + 1];
            pq.add(new Edge(a.idx, b.idx, Math.abs(a.z-b.z)));
        }
        long ans =0;
        int cnt =0;
        while (!pq.isEmpty()) {
            if(cnt == N-1) break;
            Edge cur = pq.poll();
            if (union(cur.from, cur.to)) {
                cnt++;
             //   System.out.println(cur.dist);
                ans += cur.dist;
            }
        }
        System.out.println(ans);
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        long dist;

        public Edge(int from, int to, long dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist < 0 ? -1 : 1;
        }
    }

    static class Planet {
        int idx;
        int x, y, z;

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static long calcDist(Planet a, Planet b) {
        return Math.min(Math.abs(a.x - b.x),Math.min(Math.abs(a.y - b.y) , Math.abs(a.z - b.z)));
    }
    public static int find(int node){
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x==y) return false;
        parent[y] = x;
        return true;
    }
}
