package RHS_FC.class06_tree;

/*
나무탈출 Silver I
N개의 정점 트리 모양 게임판과 게임말
1~N번 번호
1번 정점은 루트노드 , 자식이 없는 노드는 리프노드
모든 리프노드에 게임말이 놓여있음.
사람의 차례에 말이 놓여있던 노드의 부모노드로 옮김
 ( 한 노드에 여러 개의 게임 말이 놓일 수 잇음)
 만약 어떤 게임말이 루트노드에 도착했다면 게임말을 즉시 제거한다.
 게임말에 게임판에 존재 하지 않아 고를 수 없는 사람이 진다.
 성원이가 선 ㅇㅋㅇㅋ
 결국 횟수 싸움임. 리프노드에서 root까지의 거리를 모두 더했을 때
 홀수라면 선이 이기는 싸움이고 짝수라면 이길 수 없는 게임임.
 최소거리이기때문에.
 */
import java.io.*;
import java.util.*;

public class Q15900 {
    static int cnt = 0;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Integer>>();
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph.add(new ArrayList<Integer>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(-1, 1, 0);
        System.out.println(cnt % 2 == 0 ? "No" : "Yes");
    }

    public static void dfs(int parent, int cur, int dist) {
        boolean isLeaf = true;
        visited[cur] = true;
        for (int nxt : graph.get(cur)) {
            if (!visited[nxt]) {
                isLeaf = false;
                dfs(cur, nxt, dist + 1);
            }
        }
        if (isLeaf)
            cnt += dist;
    }

}
