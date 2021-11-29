package solvedac.level3;

/*
2021.11.29
문제번호 : Q11724
이름 및 난이도 : 연결요소의 개수 Silver II
문제이해 
-----------------
방향 없는 그래프가 주어질 때 연결요소의 개수를 구하는 프로그램을 작성하시오.
접근 방법 :
그래프 탐색 기본 문제.
제한 조건 : 
*/

import java.io.*;
import java.util.*;
public class Q11724 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i =0;i<N+1;i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        int ans =0;
        boolean[] visited = new boolean[N+1];
        for(int i =1;i<N+1;i++){
            if(!visited[i]){
                Queue<Integer> queue = new LinkedList<Integer>();
                visited[i] = true;
                queue.add(i);
                while(!queue.isEmpty()){
                    int cur = queue.poll();
                    for(int nxt : graph[cur]){
                        if(!visited[nxt]) {
                            visited[nxt] = true;
                            queue.add(nxt);
                        }
                    }
                }
             ans++;
            }
        }
        System.out.println(ans);

    }
}
