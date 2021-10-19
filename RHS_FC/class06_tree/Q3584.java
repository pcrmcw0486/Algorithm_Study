package RHS_FC.class06_tree;

/*
가장 가까운 공통 조상 Gold IV
rooted Tree
Nearest Common Ancestor ( A, B)
 > A와 B 를 모두 자손으로 가지면서 깊이가 가장 깊은 노드
  ( 즉 가장 가까운 노드)
노드 수 N ( 10_000) 간선 N-1
순차적으로 진행되면 안됨.
여러 방법이 있을 수 있는데 각 노드에 depth를 기록해두는 방법이 있을 수도 있고
depth가 같아진다면 같이 비교 가능하기 때문에.

set은 visit으로 할 수 있을까 생각해볼만한 가치가 있다. 
그래프나 트리에서 
 */
import java.io.*;
import java.util.*;

public class Q3584 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            int[] target = new int[2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if (i == N - 1) {
                    target[0] = u;
                    target[1] = v;
                    continue;
                }
                parent[v] = u;
            }
            Set<Integer> set = new HashSet<Integer>();
            int t = target[0];
            while (t != 0) {
                set.add(t);
                t = parent[t];
            }

            t = target[1];
            while (t != 0) {
                if (set.contains(t)) {
                    sb.append(t).append('\n');
                    break;
                }
                t = parent[t];
            }

        }
        System.out.print(sb);
    }
}
