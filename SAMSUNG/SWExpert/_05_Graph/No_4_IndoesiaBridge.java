package SAMSUNG.SWExpert._05_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
SWEA 하나로


* */
public class No_4_IndoesiaBridge {
    static Node[] island;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    static double E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            int N = Integer.parseInt(br.readLine());
            int[] xArr = new int[N];
            int[] yArr = new int[N];
            parent = new int[N];

            xArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            yArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
            E = Double.parseDouble(br.readLine());

            pq = new PriorityQueue<Edge>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    pq.add(new Edge(i,j,xArr[i],xArr[j],yArr[i],yArr[j]));
                }
            }
            int cnt = 0;
            double ans = 0;
            while (!pq.isEmpty()) {
                if (cnt == N - 1) break;
                Edge cur = pq.poll();
                if (union(cur.x, cur.y)) {
                    cnt++;
                    ans += cur.dist;
                }
            }
            sb.append(Math.round(ans)).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static int find(int n) {
        if (n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        parent[x] = y;
        return true;
    }

    public static class Node {
        int idx;
        long x;
        long y;

        public Node(int idx, long x, long y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int x;
        int y;
        double dist;

        Edge(int xIdx, int yIdx,int x1, int x2, int y1, int y2) {
            this.x = xIdx;
            this.y = yIdx;
            this.dist = (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) * E;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist < 0 ? -1 : 1;
        }
    }
}
