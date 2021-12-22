package RHS_FC.class06_tree;
/*
트리와 쿼리 Gold V
 */

import java.io.*;
import java.util.*;
public class Q15681 {
    static int[] subTree;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        tree =new ArrayList[N+1];
        for(int i =0;i<N+1;i++){
            tree[i] = new ArrayList<Integer>();
        }
        for(int i =0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v =Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        subTree = new int[N+1];
        makeTree(R, -1);
        for(int i =0;i<Q;i++){
            int t = Integer.parseInt(br.readLine());
            sb.append(subTree[t]).append('\n');
        }   
        System.out.print(sb);
    }

    public static void makeTree(int cur, int prev){
        subTree[cur] = 1;
        for(int nxt : tree[cur]){
            if(nxt != prev){
                makeTree(nxt, cur);
                subTree[cur] += subTree[nxt];
            }
        }
    }
}
