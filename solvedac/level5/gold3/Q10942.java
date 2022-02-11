package solvedac.level5.gold3;
/*
2022.01.20
문제번호 : Q10942
이름 및 난이도 : 팰린드롬? Gold III
문제이해 
-----------------
팰린드롬 놀이를 해보자. 
자연수 N개를 칠판에 적고 M번의 질문을 한다.
각 질문은 S와 E로 나타나며 S수부터 E까지 팰린드롬인지 물어본다.

제한 조건 : 
수열의 크기 N ( 1<= N <= 2000) 
질문의 개수 (1<= M <= 1000000)
 만약 크기 2000개인 질문을 최대 M크기로 물어볼 때 Naiive하게 하면
 20억임...
접근 방법 :

 1. 팰린드롬? 팰린드롬이란 앞과 뒤에서 읽었을 때 똑같은 문자열을 의미한다.
 2. query문제에서는 어떠한 값들을 지정해두고 query가 들어왔을 때 최대한 빠르게 답할 수 있도록 미리 준비해두는 것이 좋음.
    어떻게 할거냐.
    팰린드롬의 특성을 생각해보자.
    [s  ...   e ] 가 있을 때 s==e 일 때 s+1부터 e-1까지 팰린드롬이라면 팰린드롬이다.
    또한 이러한 반복적인 질문들에 대해 memo를 해둔다면 빠르게 처리가능하다고 생각

*/

import java.io.*;
import java.util.*;
public class Q10942 {
    static int[] numbers;
    static int N;
    static boolean dp[][];
    static BufferedReader br;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb= new StringBuilder();
        N = Integer.parseInt(br.readLine());
        numbers =Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new boolean[numbers.length][numbers.length];
        for(int i =0;i<numbers.length;i++)dp[i][i] = true;
        int M = Integer.parseInt(br.readLine());
        for(int q =0;q<M;q++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            sb.append(find(s,e)?1:0).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static boolean find(int start, int end){
        //마지막 종료조건
        if(start >= end) return true;
        if(numbers[start] != numbers[end]) return false;
        if(dp[start][end]== true) return true;
        if(numbers[start] == numbers[end]){
            dp[start][end] = find(start+1,end-1);
        }
        return dp[start][end];
    }
    
    //bottom -up 
    //차피 query 는 s<e
    public static void solve(){
        //하나일 때 채워줌
        for(int i =0;i<N;i++) dp[i][i] = true;
        //두개 체크해서 채워줌
        for(int i =0;i<N-1;i++){
            if(numbers[i] == numbers[i+1]) dp[i][i+1]= true;
        }
        //지금부터 자리를 늘려가며 확인하여 dp배열을 채움
        for(int length = 2 ; length < N;length++){
            for(int i =0;i<N-length;i++){
                // 양끝이 같고, s+1, e-1이 true일 때 (2->3 ->4 순으로 채워나감))
                if(numbers[i] == numbers[i+length] && dp[i+1][i+length-1])
                    dp[i][i+length] = true;
            }
        } 

    }
}
