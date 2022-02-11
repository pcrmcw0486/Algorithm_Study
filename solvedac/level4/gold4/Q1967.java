package solvedac.level4.gold4;
/*
2021.12.19
문제번호 : Q1967
이름 및 난이도 : 트리의 지름 Gold IV
문제이해 
-----------------
사이클이 없는 무방향 그래프
두 노드 사이의 경로가 가장 긴 것을 트리의 지름.
결국 지름이란 건 leaf노드와 leaf노드가 된다.
지름의 한 점이라면 어떠한 점에서 출발하여서 가장 먼 곳이라면 지름의 한 점이 될 수 있다.
그 먼점에서 가장 먼점을 찾는다.

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1967 {
    static ArrayList<Node>[] SolveByCategory.tree;
    static boolean[] visited;
    static int onePoint;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++)
            tree[i] = new ArrayList<Node>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[u].add(new Node(v, w));
            tree[v].add(new Node(u, w));
        }
        onePoint = -1;
        result = -1;
        // 지름의 한 점을 구한다.
        visited = new boolean[N + 1];
        dfs(1, 0);
        // 지름의 한 점에서 가장 먼 다른 점을 찾는다.
        visited = new boolean[N + 1];
        dfs(onePoint, 0);
        System.out.println(result);
    }

    public static void dfs(int idx, int dist) {
        if (dist > result) {
            result = dist;
            onePoint = idx;
        }
        visited[idx] = true;
        for (Node nxt : tree[idx]) {
            if (!visited[nxt.idx]) {
                dfs(nxt.idx, dist + nxt.weight);
            }
        }
    }

    static class Node {
        int idx;
        int weight;

        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
