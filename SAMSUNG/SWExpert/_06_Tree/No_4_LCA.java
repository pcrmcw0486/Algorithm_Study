package SAMSUNG.SWExpert._06_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_4_LCA {
    static int V, E;
  //  static int parents[][];
  static int parent[];
    static int depth[];
    static ArrayList<Integer> tree[];
    static int H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tt = 1 ;tt<=T;tt++){
            ans.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            int targetA = Integer.parseInt(st.nextToken());
            int targetB = Integer.parseInt(st.nextToken());

            tree = new ArrayList[V+1];
            for (int i = 0; i < V + 1; i++) tree[i] = new ArrayList<Integer>();
            depth = new int[V+1];
           // H = getHeight(V);
           // parents = new int[V+1][V];
            parent = new int[V+1];

            st = new StringTokenizer(br.readLine());
            for(int i =0;i<E;i++){
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                //parents[c][0] = parent;
                parent[c] = p;
                tree[p].add(c);
            }

            findHeight();
            int lca = findLCA(targetA, targetB);
            int subTreeSize = findSubtreeSize(lca);
            ans.append(lca).append(' ').append(subTreeSize).append('\n');
        }
        System.out.println(ans.toString());
    }

    public static int findLCA(int a, int b){
        //a를 항상 낮은 위치에 둔다.
        //depth[a] > depth[b]
        int tmp;
        if(depth[a] < depth[b]){
            tmp = b;
            b = a;
            a = tmp;
        }
        //높이 맞추기
        while(depth[a] != depth[b]){
            a = parent[a];
        }
        while(a != b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
/*
    public static int findLCA(int a, int b){
        //depth 맞추기 위한 교환 (항상 a가 밑에 있도록)
        //depth[a] > depth[b]
        int tmp;
        if(depth[a] < depth[b]){
            tmp = b;
            b = a;
            a = tmp;
        }
        //depth맞추기
        //depth[i][x] 는 i의 x번째 조상임.
        for(int i =0 ;i<V-1;i++){
            if(depth[parents[a][i]] != depth[b]){
                parents[a][i+1] = parents[parents[a][i]][0];
            }else{
            a = parents[a][i];
            }
        }
        //parent찾으면서 갱신하기
        while(a != b){
            a = parents[a][0];
            b = parents[b][0];
        }
        return a;
    }

*/
    public static int findSubtreeSize(int num){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        int cnt =0;
        while (!queue.isEmpty()) {
            int cur =queue.poll();
            queue.addAll(tree[cur]);
            cnt++;
        }
        return cnt;
    }
    public static int getHeight(int V){
        int tmp = 1;
        while(tmp <=V){
            tmp<<=1;
        }
        return tmp;
    }

    //만약 위에서 부터 순서대로 주어지는 것이 보장된다면
    // 굳이 BFS를 하지 않고도, 높이를 구할 수 있다.
    public static void findHeight(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int h =0 ;
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i =0;i<size;i++){
                int cur = queue.poll();
                queue.addAll(tree[cur]);
                depth[cur] = h;
            }
            h++;
        }
    }
}
