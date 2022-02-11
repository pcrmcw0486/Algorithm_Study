package solvedac.level5.gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q11049
 * @문제이름 : 행렬곱셈순서
 * @난이도 : Gold III
 * @date : 2022-02-11 오후 12:01
 * @author : pcrmcw0486
 * @문제이해
 * 크기가 N*M인 A와 M*K를 곱할 때 곱셉 연산의 수는 N*M*K번.
 * ABC AB*C와 A*BC 에 따라 행렬 곱 횟수가 달라진다.
 * N개의 행렬 크기가 주어질 때 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수 최소값.
 * 행렬의 순서를 바꾸면 안된다.
 * N <= 500  (r,c) <= 500
 * 정답은 int형 안에 가능하다.
 * @알고리즘
 * SolveByCategory.dp
 * @접근방법
 * (0,N)에 대해 (0,x)(x+1,N)을 곱하게 되면 (0,N)이 만들어진다.
 * 이 때, (0,N)의 최소값을 찾는 문제이다.
 * TSP랑 비슷한 구조같은데
 * SolveByCategory.dp[i][j]는 (i~j)까지 곱한 횟수 (i==j라면 0)
  * SolveByCategory.dp[i][j] = SolveByCategory.dp[i][i] + SolveByCategory.dp[i+1][j] + num[i][0]*num[i+1][0]*num[i+1][1];
 *              (i=0~j)
*/
public class Q11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] size = new int[N][2];
        int[][] dp = new int[N][N];
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            size[i][0] = Integer.parseInt(st.nextToken());
            size[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int length=1;length<N;length++){
            //자신 기준 오른쪽으로 한칸 앞과 곱할대
            for(int start =0;start<N-length;start++){
                dp[start][start+length] = Integer.MAX_VALUE;
                //시작점
                for(int end =start;end<(start+length);end++){
                    //여러 끝점들.
                   /* System.out.println("SolveByCategory.dp["+i+"]["+(i+length)+"] = " +"["+ i +"][" + j + "] + ["+(j+1)+"]["+(i+length)+"]");
                    System.out.println(SolveByCategory.dp[i][j] + SolveByCategory.dp[j+1][i + length] + (size[i][0] * size[j][1] * size[i+length][1]));*/
                    dp[start][start+length] = Math.min(dp[start][start+length], dp[start][end] + dp[end+1][start + length] + (size[start][0] * size[end][1] * size[start+length][1]));
                }
            }
        }
        System.out.println( dp[0][N-1]);
    }
}
