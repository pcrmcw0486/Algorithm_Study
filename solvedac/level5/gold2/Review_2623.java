package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_2623
 * @문제이름 : 음악프로그램
 * @난이도 : Gold II
 * @date : 2022-03-01 오전 4:28
 * @author : pcrmcw0486
 * @문제이해
 * 출연 순서를 정하는 일.
 * 보조PD에게 출연 순서를 정해오게 함.
 * 보조PD가 정해온 출연순서를 모아 전체 가수 순서를 정하자.
 * 정하는 것이 불가능 하다면 0을 출력하자.
 * @알고리즘
 * @접근방법
 * 정해진 순서가 있다.
 * 1 4 3이라고 할 때 1 -> 4- > 3이라면 1->3 또한 보장이 된다. 3이 열리려면 4가 열려야하고 그럴려면 1이 열려야 하기 때문,
 * 위상정렬.
 * 위상정렬이 불가능한 경우는 언제인가?
 * 다음 어떠한 차례가 되어야하는 노드가 없는 상황 (다 보지 않았는데)
 * 그래프가 방향성이 있어야 하는데, 이를 거슬러 사이클이 생기는 경우이다.
*/
public class Review_2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N+1]; //1~N
        int[] inDegree = new int[N + 1];
        for (int i = 0; i < N+1; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int j = 1; j < size; j++) {
                int next = Integer.parseInt(st.nextToken());
                inDegree[next]++;
                graph[prev].add(next);
                prev= next;
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int cnt =0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append('\n');
            cnt++;
            for (int nxt : graph[cur]) {
                inDegree[nxt]--;
                if (inDegree[nxt] == 0) {
                    queue.add(nxt);
                }
            }
        }
        if (cnt == N) {
            System.out.print(sb);
        }else{
            System.out.println(0);
        }
    }
}
