package solvedac.level5.gold3;

/*
2022.01.20
문제번호 : Q9466
이름 및 난이도 : 텀 프로젝트 Gold III
문제이해 
-----------------
프로젝트 팀원 수 제한이 X
(모든 학생이 한팀도 가능)
팀을 구성하기 위해 함께하고 싶은 학생 선택(단 한명만 선택가능, 자기 자신도 선택 가능)

제한 조건 : 
 T 
 학생의 수 2 <= n <=100_000

접근 방법 :
   한 팀이 되는 방법
    - 혼자서 
    - 또는 어떠한 그룹이 cycle을 이룰 때. cycle을 이루는 것 끼리.
    cycle을 이루는 가 라는 문제이다.
    어떠한 cycle그룹을 완성해 내야한다.
    
    disjoint set 문제라고 생각했음.
    cycle check 여부 그로 인해 만들어지는 어떠한 하나의 그룹.(수가 제한이 없다)
     ?? 어떠한 그룹이 cycle이 될 때 그 그룹은 모두 cycle이 되는가?
     그건 아니라는 걸 깨달음. 방향성이 없다면 가능할 수 있겠다.

    팀이 되는 방법을 자세히 보면 모두가 서로 연결되어 있는 것. 근데 이 원하는 필요한 노드에 방향성이 있음.
    즉 순서에 따라 진행했을 때 inDegree[] != 0 이 아닌 것.
    inDegree[] =0 이라는 뜻이 같이 하고싶은 사람이 없다는 것.
    (어떠한 노드가 연결이 되어있을 때 cycle이 되려면 그 그룹의 inDegree[]는 항상 1이상이다)

    추상화?
    어떤 방향 그래프에서 각 노드들의 edge가 하나밖에 없을 때,
    cycle을 이루는 집합을 구하세요.
    cycle을 이루는 모습을 보았을 때? edge는 하나 뿐임.
    위상정렬이 떠오르겟죠.

    또는 DFS로 푸는 방법이 있을 수 있겟다. Cycle이니까 자신에서 출발해서 자신으로 돌아오는 길이 있는지 확인.

*/

import java.io.*;
import java.util.*;

public class Q9466 {
    static int N;
    static int inDegree[];
    static BufferedReader br;
    static int[] list;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            input();
            int ans = solution();
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        inDegree = new int[N + 1];
        inDegree[0] = -1; // dummy
        list = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            int want = Integer.parseInt(st.nextToken());
            inDegree[want]++;
            list[i] = want;
        }
    }

    public static int solution() {
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                cnt++;
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int nxt = list[cur];
            inDegree[nxt]--;
            if (inDegree[nxt] == 0) {
                cnt++;
                queue.add(nxt);
            }

        }
        return cnt;
    }

    static int cnt;
    static boolean[] visited;
    static boolean[] isFinished;

    public static void anotherSolve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            list = new int[N + 1];
            list = new int[N + 1];
            visited = new boolean[N + 1];
            isFinished = new boolean[N + 1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i < N + 1; i++)
                dfs(i);
            sb.append(N - cnt).append('\n');
        }
    }

    public static void dfs(int cur) {
        if (visited[cur])
            return;
        visited[cur] = true;

        int nxt = list[cur];
        if (!visited[nxt])
            dfs(nxt);
        else {
            // 일반적인 dfs를 하다가 방문된 노드를 만났다. 즉, cur -> nxt)~~~->(cur 인 상황 만나버렸다.
            // visited 확인시 방문한적이 있다면 여기서 Cycle을 이룬다. 어디서든
            // 1-> 3-> 3이면 2번째 3에서
            // 4 -> 6 -> 7 -> 4라면 마지막 4에서
            if (isFinished[nxt] != true) { // 처음 체크한다면 해당 cycle을 모두 체크해준다. nxt노드는 cycle의 일부분일 테니까.
                cnt++;
                for (int i = nxt; i != cur; i = list[i])
                    cnt++;
            }
        }
        isFinished[cur] = true;
    }
}
