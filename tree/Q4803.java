/*
https://www.acmicpc.net/problem/4803
*** 트리 Gold IV***
*** 접근 방향 ***
트리는 사이클이 없다는 점에서 착안하되, 여러 tree로 이루어져있음을
인지하고 문제를 접근한다.
0~N까지 접근하되 이미 방문한 노드를 방문하는 경우는 
부모 경우 밖에 없어야 한다.(즉 한번만 방문해야함.) 
 > ?? 무슨 오류가 있지.?
*** 알고리즘 자료구조 스킬 ***
BFS, 트리
*** 문제 조건 및 이해 ***
트리? 사이클이 없는 연결 요소. 정점n 간선n-1, 두 정점에 대해서 경로가 유일
그래프가 주어졌을 때, 트리의 개수를 세는 프로그램
@input
    @테스트 케이스 여러개임.
    @ 정점개수 n(<=500)과 간선개수 m(<=(n)(n-1)/2)이 주어짐.
     이후 m개에 대해 간선을 나타내는 두 정수가 주어짐.
     입력 마지막에 0 0 이 주어짐.
     정점은 1부터 n까지 번호가 매겨져 있음.
 */
package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q4803 {
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> tree;
    static int edge = 0;
    static int vertex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int testNum = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 || M == 0)
                break;

            tree = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                tree.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken()) - 1;
                int n2 = Integer.parseInt(st.nextToken()) - 1;
                tree.get(n1).add(n2);
                tree.get(n2).add(n1);
            }
            visit = new boolean[N];

            int treeCount = 0;
            for (int i = 0; i < N; i++) {
                edge = 0;
                vertex = 0;
                if (!visit[i]) {
                    treeCount += checkTree(i);
                }
            }

            if (treeCount == 0) {
                sb.append("Case ").append(testNum).append(": ").append("No trees.").append("\n");
            } else if (treeCount == 1) {
                sb.append("Case ").append(testNum).append(": ").append("There is one tree.").append("\n");
            } else {
                sb.append("Case ").append(testNum).append(": ").append("A forest of ").append(treeCount)
                        .append(" trees.").append("\n");
            }
            testNum++;
        }
        System.out.print(sb.toString());
    }

    static int checkTree(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int node = 0, edge = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            node += 1;
            visit[cur] = true;
            for (int next : tree.get(cur)) {
                edge += 1;
                if (!visit[next]) {
                    q.offer(next);
                }
            }
        }
        return (edge / 2) + 1 == node ? 1 : 0;
    }

}
