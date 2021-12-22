package RHS_FC.class07_topologicalSort;

/*
영우는 사기꾼? Gold IV
직접적인 연관이 없으면 지을 수 있음.
최대 3개의 건물에게만 영향을 미치도록 함.
치트키로 원하는 건물은 마음대로 설치 가능.
치트키를 사용하였는지 확인해보자.
치트키로 건설한 건물은 건설 정보에 들어가지 않는다.
1 a (a건물 1개 건설)
2 a (a건물 1개 파괴)
건물종류, 관계수, 게임정보 개수 10^5
필요한 데이터는
현재 각 건물의 건설 개수 정보

발상의 전환!
A가 영향을 주는 건 최대 3개이지만
영향을 받는 경우는 최대 99_999 가능하다.
for문을 돌리면서 N-1씩 보는건 시간을 많이 잡아먹는데
조금 다르게 생각해서 건물 짓는데 필요한 개수로 접근
건물이 올라가면 개수 증가시켜주고 inDegree를 잘 접합해서 푼 문제이다.ㄴ
 */
import java.io.*;
import java.util.*;

public class Q14676 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        int[] cnt = new int[N + 1];
        int[] indeg = new int[N + 1];
        int[] satisfaction = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            indeg[v]++;
        }
        boolean cheet = false;
        while (K-- > 0 && !cheet) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            if (t == 1) {
                if (satisfaction[x] < indeg[x])
                    cheet = true;
                cnt[x]++;
                if (cnt[x] == 1) {
                    for (int y : adj[x])
                        satisfaction[y]++;
                }
            } else {
                if (cnt[x] == 0)
                    cheet = true;
                cnt[x]--;
                if (cnt[x] == 0) {
                    for (int y : adj[x])
                        satisfaction[y]--;
                }
            }
        }
        System.out.println(cheet ? "Lier!" : "King-God-Emperor");
    }
}
