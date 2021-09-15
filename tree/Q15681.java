package tree;

//Q15681 java
// 트리 20210903
import java.io.*;
import java.util.*;

public class Q15681 {
    static int[] parent;
    static int[] size;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        parent = new int[N + 1];
        size = new int[N + 1];
        Arrays.fill(size, 1);

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        parent[root] = 0;
        makeTree(root);
        countVertex(root);
        for (int i = 0; i < M; i++) {
            sb.append(String.valueOf(size[Integer.parseInt(br.readLine())])).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void makeTree(int current) {
        for (Integer child : graph[current]) {
            if (child != parent[current]) {
                parent[child] = current;
                makeTree(child);
            }
        }
    }

    public static void countVertex(int current) {
        for (Integer child : graph[current]) {
            if (child != parent[current]) {
                countVertex(child);
                size[current] += size[child];
            }
        }
    }
}
