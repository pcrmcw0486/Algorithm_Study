package RHS_FC.class06_tree;
/*
회사문화 1
부하의 부하 내리 칭찬 
수치 있음.
 */
import java.io.*;
import java.util.*;

public class Q14267{
    static ArrayList<Integer>[] org;
    static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        org = new ArrayList[n+1];
        for(int i=0; i<org.length; i++)org[i] = new ArrayList<Integer>();

        answer = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1;i<n+1;i++){
            int p = Integer.parseInt(st.nextToken());
            if(p== -1) continue;
            org[p].add(i);
        }
        for(int i =0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int v =Integer.parseInt(st.nextToken());
            answer[p] = v;
        }
        good(1,0);
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<n+1;i++){
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void good(int cur, int value){
        answer[cur] += value;

        for(int nxt : org[cur]){
            good(nxt, answer[cur]);
        }
    }
}