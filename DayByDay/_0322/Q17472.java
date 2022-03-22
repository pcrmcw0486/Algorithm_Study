package DayByDay._0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q17472
 * @문제이름 : 다리 만들기 2
 * @난이도 : Godl I
 * @date : 2022-03-22 오후 7:27
 * @문제이해
 * @알고리즘 스패닝 트리, BFS 탐색,
 * @접근방법 1. 섬 grouping
 * 2. 각 섬마다 다리 모두 연결해보기 edge생성
 * 2.1 detail isVisited[] int 자기 번호 아니면 fail
 * 3. spanning tree만들기.
 */
public class Q17472 {
    static int groupSize = 1;
    static boolean[][] isVisited;
    static int[][] map;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); //0땅 -1 가능.
            }
        }

        //grouping
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !isVisited[i][j]) {
                    isVisited[i][j] = true;
                    grouping(i, j);
                    groupSize++;
                }
            }
        }
        //makeEdge Edge가 많아봐야
        parent = new int[groupSize];
        for (int i = 0; i < groupSize; i++) parent[i] = i;
        int[][] edges = new int[groupSize][groupSize];
        //가로줄
        for (int i = 0; i < N; i++) {
            int prevGroup = -1;
            int dist = 0;
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    if (prevGroup == -1)
                        prevGroup = map[i][j];
                    else if (map[i][j] != prevGroup) {
                        if (dist > 1) {
                            int curGroup = map[i][j];
                            if (edges[curGroup][prevGroup] == 0)
                                edges[curGroup][prevGroup] = dist;
                            else
                                edges[curGroup][prevGroup] = Math.min(edges[curGroup][prevGroup], dist);
                            edges[prevGroup][curGroup] = edges[curGroup][prevGroup];
                        }
                        prevGroup = map[i][j];
                    }
                    dist = 0;
                } else dist++;
            }
        }
        //세로줄 (굳이하면 한번에도 가능하긴 하겟네요.. 배열하나 더 써서)
        for (int i = 0; i < M; i++) {
            int prevGroup = -1;
            int dist = 0;
            for (int j = 0; j < N; j++) {
                if (map[j][i] != 0) {
                    if (prevGroup == -1)
                        prevGroup = map[j][i];
                    else if (map[j][i] != prevGroup) {
                        if (dist > 1) {
                            int curGroup = map[j][i];
                            if (edges[curGroup][prevGroup] == 0)
                                edges[curGroup][prevGroup] = dist;
                            else
                                edges[curGroup][prevGroup] = Math.min(edges[curGroup][prevGroup], dist);
                            edges[prevGroup][curGroup] = edges[curGroup][prevGroup];
                        }
                        prevGroup = map[j][i];
                    }
                    dist = 0;
                } else
                    dist++;
            }
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i < groupSize; i++) {
            for (int j = i + 1; j < groupSize; j++) {
                if(edges[i][j] != 0)
                    pq.add(new Edge(i, j, edges[i][j]));
            }
        }
        int cnt = 0;
        int dist = 0;
        while (!pq.isEmpty()) {
            if (cnt == groupSize - 2) break;
            Edge cur = pq.poll();
            if (union(cur.u, cur.v)) {
                cnt++;
                dist += cur.dist;
            }
        }
        if (cnt == groupSize - 2) System.out.println(dist);
        else System.out.println(-1);
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        parent[x] = y;
        return true;
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, dist;

        public Edge(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    public static void grouping(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
            if (map[nx][ny] == 0) continue;
            if (isVisited[nx][ny]) continue;
            isVisited[nx][ny] = true;
            grouping(nx, ny);
        }
        map[x][y] = groupSize;
    }
}
