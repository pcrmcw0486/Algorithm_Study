/*
https://www.acmicpc.net/problem/2252
줄세우기 Gold II
*** 접근 방법 및 문제조건 ***
N명의 학생들을 키 순서대로 줄 세운다.
두 학생의 키를 비교한다. 일부 학생의 키만 비교해보았다.
줄을 세우시오.
3 2
1 3 
2 3  > 1 2 3
union-find처럼 생각했다..
4 2
3 1
? 3 1 4 2 
? 3 4 1 2 즉 그냥 정해진 순서대로만 나오면된다.
N 32_000
M 100_000
*** 알고리즘 자료구조 스킬 ***
위상정렬, 트리
 */
package SolveByCategory.tree.topology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2252 {
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        visit = new boolean[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            graph[v].add(w);
        }
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                dfs(i);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop() + 1).append(" ");
        }
        System.out.println(sb.toString());

    }

    public static void dfs(int v) {
        visit[v] = true;
        for (int next : graph[v]) {
            if (!visit[next]) {
                dfs(next);
            }
        }
        stack.push(v);
    }
}
