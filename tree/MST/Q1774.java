/*
https://www.acmicpc.net/problem/1774
우주신과의 교감 Gold IV
*** 접근 방법 ***
MST 만들기 유형이다.
간선이 주어져 있지 않고 간선을 구하기 위한 정점의 좌표가 주어져있고
각 정점은 주어진 순서대로 번호가 매겨진다.
여러가지 방법이 있을 수 있을 것 같은데
Kruskal로 풀이시 
1. 모든 간선을 구하면서 주어진 각 번호의 간선의 weight를 가장 낮은 값으로 주어
  가장 먼저 선택되게 하는 방법.
2. union-find를 통해 연결되어 있는 정점들을 먼저 처리하는 방법.
Prim으로 풀이시
위와 같이 주어진 간 번호의 정점끼리의 간선의 weight를 가장 낮은 값으로 주는 방법
 (무조건 선택되도록.)

*** 알고리즘 자료구조 스킬 ***
union-find && kruskal
*** 문제 조건 ***

 */
package tree.MST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Point {
    int num;
    double x;
    double y;

    Point(int num, double x, double y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    double weight;

    Edge(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        // 만약 차이가 0.1 이나 -0.1 이면 0이 나오네.
        // return (int) (this.weight - o.weight);
        if (weight < o.weight)
            return -1;
        return 1;
    }
}

public class Q1774 {
    static int[] parent;
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        init(N);

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            points[i] = new Point(i, x, y);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            union(start, end);
        }

        edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double weight = distance(points[i], points[j]);
                edgeList.add(new Edge(points[i].num, points[j].num, weight));

            }
        }

        Collections.sort(edgeList);
        double ans = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);

            if (find(edge.start) != find(edge.end)) {
                ans += edge.weight;
                union(edge.start, edge.end);
            }
        }
        bw.write(String.format("%.2f", ans) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void init(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
    }

    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
        }
    }

    public static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
}
