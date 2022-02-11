package SAMSUNG.SWExpert._05_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 영준이의 real BFS
 * bfs 순서대로 진행을 하면서 거리를 측정하여 이동거리를 계산한다.
 * 이 때 어떠한 u에서 v로 갈 때는 항상 공통조상을 거쳐서 지나가게 된다.
 * 그렇게 되면 u에서 공통조상까지의 거리 + v에서 공통조상까지의 거리가 더해지게 된다.
 * 필요한건 현재와 이전값이 필요하다.
 * Point는 어차피 BFS를 한번 해야합니다.
 * 이 때 기록을 해 두고, 값 두개씩 가져와서 계산하는게 나을 듯합니다.
 * root는 항상 1이고, 타색하는 노드는 작은 번호부터 탐색한다.
 * */
public class Review_No3 {
    //LCA를 구해야합니다.
    //parent가 정해져서 나온다.
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[][] parent;
    static int maxLevel;
    static int[] order;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            N = Integer.parseInt(br.readLine());
            maxLevel = calcMaxLevel(N) + 1;
            depth = new int[N+1];
            parent = new int[N + 1][maxLevel];
            tree = new ArrayList[N+1];
            order = new int[N];
            for (int i = 0; i < N + 1; i++) tree[i] = new ArrayList<Integer>();

            st = new StringTokenizer(br.readLine());
            for(int i= 2;i<N+1;i++){
                int p = Integer.parseInt(st.nextToken());
                parent[i][0] = p;
                tree[p].add(i);
            }

            //BFS with height And parent init
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(1);
            int idx =0 ;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                order[idx++] = cur;
                //parent 배열 계산
                for(int i =1;i<maxLevel;i++){
                    parent[cur][i] = parent[parent[cur][i-1]][i-1];
                }
                for (int nxt : tree[cur]) {
                    depth[nxt] = depth[cur] +1;
                    queue.add(nxt);
                }
            }
            //LCA  항상 i가 i+1보다 depth가 낮음. 항상 always
            long ans =0;
            for(int i =0;i<N-1;i++){
                int lca = LCA(order[i], order[i + 1]);
                ans += (depth[order[i]] - depth[lca]) + (depth[order[i + 1]] - depth[lca]);
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
    public static int LCA(int a, int b){
        //높이를 맞춰주어야겟죠
        for(int i =maxLevel;i>=0;i--){
            if(((depth[b] - depth[a])&1<<i)>0){
                b = parent[b][i];
            }
        }
        if(b==a) return a;
        for(int i = maxLevel-1;i>=0;i--){
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }


    public static int calcMaxLevel(int n) {
        double log2 = Math.log(n)/Math.log(2);
        return (int) Math.ceil(log2);
    }
}
