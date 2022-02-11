/*
https://www.acmicpc.net/problem/17472
다리만들기 Gold II
*** 접근 방법 ***
prim을 쓰든, Kruskal을 쓰든 이 문제의 중요한 point는
edge를 어떻게 구할것인가 하는 것이다.
edge를 구하는게 point인 문제.
+ N,M은 최대 10까지 로 문제 조건이 작은 편에 속한다.
+ 길이가 1인 다리는 포함하지 않는다.

 > 구분을 일단 해줘야됨. dfs또는 bfs로
 > 
*** 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***

 */
package SolveByCategory.tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Edge3 implements Comparable<Edge3> {
    int start;
    int end;
    int dist;

    Edge3(int start, int end, int dist) {
        this.start = start;
        this.end = end;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge3 o) {
        return this.dist - o.dist;
    }
}

public class Q17472 {
    static int[][] map;
    static int[][] newMap;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int number = 1;
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // --- NUMBERING ---
        newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && newMap[i][j] == 0) {
                    dfs(i, j);
                    number++;
                }
            }
        }
        //////////////// --- FUCKING CALCUL EDGE --- ///////////////////
        ArrayList<Edge3> edgelist = new ArrayList<>();
        // 가로 방향
        for (int i = 0; i < N; i++) {
            int start = -1;
            int end = -1;
            int dist = 0;
            for (int j = 0; j < M; j++) {
                if (start == -1 && newMap[i][j] != 0)
                    start = newMap[i][j];
                else if (start != -1 && newMap[i][j] == 0)
                    dist++;
                else if (start != -1 && newMap[i][j] != 0 && start == newMap[i][j]) {
                    dist = 0;
                } else if (start != -1 && newMap[i][j] != 0 && start != newMap[i][j]) {
                    end = newMap[i][j];
                    // make Edge
                    if (dist > 1)
                        edgelist.add(new Edge3(start, end, dist));
                    start = end;
                    end = -1;
                    dist = 0;
                }
            }
        }
        // 세로방향
        for (int i = 0; i < M; i++) {
            int start = -1;
            int end = -1;
            int dist = 0;
            for (int j = 0; j < N; j++) {
                if (start == -1 && newMap[j][i] != 0)
                    start = newMap[j][i];
                else if (start != -1 && newMap[j][i] == 0)
                    dist++;
                else if (start != -1 && newMap[j][i] != 0 && start == newMap[j][i]) {
                    dist = 0;
                } else if (start != -1 && newMap[j][i] != 0 && newMap[j][i] != start) {
                    end = newMap[j][i];
                    if (start == end) {
                        dist = 0;
                        continue;
                    }
                    if (dist > 1)
                        edgelist.add(new Edge3(start, end, dist));
                    start = end;
                    end = -1;
                    dist = 0;
                }
            }
        }
        ////////////////////////////////////////////////////////////////////

        //////////////// --------- KRUSKAL ------- ////////////////
        Collections.sort(edgelist);
        parent = new int[number];
        for (int i = 1; i < parent.length; i++)
            parent[i] = i;
        int ans = 0;
        for (Edge3 edge : edgelist) {
            // System.out.println(edge.start + " " + edge.end + " " + edge.dist);
            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                ans += edge.dist;
            }
        }
        ///////// ---- CHECK MST -------- /////////////
        boolean check = true;
        int checknum = find(1);
        for (int i = 2; i < parent.length; i++) {
            if (checknum != find(i))
                check = false;
        }
        if (!check)
            System.out.println(-1);
        else
            System.out.println(ans);

    }

    public static void dfs(int x, int y) {
        newMap[x][y] = number;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1)
                continue;
            if (map[nx][ny] == 1 && newMap[nx][ny] == 0) {
                dfs(nx, ny);
            }
        }
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
