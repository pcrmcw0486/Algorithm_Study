package solvedac.level5.gold3;
/*
2022.01.16
문제번호 : Q2252
이름 및 난이도 : 줄 세우기 Gold III
문제이해 
-----------------
N명의 학생들을 키 순서대로 줄을 세운다. 
두 학생의 키를 비교하여 줄을 세우자.
A,B >>  A < B
 '상대'

제한 조건 : 
  N <= 32000 
  1<=M<=100_000
접근 방법 :
 위상정렬 기본 문제.
 '상대적', '비교하여 주어진' + 정렬 
  A->B
  C->B라면?
  (A,C) -> B A와 C는 모르겟지만 B는 확실히 크다. 와같은식.
*/

import java.io.*;
import java.util.*;
public class Q2252 {
    static int N, M;
    static int[] inDegree;
    static ArrayList<Integer>[] order;
    public static void main(String[] args)throws IOException{
        input();
        solution();
    }
    public static void input()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        order = new ArrayList[N+1];
        for(int i =0;i<N+1;i++){
            order[i] = new ArrayList<Integer>();
        }
        inDegree = new int[N+1];
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            order[u].add(v);
            inDegree[v]++;
        }
    }
    public static void solution() throws IOException{
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i =1;i<N+1;i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        StringBuilder sb= new StringBuilder();
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur).append(' ');
            for(int nxt : order[cur]){
                inDegree[nxt]--;
                if(inDegree[nxt] == 0){
                    queue.add(nxt);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
