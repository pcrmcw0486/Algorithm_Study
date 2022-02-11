package RHS_FC.class06_tree;
/*
트리 Gold V
리프노드는 자식의 개수가 0인 노드를 말함.
트리가 주어질 때 노드 하나를 지울때 남은 트리에서 리프노드의 개수를 구하는 프로그램을 작성
노드의 모든 자손이 사라짐.
-1을 부모로 갖는 노드가 루트노드임.

쉽게 생각하면 노드 구성하고
삭제된 노드 방문 안하고 리프노드 구하면 되긴함.
쉽게 생각하면됨. 그럼 언제 리프노드가 되느냐.
연결된 노드가 하나이고 부모이면. 
으로 생각하고 dfs돌리면 금방하긴하는데 더 좋은 방법이 있을까?
일단 직관적으로 생각나는대로 한번 해봄.

말고 다른 방법은,...
밑에서 위로 올라간다? 이건 어차피 leaf node가 뭔지 몰라서 못하고.
위에서 아래로 밖에 답이 없는데.
이러나 저러나 한번 도는건 맞음.O(V+E)
 */
import java.io.*;
import java.util.*;
public class Q1068 {
    static int cnt =0;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static int target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        tree= new ArrayList<ArrayList<Integer>>();
        for(int i =0;i<N;i++) tree.add(new ArrayList<Integer>());
        int root = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int u =0;u<N;u++){
            int v = Integer.parseInt(st.nextToken());
            if(v == -1) {root = u; continue;}
            tree.get(v).add(u);
        }   
        target = Integer.parseInt(br.readLine());
        
        visited[target] = true;
        dfs(root);
        System.out.println(cnt);
    }

    public static void dfs(int cur){
        boolean isLeaf = true;
        if(visited[cur]) return;
        visited[cur] = true;
        for(int nxt : tree.get(cur)){
            if(!visited[nxt]){
                isLeaf = false;
                dfs(nxt);
            }
        }
        if(isLeaf) cnt++;
    }
}
