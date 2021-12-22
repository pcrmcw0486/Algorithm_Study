package solvedac.level4;
/*
2021.12.09
문제번호 : Q11725
이름 및 난이도 : 트리의 부모 찾기
문제이해 
-----------------
루트 없는 트리가 주어지고 루트가 1이라고 할 때 각 노드의 부모를 구하는 프로그램
작성.
접근 방법 :
트리 or 그래프 탐색
방문 여부를 체크하면서 부모를 기록하면됨.
노드의 수가 1부터 시작하므로 0으로 방문여부와 부모를 동시에 체크할 수 있을 것 같다.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N + 1];
        int[] parent = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        parent[1] = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nxt : tree[cur]) {
                if (parent[nxt] == 0) {
                    parent[nxt] = cur;
                    queue.add(nxt);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N + 1; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
