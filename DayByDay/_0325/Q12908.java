package DayByDay._0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q12908
 * @문제이름 : 텔레포트 3
 * @난이도 : Gold IV
 * @date : 2022-03-25 오후 3:45
 * @문제이해 크기가 무한대인 격자판  각점은 (x,y)
 * (xs,ys) -> (xe,ye)로 이동하려고 한다.
 * 이동 방법
 * 1. 점프 (x,y) -> (x+1,y),(x-1,y), (x,y+1), (x,y-1) 점프는 1초가 걸린다.
 * 2. 텔레포트를 사용 (텔레포트 방법은 총 세가지가 있으며 미리 정해져있다.)
 * (x1,y1) <-> <->(x2,y2) 텔레포트는 10초가 걸린다.
 * 빠른 시간에 도착해보자.
 * @알고리즘 그래프 탐색.
 * 텔레포트 위치에 도착한다면 탈 수 있다.
 * 텔레포트를 어떻게 표시하징?
 * 좌표가 3*2개 주어지죠. 각각의 좌표에 대해 기록해두어야죵. 텔레포트 기록이 point인 문제네용.
 * Naiive하게 풀어봅시다.
 * 그래봐야 edge 7+6+5+4+3+2+1 = 28 개밖에 안됨.(아 양방향이긴 한데)
 * 조금더 간결하게 풀 수 없나.
 * one source to one destination
 * 최단거리 문제 푸는 것과 같음
 * 최단거리 dijkstra, prim
 * dijkstra하려면 Node point와 edge들을 모두 연결해주어야한다.
 * @접근방법
 */
public class Q12908 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point[] pos = new Point[8];
        long[][] edges = new long[8][8]; //start =0 end = 7
        st = new StringTokenizer(br.readLine());
        pos[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        pos[7] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for (int i = 1; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i * 2 - 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            pos[i * 2] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            edges[i * 2 - 1][i * 2] = edges[i * 2][i * 2 - 1] = 10;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (edges[i][j] == 10) continue;
                long dist = calcTime(pos[i], pos[j]);
                edges[i][j] = dist;
                edges[j][i] = dist;
            }
        }
        long[] dist = new long[8];
        boolean[] isVisited = new boolean[8];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        isVisited[0] = true;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.dist > dist[cur.idx]) continue;
            if (cur.idx == 7) break;
            for (int i = 0; i < 8; i++) {
                if (dist[i] > dist[cur.idx] + edges[cur.idx][i]) {
                    dist[i] = dist[cur.idx] + edges[cur.idx][i];
                    pq.add(new Edge(i, dist[i]));
                }
            }
            isVisited[cur.idx] = true;
        }
        System.out.println(dist[7]);
    }

    public static long calcTime(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        long dist;

        public Edge(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return (this.dist - o.dist)<0?-1:1;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
