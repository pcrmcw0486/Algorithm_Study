package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Review_2887 {
    static class Edge implements Comparable<Edge>{
        int x,y, dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
    static class Point implements Comparable<Point>{
        int idx, value;

        public Point(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        public int compareTo(Point o) {
            return this.value - o.value;
        }
    }
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> listX = new ArrayList<>();
        ArrayList<Point> listY = new ArrayList<>();
        ArrayList<Point> listZ = new ArrayList<>();
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            listX.add(new Point(i, Integer.parseInt(st.nextToken())));
            listY.add(new Point(i, Integer.parseInt(st.nextToken())));
            listZ.add(new Point(i, Integer.parseInt(st.nextToken())));
            parent[i] = i;
        }
        Collections.sort(listX);
        Collections.sort(listY);
        Collections.sort(listZ);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N - 1; i++) {
            Point x,y;
            x = listX.get(i); y = listX.get(i + 1);
            pq.add(new Edge(x.idx, y.idx, Math.abs(x.value - y.value)));
            x = listY.get(i); y = listY.get(i + 1);
            pq.add(new Edge(x.idx, y.idx, Math.abs(x.value - y.value)));
            x = listZ.get(i); y = listZ.get(i + 1);
            pq.add(new Edge(x.idx, y.idx, Math.abs(x.value - y.value)));
        }

        int cnt= 0;
        long ans =0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (union(cur.x, cur.y)) {
                ans += cur.dist;
                cnt++;
            }
            if(cnt == N-1) break;
        }
        System.out.println(ans);
    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if( x==y) return false;
        parent[x] = y;
        return true;
    }
}
