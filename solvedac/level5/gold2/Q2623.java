package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2623
 * @문제이름 : 음악프로그램
 * @난이도 : Gold II
 * @date : 2022-02-13 오후 4:04
 * @author : pcrmcw0486
 * @문제이해
 * 가수의 출연 순서를 정하다.
 * 출연가수 순서들이 주어질 때, 가수 출연 순서를 정해보자.
 * N <= 1000 M<=100
 * 순서를 정하는 것이 불가능할 경우 0을 출력한다.
 * @알고리즘
 * 위상정렬 문제
 * @접근방법
 *  basic한 위상정렬 문제이다.

*/
public class Q2623 {
    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        inDegree = new int[N+1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<Integer>();
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for(int j =1;j<cnt;j++){
                int next = Integer.parseInt(st.nextToken());
                graph[prev].add(next);
                inDegree[next]++;
                prev= next;
            }
        }
        int ans =0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i =1;i<N+1;i++){
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append('\n');
            ans++;
            for (int x : graph[cur]) {
                inDegree[x]--;
                if (inDegree[x] == 0) {
                    queue.add(x);
                }
            }
        }
        System.out.println(ans==N?sb.toString():0);
    }
}
