package solvedac.level5.gold2;

import jdk.jshell.Snippet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q1766
 * @문제이름 : 문제집
 * @난이도 : Gold II
 * @date : 2022-02-12 오전 11:00
 * @문제이해 1~ N 개의 문제는 난이도 순서대로 번호가 붙여져 있다.
 * '먼저 푸는 것이 좋은 문제'가 잇다.
 * 조건에 따라 푼다.
 * N개의 문제는 모두 푼다.
 * 먼저 푸는 것이 좋은 문제가 있는 문제는 먼저 푸는 것이 좋은 문제를 먼저 푼다.
 * 가능한 쉬운 문제 부터 푼다.
 * 번호가 낮은 것 부터 풀어나가야 한다.
 * @알고리즘 위상정렬
 * @접근방법
 */
public class Q1766 {
    static int N, K;
    static ArrayList<Integer>[] graph;
    static int[] haveToSolveCnt;
    static boolean[] isComplete;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        haveToSolveCnt = new int[N + 1];
        isComplete = new boolean[N+1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        for(int i =0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            haveToSolveCnt[v]++;
        }
        //쉬운 문제부터 풀기 관리

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<N+1;i++){
           if(haveToSolveCnt[i]==0)
               queue.add(i);
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(' ');
            for (int next : graph[cur]) {
                haveToSolveCnt[next]--;
                if (haveToSolveCnt[next] == 0) {
                    queue.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
