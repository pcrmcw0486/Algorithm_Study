package RHS_FC.class07_topologicalSort;

/*
줄 세우기 Gold II
N명의 학생들을 키 순서대로 세우려고 한다.
두 학생의 키를 비교한 값이 온다.
일부 학생들의 키를 비교한 결과가 주어졌을 때 줄을 세우는 프로그램을 작성.

N <= 32000, M<= 100_000
두 학생의 번호 A,B가 주어질 때 A가 B 앞에 서야한다.
가능한 경우로 생각해야함. 문제에서 요구하는 것이 무엇인지를 파악해야한다는거
4 2
3 1 
일 때 3 4 1 2 일 수도 3 1 4 2 일수도 여러 정답이 나올 수 있지만
정확한 값이 아니라 위상정렬을 요구하고 있다는 것을 생각할 수 있다.
 */
import java.io.*;
import java.util.*;

public class Q2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];
        ArrayList<Integer>[] student = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            student[i] = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            indegree[b]++;
            student[a].add(b);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for (int nxt : student[cur]) {
                indegree[nxt]--;
                if (indegree[nxt] == 0)
                    queue.add(nxt);
            }
        }

        System.out.print(sb);
    }

}
