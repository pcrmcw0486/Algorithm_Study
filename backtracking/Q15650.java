package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q15650 {

    public static int N;
    public static int M;
    public static int[] numbers;
    public static boolean[] check;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        check = new boolean[N + 1];
        for(int i = 1 ;i<N+1-(M-1);i++){
            numbers[0] = i;
            solution(i,0);
        }
        System.out.print(sb.toString());
    }

    public static void solution(int start, int count) {
       if(count == M-1){
           for(int n : numbers){
               sb.append(n).append(" ");
           }
           sb.append("\n");
           return;
       }
       if(N-start < M-count-1){
        return;
       }

        for(int i = start+1; i<N+1;i++){
            numbers[count+1] = i;
            solution(i,count+1);
        }
    }
}
