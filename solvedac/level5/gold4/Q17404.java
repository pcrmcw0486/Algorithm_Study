package solvedac.level5.gold4;
/*
2022.01.13
문제번호 : Q17404
이름 및 난이도 : RGB거리 2 Gold IV
문제이해 
-----------------
N개의 집이 있고 거리는 선분으로 나타낼 수 있다. (1~N번집)
각각의 집이 RGB로 칠하는 비용이 주어질 때 아래 규칙을 만족하며 칠하는 최소 비용
- 1번 집은 2번과 N번 집의 색과 같지 않다.
- N번 집의 색은 N-1번 과 1번 집의 색과 같지 않다.
- i(2<=i<=N-1)번 집은 i-1, i+1번집과 같지 않다.

제한 조건 : 
접근 방법 :
 원형으로 이루어진 형태에서의 dp이다.
 주어진 조건에 따라 case를 나누어 계산한다.
 1번째 집이 R일 때 1~~~N-1  N번째 집은 G or B (dp[n-1][G] + B or dp[n-1][B] + G)
                                              dp[n-1][R] + Math.min(B,G);
 1번째 집이 G일 때 1~~~N-1 N번째 집은 R,B
   - n번째 집을 R로 칠할 때
    dp[n][R] = dp[n-1][G]+cost[n][R] or dp[n-1][B] + cost[n][R];
    dp[n][G] X
    - n번째 집을 B로 칠할때
    dp[n][B] = dp[n-1][G]+cost[n][B] dp[n-1][R]+cost[n][B]
*/

import java.io.*;
import java.util.*;
public class Q17404 {
    static int N;
    static int cost[][];
    static int[][] dp;
    final static int RED =0;
    final static int BLUE =1;
    final static int GREEN =2;
    final static int INF = 10000000;
    public static void main(String[] args) throws IOException{
        input();
        solution();
    }
    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        StringTokenizer st;
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
        }
    }
    public static void solution(){
        int ans = Integer.MAX_VALUE;
        for(int i =0;i<3;i++){
            dp = new int[N][3];
            Arrays.fill(dp[0],INF);
            dp[0][i] = cost[0][i];  // RED  GREEN BLUE
            //1~N-1번째 집 계산
            for(int j =1;j<N;j++){
                dp[j][RED] = Math.min(dp[j-1][BLUE], dp[j-1][GREEN]) + cost[j][RED];
                dp[j][BLUE] = Math.min(dp[j-1][RED], dp[j-1][GREEN]) + cost[j][BLUE];
                dp[j][GREEN] = Math.min(dp[j-1][RED], dp[j-1][BLUE]) + cost[j][GREEN];
                if(j==N-1)
                    dp[j][i] = INF;
            }
            int min = Math.min(dp[N-1][RED],Math.min(dp[N-1][GREEN],dp[N-1][BLUE]));
            if(min < ans)
                ans = min;
        }
        
        System.out.println(ans);
    }
}
