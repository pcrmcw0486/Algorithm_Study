/*
*** 이분그래프 Gold IV ***
*** 접근 방법 ***
- 이분그래프이므로 SET 는 2개로 나눠질 수 있다.
- 만약 이분그래프라고 한다한쪽에서 BFS를 통해 접근 했을 때
- A->B->A->B 와 같은 순서로 SET가 진행 되어야한다.
*** 활용 알고리즘 및 활용 방법 ***
- BFS
- Set 자료구조를 활용하여 A->B->A->B set를 유지해야한다를 체크
*** 문제의 조건 및 풀이 방법 ***
- 이분 그래프의 판별 (YES OR No)
*/
package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            String input[] = br.readLine().split(" ");
            int size = Integer.parseInt(input[0]);
            int count = Integer.parseInt(input[1]);
            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
            Queue<Integer> q = new LinkedList<>();
            for (int j = 0; j < size + 1; j++)
                graph.add(new ArrayList<Integer>());
            int[] visit = new int[size + 1];

            for (int j = 0; j < count; j++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                graph.get(n1).add(n2);
                graph.get(n2).add(n1);
            }

            boolean option = true;
            for (int j = 0; j < size; j++) {
                if (visit[j] == 0) {
                    q.offer(j);
                    visit[j] = 1;
                    while (!q.isEmpty() && option) {
                        int edge = q.poll();
                        int set_num = visit[edge];
                        for (Integer vertex : graph.get(edge)) {
                            if (visit[vertex] == 0) {
                                visit[vertex] = -set_num;
                                q.offer(vertex);
                            } else if (visit[vertex] == set_num) {
                                option = false;
                                break;
                            }
                        }
                    }
                    if (!option)
                        break;
                }
            }
            sb.append(option ? "YES" : "NO").append("\n");
        }
        System.out.print(sb.toString());
    }
}
