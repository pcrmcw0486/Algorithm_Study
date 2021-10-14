package RHS_FC.class05_graph;

/*
경로 찾기 Silver I
가중치 없는 방향 그래프 G가 주어질때 
모든 정점 i,j에 대해서 i에서 j로 가능 경로가 있는지 구하는 프로그램
정점 0~N-1 까지 dfs 하면됨.
 */
import java.io.*;
import java.util.*;

public class Q11403 {
    static ArrayList<ArrayList<Integer>> graph;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Integer>>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) {
                    graph.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i);
            for (int j = 0; j < N; j++) {
                sb.append(visited[j] ? '1' : '0').append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void dfs(int x) {
        for (int next : graph.get(x)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }

}
