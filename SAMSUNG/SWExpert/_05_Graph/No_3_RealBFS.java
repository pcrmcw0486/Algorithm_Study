package SAMSUNG.SWExpert._05_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
트리에서 BFS (루트가 있는 트리)
1부터 N까지 번호가 붙은 1이 루트이자 탐색 시작점.
//탐색을 하는 노드의 자식들을 작은 번호부터 순서대로 큐의 뒤쪽에 넣는 방식으로 탐색이 진행된다.
//BFS를 한다면 노드를 방문하는 순서가 정해지고, 그 순서를 따라 최단거리로 트리를 이동하여 모든 노드를 탐색한다.
몇개의 간선을 지나야하는가
공통부모까지의 높이의 합. 이거든요.
공통부모를 찾아내야됩니다.
then how?
BFS 특성상 연결지어서 할 수 있는게 있을까?
배열을 통해 접근 가능할까?

LCA Dp로 만들어보기
dp[i][j] = i와 j의 공통 조상.
dp[x][x] = x;
어떤 노드 a,b 에 대해 공통조상은 dp[a][b]
dp[a][b]를 모르고 있을 때
dp[a][b] = dp[parent[a][b] or dp[a][parent[b]] or dp[parent[a][parent[b]]
a의 depth가 낮을 때 -> dp[parent[a]][b]
b의 depth가 낮을 때 -> dp[a][parent[b]]
depth가 같을 때 dp[parent[a]][parent[b]]

lca의 dp                                                                                                                ㅓ
* */
public class No_3_RealBFS {
    static int[] dist;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] order;
    static int[] depth;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');

            //data Init
            int N = Integer.parseInt(br.readLine());
            tree = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                tree[i] = new ArrayList<Integer>();
            }

            depth = new int[N + 1];
            order = new int[N];

            //tree의 최대 높이
            K =(int)Math.ceil(Math.log(N)/Math.log(2))+1;
            parent = new int[N+1][K];
            //make Tree
            st = new StringTokenizer(br.readLine());
            for (int i = 2; i < N + 1; i++) {
                int p = Integer.parseInt(st.nextToken());
                parent[i][0] = p;
                tree[p].add(i);// asc ordering.
            }

            //BFS for order and depth
           int  h = 0;
            int idx = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(1);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    queue.addAll(tree[cur]);
                    depth[cur] = h;
                    order[idx++] = cur;
                }
                h++;
            }
            //h가 최대 dpeth
            //makeDP
            for(int i = 1;i<K;i++){
                for(int j=1;j<N+1;j++){
                    parent[j][i] = parent[parent[j][i-1]][i-1];
                }
            }


            //solution
            long ans = 0;
            for (int i = 1; i < N; i++) {
                int a = order[i - 1];
                int b = order[i];
                int lca = lca(a,b);
                ans += depth[a]-depth[lca];
                ans += depth[b] - depth[lca];
            }

            sb.append(ans).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static int lca(int a, int b){

        //깊이 낮은쪽 기준
        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }
        for(int i = K-1;i>=0;i--){
            if(Math.pow(2,i) <= depth[a]-depth[b]){
                a = parent[a][i];
            }
        }
        if( a== b) return a;
        for(int i= K-1;i>=0;i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
//     시간초과 LCA
//    public static int solve(int a, int b) {
//        int cnt =0;
//        if(depth[a] > depth[b]){
//            while(depth[a] != depth[b]){
//                a = parent[a];
//                cnt++;
//            }
//        }else if(depth[a] < depth[b]){
//            while (depth[a] != depth[b]) {
//                b = parent[b];
//                cnt++;
//            }
//        }
//        while(a != b){
//            a = parent[a];
//            b = parent[b];
//            cnt += 2;
//        }
//        return cnt;
//    }
}

