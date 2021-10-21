package RHS_FC.class09_dp;
/*
2021.10.21
문제번호 : Q1949
이름 및 난이도 : 우수마을 Gold I
문제이해 
-----------------
N개 마을,Tree구조
1. '우수마을'로 선정된 마을 주민 수의 총합을 최대로 한다.
2. 마을 사이의 충돌을 방지하기 위해 '우수마을'은 인접할 수 없다.
3. '우수마을'로 선정되지 못한 마을은 적어도 하나의 '우수마을'과 인접해야한다.
어떤 마을에 대해서 상태는 총 두가지
우수마을 이거나 우수마을이 아니거나.
우수마을이 아닌 노드의 child는 모두 우수마을인가? 아닐 수 있음.
루트의 상태는 일단 두가지로 정해짐.
dp[root][0] : 우수마을 아님 
dp[root][1] : 우수마을
dp[root][0] 에대해서는 +=Math.max(dp[child][0], dp[child][1]) child가 0일수도 1일 수도 있으므로..
dp[root][1]이라면 += dp[child][0] 우수마을이 아니여야함.
dp[node][0] = 일단 기본적으로 0값이 들어가고
dp[node][1] = 자신의 값이 들어갈거임.
접근 방법 :
제한 조건 : 
*/
import java.io.*;
import java.util.*;
public class Q1949 {
    static int[][] dp;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        dp = new int[N+1][2];
        for(int i =0;i<N+1;i++)tree[i] = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());
        for(int i =1;i<N+1;i++){
            dp[i][0] = 0;
            dp[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i =0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        //트리라면 어느 하나를 잡아도 밑으로 떨어뜨리면 tree형태가 되므로, 아무 root나 잡는다.
        dfs(1,-1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int cur, int prev){
        for(int nxt : tree[cur]){
            if(nxt == prev) continue;
            dfs(nxt,cur);
            dp[cur][0] += Math.max(dp[nxt][1], dp[nxt][0]);
            dp[cur][1] += dp[nxt][0];
        }
    }
}
