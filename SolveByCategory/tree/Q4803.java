/*
https://www.acmicpc.net/problem/4803
*** 트리 Gold IV***
*** 접근 방향 ***
트리는 사이클이 없다는 점에서 착안하되, 여러 tree로 이루어져있음을
인지하고 문제를 접근한다.
0~N까지 접근하되 이미 방문한 노드를 방문하는 경우는 
부모 경우 밖에 없어야 한다.(즉 한번만 방문해야함.) 
 > ?? 무슨 오류가 있지.? 시발 input 개같은거 or로 해서 시발 몇시간을 날려먹은거냐
 >> dfs 또는 간선 개수 등으로 트리특징을 이용해서 풀이가 가능하다.
 >> dfs 또는 bfs로 vertex개수와 edge개수를 구하는데 이때 트리는
 >> 노드 n에 edge가 n-1이고, 양방향 그래프이므로 모든 edge를 통과하면
 >> 2n - 2이므로 (2n-2)/2 +1 = vertex 를 이용해 풀 수도 있겟다.
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
package SolveByCategory.tree;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int testNum = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;

            tree = new ArrayList<>();
            for (int i = 0; i < N + 1; i++) {
                tree.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                tree.get(n1).add(n2);
                tree.get(n2).add(n1);
            }
            visit = new boolean[N + 1];
            int treeCount = 0;
            for (int i = 1; i < N + 1; i++) {
                if (!visit[i] && travelTree(i))
                    treeCount++;
            }

            if (treeCount == 0) {
                sb.append(String.format("Case %d: No trees. \n", testNum));
            } else if (treeCount == 1) {
                sb.append(String.format("Case %d: There is one SolveByCategory.tree.\n", testNum));
            } else {
                sb.append(String.format("Case %d: A forest of %d trees.\n", testNum, treeCount));
            }
            testNum++;
        }
        System.out.print(sb);
    }

    public static boolean travelTree(int start) {
        boolean result = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : tree.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                    tree.get(next).remove(tree.get(next).indexOf(cur));

                } else {
                    return false;
                }
            }
        }
        return result;
    }

}
