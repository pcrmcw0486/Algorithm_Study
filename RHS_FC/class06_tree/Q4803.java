package RHS_FC.class06_tree;
/*
https://www.acmicpc.net/problem/4803
트리 Gold IV
 연결요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합이다.
 트리는 사이클이 없는 연결요소이다.
 즉, parent말고 중복되는 것이 있으면 안됨.
 또는 트리는 정점이 n개 간선이 n-1개 있다.
 
 트리의 특징을 이용하여 여러 방법으로 구할 수 있을 것임.
 예를 들어 연결되어 있는 모든 정점을 돌면서
 각 정점의 간선의 수를 합치면 tree의 경우
 2*(n-1)이 될것임. 2n-2 그래서
 간선수 합 2n-2에 대해 +2 > /2 하면 n이 되어야함.

 또는 사이클 개념으로 접근하여 parent말고 또 중복되는 정점이 있다면
 사이클이 있으므로 트리가 아님.

 이러나 저러나 연결되어 있는 요소들은 모두 check해서 빼주어야함.

 */
import java.io.*;
import java.util.*;
public class Q4803 {
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int vertex, edge;
    static boolean isCycle;
    public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringBuilder sb = new StringBuilder();
     StringTokenizer st;
     int T =1;
     while(true){
         int treeCount = 0;
         st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken());
         if(N == 0 && M == 0) break;
         tree  = new ArrayList<ArrayList<Integer>>();
         for(int i =0;i<N+1;i++) tree.add(new ArrayList<Integer>());
         for(int i =0;i<M;i++){
             st = new StringTokenizer(br.readLine());
             int u = Integer.parseInt(st.nextToken());
             int v = Integer.parseInt(st.nextToken());
             tree.get(u).add(v);
             tree.get(v).add(u);
         }
         visited = new boolean[N+1];
         //solution1
         for(int i =1;i<N+1;i++){
             if(!visited[i]){
                 visited[i] = true;
                 vertex =1;
                 edge = 0;
                 dfs(i);
                 if((edge/2)+1 == vertex) treeCount++;
             }
         }
         //solution2
         for(int i =1;i<N+1;i++){
             if(!visited[i]){
                isCycle = false;
                 dfs2(0,i);
                 if(!isCycle) treeCount++;
             }
         }
         sb.append("Case " + T +": ");
         if(treeCount ==0){
            sb.append("No trees.\n");
         }else if(treeCount == 1){;
             sb.append("There is one tree\n");
         }else{
            sb.append("A forest of " + treeCount + " trees.\n");
         }
         T++;
     }    
     System.out.print(sb);
    }
    public static void dfs(int cur){
        edge += tree.get(cur).size();
        for(int next : tree.get(cur)){
            if(!visited[next]){
                visited[next] = true;
                vertex++;
                dfs(next);
            }
        }
    }

    //cycle 체크하는 경우
    public static void dfs2(int before, int cur){
        visited[cur]= true;
        for(int next : tree.get(cur)){
            if(next != before){
                if(!visited[next]) dfs2(cur,next);
                else isCycle = true;
            }
        }

    }
}
