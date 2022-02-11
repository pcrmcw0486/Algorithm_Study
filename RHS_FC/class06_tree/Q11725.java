package RHS_FC.class06_tree;
/*
트리의 부모 찾기 Silver II
루트 없는 트리가 주어질 때 트리의 루트를 1이라고 정했을 때 
각 노드의 부모를 구하는 프로그램을 작성하시오.

 */
import java.util.*;
import java.io.*;
public class Q11725 {
    static int[] parent;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> SolveByCategory.tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<ArrayList<Integer>>();
        for(int i =0;i<N+1;i++)
            tree.add(new ArrayList<Integer>());
        for(int i =0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());    
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }
        parent = new int[N+1];
        visited = new boolean[N+1];
        visited[1] = true;
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for(int i =2;i<N+1;i++){
            sb.append(parent[i]).append('\n');
        }
        System.out.print(sb);
    }
    public static void dfs(int cur){
        for(int nxt : tree.get(cur)){
            if(!visited[nxt]){
                parent[nxt] = cur;
                visited[nxt] = true;
                dfs(nxt);
            }
        }
    }
}
