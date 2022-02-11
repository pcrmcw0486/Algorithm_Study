
/*
https://www.acmicpc.net/problem/2887
행성 터널 Gold I
*** 접근 방법 ***
기본 MST 문제임.

*** 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***
정점 A(x,y, z)와 정점 B(a,b,c)에 대해서
weight는 min( |x-a|, |y-b|, |z-c|)임.
 */
package SolveByCategory.tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Point2 {
    int num;
    int x;
    int y;
    int z;

    Point2(int num, int x, int y, int z) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x : " + x + " y : " + y + "z : " + z;
    }
}

class Edge2 implements Comparable<Edge2> {
    int start;
    int end;
    int weight;

    Edge2(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge2 o) {
        return this.weight - o.weight;
    }

}

public class Q2887 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point2[] points = new Point2[N];
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
        ArrayList<Edge2> edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            points[i] = new Point2(i, x, y, z);
        }

        //////////////////////////////////////////
        Arrays.sort(points, new Comparator<Point2>() {
            @Override
            public int compare(Point2 o1, Point2 o2) {
                return o1.x - o2.x;
            }
        });
        for (int i = 0; i < points.length - 1; i++) {
            int distance = Math.abs(points[i].x - points[i + 1].x);
            edgeList.add(new Edge2(points[i].num, points[i + 1].num, distance));
        }
        ///////////////////////////////////////
        Arrays.sort(points, new Comparator<Point2>() {
            @Override
            public int compare(Point2 o1, Point2 o2) {
                return o1.y - o2.y;
            }
        });
        for (int i = 0; i < points.length - 1; i++) {
            int distance = Math.abs(points[i].y - points[i + 1].y);
            edgeList.add(new Edge2(points[i].num, points[i + 1].num, distance));
        }
        ////////////////////////////////////////
        Arrays.sort(points, new Comparator<Point2>() {
            @Override
            public int compare(Point2 o1, Point2 o2) {
                return o1.z - o2.z;
            }
        });
        for (int i = 0; i < points.length - 1; i++) {
            int distance = Math.abs(points[i].z - points[i + 1].z);
            edgeList.add(new Edge2(points[i].num, points[i + 1].num, distance));
        }

        //////////////////////////////////////////

        Collections.sort(edgeList);
        int ans = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge2 edge = edgeList.get(i);
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                ans += edge.weight;
            }
        }
        System.out.println(ans);
    }

    public static int distance(Point2 p1, Point2 p2) {
        return Math.min(Math.abs(p1.x - p2.x), Math.min(Math.abs(p1.y - p2.y), Math.abs(p1.z - p2.z)));
    }

    public static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
        }
    }

    public static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

}
