package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q5557
이름 및 난이도 : 1학년 Gold V
문제이해 
-----------------
마지막 두 숫자 사이에 '=' 
나머지 숫자 사이에 + 또는 - 
등식을 만들고 있다.
ex) 8+3-2-4+8-7-2-4-0+8=8
음수를 배우지 않음, 20넘는수 모름
중간에 나오는 수 0<= <=20
ex) 8+3+2-4-8-7+2+4+0+8 = 8 올바른 수식
8+3+2-4-8-7 음수이기 때문에  못 만듦.
올바른 등식의 수를 구하는 프로그램.
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N-1][21];

        ///dp[0][0] = 1L;
        // 시작 값이 0일 때 j-0 ==0 이고 j+0 ==0 이라서 안됨.

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        dp[0][t] = 1;
        for(int i =1;i<N-1;i++){
            int x=  Integer.parseInt(st.nextToken());
            for(int j=0;j<21;j++){
                if(dp[i-1][j] > 0 ){
                    if(j-x >= 0){
                        dp[i][j-x]+= dp[i-1][j];
                    }
                    if(j+x <=20){
                        dp[i][j+x]+=dp[i-1][j];
                    }
                }
            }
        }

        int target = Integer.parseInt(st.nextToken());
        System.out.println(dp[N - 2][target]);

    }

}
