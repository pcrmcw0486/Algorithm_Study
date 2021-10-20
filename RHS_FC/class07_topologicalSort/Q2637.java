package RHS_FC.class07_topologicalSort;
/*
2021.10.20
문제번호 : Q2637
이름 및 난이도 : 장난감 조립 Gold II
문제이해 
-----------------
- 장난감 여러 부품으로 조립
- 기본부품과 기본부품으로 이루어진 중간 부품
- ex) 중간부품 5 는 2개의 기본부품 1과 2개의 기본부품 2로 만들어짐.
      중간부품 6은 2개의 중간부품5, 3개의 기본 부품3, 4개의 기본부품 4
       완제품 7은 2개의 중간부품5, 3개의중간부품 6, 5개의 기본부품 4
 - 완제품을 조립하기 위한 필요 기본부품의 종류별 개수를 계산하시오.
접근 방법 :
 X를 만들기 위해 필요한 [A,Acnt], [B,Bcnt]가 있다고 할 때
 다시 A또는 B로 가서 게산해야함.
 기본부품의 경우 필요한 것이 없음.
 완제품으로 부터 기본부품까지 내려가는 것이 올바른 순서임.
제한 조건 : 
*/
import java.io.*;
import java.util.*;
public class Q2637 {

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] ans =  new int[N+1];
        ArrayList<int[]>[] order = new ArrayList[N+1];
        int[] inDegree = new int[N+1];
    
        for(int i = 0; i < N + 1; i++) order[i] = new ArrayList<int[]>();
        for(int i =1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            order[x].add(new int[]{y,k});
            inDegree[y]++;
        }
        Deque<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{N,1});
        ans[N] = 1;
        //거꾸로 했음. 필요하다의 개념
        // 충족한다의 개념. 상태가 [N+1][N+1]개 있었어야함.
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] nxt : order[cur[0]]){
                inDegree[nxt[0]]--;
                ans[nxt[0]] += ans[cur[0]] * nxt[1];
                if(inDegree[nxt[0]] == 0)
                    queue.add(nxt);
            }
        }
        for(int i = 1;i<N+1;i++){
            if(order[i].size() == 0){
                sb.append(i).append(" ").append(ans[i]).append('\n');
            }
        }
        System.out.print(sb);
    }
}
