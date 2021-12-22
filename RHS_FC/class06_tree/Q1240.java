package RHS_FC.class06_tree;

/*
노드 사이의 거리 Gold V
N(2~1_000)개 노드로 이루어진 트리가 주어지고
M<=1000개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력한다.
N > N-1 / M
tree에서 makeTree를 하지 않고 rootedTree처럼 하려면
prev 매개변수로 두고 안보면 그게 그냥 rooted Tree 순회가 됨.parent안보는것처럼
생각하면 되므로.
 */
import java.io.*;
import java.util.*;

public class Q1240 {
    static int nodeDist[];
    static ArrayList<Edge>[] tree;
    static final int UNVISITED = 10001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        nodeDist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            tree[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree[u].add(new Edge(v, d));
            tree[v].add(new Edge(u, d));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Arrays.fill(nodeDist, UNVISITED);
            solve(u, v);
            sb.append(nodeDist[v]).append('\n');
        }
        System.out.print(sb);
    }

    public static void solve(int u, int v) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(u);
        nodeDist[u] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == v) {
                break;
            }
            for (Edge nxt : tree[cur]) {
                if (nodeDist[nxt.to] == UNVISITED) {
                    nodeDist[nxt.to] = nodeDist[cur] + nxt.dist;
                    queue.add(nxt.to);
                }
            }
        }
    }

    private static class Edge {
        int to;
        int dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
