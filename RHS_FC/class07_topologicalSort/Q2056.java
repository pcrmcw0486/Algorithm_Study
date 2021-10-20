package RHS_FC.class07_topologicalSort;
/*
작업 Gold IV 
시간제한 2초
수행해야할 작업 N개(3 ~ 10_000)
각 작업마다 걸리는 시간 1~100
선행관계가 있어서 어떤 작업을 수행하기 위해 반드시 먼저 완료되어야 할 작업이 있음.
K번 작업에 대해 선행관계에 있는 작업의 번호는 모두 1이상 k-1이하
선행관계가 없는 경우도 존재함.(1번 작업)

모든 작업을 완료하기 위한 최소 시간. 동시 수행 가능.

main처럼 풀수 있는 이유는 K작업 선행관계 번호의 특수 관계 때문임.
 */
import java.io.*;
import java.util.*;
public class Q2056 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N+1];
        for(int i =1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            time[i] = t;
            for(int j=0;j<size;j++){
                int before = Integer.parseInt(st.nextToken());
                if(time[before] + t > time[i]){
                    time[i] = time[before] + t;
                }
            }
            answer = Math.max(answer, time[i]);
        }

        System.out.println(answer);
    }

    public static void anotherSolutonUsingTopSort() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N+1];
        int[] inDegree = new int[N+1];
        int[] doneTime = new int[N+1];
        ArrayList<Integer>[] order = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++)order[i] = new ArrayList<Integer>();
        for(int i =1;i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            for(int j =0;j<size;j++){
                int v = Integer.parseInt(st.nextToken());
                order[v].add(i);
                inDegree[i]++;
            }
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        for(int i=1;i<N+1;i++){
            if(inDegree[i] == 0){
                queue.add(i);
                doneTime[i] = time[i];   
            }
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int nxt : order[cur]){
                inDegree[nxt]--;
                doneTime[nxt] = Math.max(doneTime[nxt], doneTime[cur] + time[nxt]);
                if(inDegree[nxt] == 0) queue.add(nxt);
            }
        } 
        for(int i =1;i<N+1;i++)
            answer = Math.max(answer, doneTime[i]);
        System.out.println(answer);        
    }
    
}
