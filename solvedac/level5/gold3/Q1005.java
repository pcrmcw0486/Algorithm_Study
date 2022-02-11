package solvedac.level5.gold3;

/*
2022.01.15
문제번호 : Q1005
이름 및 난이도 : ACM Craft Gold III
문제이해 
-----------------
매 게임 시작 시 건물 짓는 순서가 주어지고, 모든 건물은 각각 건설을 시작하여 delay가 존재.
특정 건물을 가장 빨리 짓는 최소시간.
건물이 지어지기 위해 '선행' 되어야 하는 건물들이 존재한다.

제한 조건 : 
접근 방법 :
 2<=N <= 1000 (건물 개수)
 1<=K <= 100_000 (규칙 순서)
 0<=D <= 100_000 (걸리는 시간)
*/

import java.io.*;
import java.util.*;
public class Q1005 {
    static StringBuilder sb;
    static int N, K;
    static int time[];
    static ArrayList<Integer>[] order;
    static BufferedReader br;
    static int needCnt[];
    static int minTime[];
    static int target;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- >0){
            input();
            solution();
        }
        System.out.print(sb.toString());
    }

    public static void input()throws IOException{
        //BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        needCnt = new int[N];
        order = new ArrayList[N];
        minTime= new int[N];
        for(int i =0;i<N;i++){
            order[i] = new ArrayList<Integer>();
        }

        //건물 당 걸리는 시간
        time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 규칙
        for(int i =0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            needCnt[v]++;    //  cnt to release V
            order[u].add(v);  // u -> v
        }

        target = Integer.parseInt(br.readLine())-1;
    }

    public static void solution(){
        Queue<Integer> progress = new LinkedList<Integer>();
        //선행 건물이 필요없는 건물들은 바로 시작
        for(int i =0;i<N;i++){
            if(needCnt[i] == 0){
                progress.offer(i);
           //     minTime[i] = time[i];
            }
        }

        while(!progress.isEmpty()){
            int cur = progress.poll();
            minTime[cur] += time[cur];
            if(cur == target){
                break;
            }
            for(int nxt : order[cur]){
                needCnt[nxt]--;
                minTime[nxt] = Math.max(minTime[cur], minTime[nxt]);
                if(needCnt[nxt] == 0){
                    progress.add(nxt);
                }
            }
        }
        sb.append(minTime[target]).append('\n');
    }
}
