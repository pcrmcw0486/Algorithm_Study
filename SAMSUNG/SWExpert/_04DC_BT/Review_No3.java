package SAMSUNG.SWExpert._04DC_BT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Review_No3 {
    static int N;
    static int L;
    static int[][] schedule;
    static long[] totalSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        schedule = new int[100001][2];
        totalSum = new long[100001];
        for(int test_case = 1; test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            for (int i = 1; i < N+1; i++) {
                st = new StringTokenizer(br.readLine());
                schedule[i][0]  = Integer.parseInt(st.nextToken());
                schedule[i][1] = Integer.parseInt(st.nextToken());
                totalSum[i] = totalSum[i-1] + schedule[i][1] - schedule[i][0];
            }

            long ans = -1;
            for(int i =1;i<N+1;i++){
                ans = Math.max(ans,findBest(i));
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
    public static long findBest(int idx){
        int left = idx;
        int right = N;
        int target = schedule[idx][0] + L; //마지막 지점.
        int mid;
        int pos = -1;
        while(left<=right){
            mid = (left+right)>>1;
            if(schedule[mid][0] <= target){
                pos = mid;
                left = mid +1;
            }else{
                right = mid -1;
            }
        }

        long maxTime =0;
        if(target < schedule[pos][1]){
            maxTime = totalSum[pos-1] - totalSum[idx-1] + (target-schedule[pos][0]);
        }else{
            maxTime = totalSum[pos] - totalSum[idx-1];
        }
        return maxTime;
    }
}
