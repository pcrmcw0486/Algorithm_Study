/*
https://www.acmicpc.net/problem/1167
https://www.acmicpc.net/problem/1967

 *** 트리의 지름 Gold III  1967과 동일***
 *** 접근 방향 및 방법 ***
 트리의 특징 중 Vertex가 N개라면
 Edge는 N-1개임.
 DFS 또는 BFS시간복잡도가 인접 리스트 사용시 O(V+E) -> O(2N-1)
 을 각각 N번씩 하면 O(N^2)에 해결이 가능함. 그래프의 경우에는 시간을
 만족하지 못할 수 있으나 트리의 특성 때문에 해결가능하다고 생각함 하여
 각 Vertex마다 DFS로 가장 긴 길이를 구하여 Max 값을 solve
  > 라고 생각했으나 시간 초과.
  > 쉽게 트리를 펼쳐 본다고 생각하면 어느 지점에서 잡던 가장 깊게 들어가면 그 곳이
  > 항상 지름의 두 점 중 하나가 된다.
  > 그 지점에서 다시 dfs를 통해 지름을 구한다.
 *** 사용 알고리즘, 자료구조, 스킬 ****
 가장 긴 거리를 탐색해야함.
 거리가 양수라서 현재까지 Max라면 더 갔을 때 더 커질 수 있음
 또한 부분의 Max가 전체의 Max는 되지 못하는 경우가 존재함.

 1번 더 깊게 들어갈 생각을 해보자.
 문제를 간단히 함에 있어서 이미지를 떠올려서 간단히 해보자.
 *** 문제 조건 ***
 @time : 2sec
 @memory : 256MB
 @트리의 지름? 트리에서 임의의 두 점 사이의 거리 중
   가장 긴 것을 말한다.
 @Input
    @ V : 정점개수 (2<=V <=100_000)
    @ V개 줄에 걸친 간선 정보(1부터 V)
        간선정보 ? 정수가 두개씩 주어짐
         1. 정점번호
         2. 그 정점까지의 거리
         1번 3까지 2 (1 3 2 -1)
         2번 4까지 4 (2 4 4 -1)
         3번 1까지 2 4까지 3(3 1 2 4 3 -1)
         거리는 10_000 이하의 자연수
         > 최대거리? 정점개수 V-1 * 최대 거리 10_000
          => 1_000_000_000(int에서 가능함)
 */
package SolveByCategory.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node1 {
    int end;
    int length;

    Node1(int end, int length) {
        this.end = end;
        this.length = length;
    }
}

public class Q1167 {
    static List<Node1>[] SolveByCategory.tree;
    static boolean[] visit;
    static int point1;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        StringTokenizer st = null;
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<Node1>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end, length;
            while ((end = Integer.parseInt(st.nextToken())) != -1) {
                length = Integer.parseInt(st.nextToken());
                tree[start].add(new Node1(end, length));
            }
        }
        visit = new boolean[N + 1];
        dfs(1, 0);
        visit = new boolean[N + 1];
        dfs(point1, 0);
        System.out.println(result);
    }

    public static void dfs(int cur, int length) {
        visit[cur] = true;
        if (length > result) {
            result = length;
            point1 = cur;
        }
        for (Node1 next : tree[cur]) {
            if (!visit[next.end]) {
                visit[next.end] = true;
                dfs(next.end, length + next.length);
            }
        }
    }
}
