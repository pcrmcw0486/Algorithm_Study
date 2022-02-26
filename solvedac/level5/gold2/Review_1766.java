package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_1766
 * @문제이름 : 문제집
 * @난이도 : Gold II
 * @date : 2022-02-19 오전 10:43
 * @author : pcrmcw0486
 * @문제이해
 * "N 문제를 풀려고 한다. 각 문제는 난이도 순서로 출제되어 있다 (번호에 따라)
 * "먼저 푸는 문제" 가 있다는 것을 알게 되었다.
 * 1. N개의 문제는 모두 풀어야 한다.
 * 2. 먼저 푸는 것이 좋은 문제는 먼저 푸는 것이 좋은 문제를 풀어야 한다.
 * 3. 가능하면 쉬운 문제를 풀어야 한다.
 * 풀 문제의 순서를 정해주세요,
 * @알고리즘
 * 위상정렬 , 정렬 문제.
 * @접근방법
 * 순서가 정해지고 순서에 따라 풀어야한다. (위상 정렬)
 * 근데, 가능하면 쉬운거 먼저 풀고 싶다.
 * 두가지의 기준이 있다. 이 두가지 기준을 모두 만족하여야 한다.
 * 만족함에 있어서 두 가지 기준 중 가장 큰 기준은 무엇인가.
 * 가장 우선시 되어야 하는 기준은 ' 가능하면 쉬운 것 먼저 풀고 싶다'가 되어야 한다.
 * 이후에 근데 풀게 있다면... 이 되는 것이 맞음
 * 즉, 조금 헷갈린다면 문제를 풀고 있다고 생각해보자
 * 1번 부터 풀려고 하는데 1번에 추천 문제 10번이 적혀있다.
 * 적힌 추천 문제로 갔더니 또 추천 문제7번이 적혀있다고 생각해보자.
 * 7번 10번을 다 푼 후에 1번을 풀고 그 다음 2번 문제로 넘어가는거다.
 * 2번 문제에서도 위와 같은 작업을 반복한다고 생각하면 된다.
 * 즉 큰 Base는 작은 문제 부터 푸는 건데 먼저 풀게 있다면 먼저푸는것이라고 생각하면 된다.
 * 이 먼저 푸는것 을 위상정렬로 생각하여 진행하고,
 * 문제를 푸는 것이 가능한 문제들에 대해 정렬해주기 위해 PriorityQueue 를 통해 관리한다.

*/
public class Review_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] inDegree = new int[N+1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            inDegree[target]++;
            graph[prev].add(target);
        }
        PriorityQueue<Integer> problems = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i =1;i<N+1;i++){
            if(inDegree[i] ==0){
                problems.add(i);
            }
        }
        while (!problems.isEmpty()) {
            int cur = problems.poll();
            sb.append(cur).append(' ');
            for (int next : graph[cur]) {
                inDegree[next]--;
                if(inDegree[next] == 0){
                    problems.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
