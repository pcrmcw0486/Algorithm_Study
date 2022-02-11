package solvedac.level5.gold3;
/*
2022.01.15
문제번호 : Q1644
이름 및 난이도 :  소수의 연속 합.
문제이해 
-----------------
하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다.
소수는 반드시 한번만 덧셈에 사용될 수 있다.
해당 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하시오.
제한 조건 : 
  1<= N <= 4000000
접근 방법 :
 어떤 x가 연속된 소수의 합으로 나타낼 수 있을 때 x를 이루는 마지막 소수 a와 다음 소수 b에 대해
 dp[x+b]++; 라고 한다면,
 4_000_000안의 소수에 대해 모든 case를 다 구하는 셈.
 안에 소수 N개가 있다면 N + N-1 + ~~~ 1 N(N+1)/2 가지. 
  이 방법을 사용하게 된다면
   1. 주어진 N 이하의 소수를 모두 구함. O(N * (루트 N ))
      - 소수판별(에라토스테네스의 체) 각 수마다 루트 N
   2. 모든 case들을 구함.
   3. return N

   다른 방법을 생각해보자. (다른 곳에 포커스)
   2 3 5라고 할 때
   2 2+3 2+3+5
   3 3+5
   5
   >> A가 주어질 때 연속된 수의 합 중 X인 값을 구하시오.
   와 같은 문제임. 이걸로 만들어 내야함. 문제를.

   교훈) 
    - 소수 구하기 feat 소수 판별 에라토스테네스의 체
    - 문제 추상화. 본질 꿰뚫기

*/

import java.io.*;
import java.util.*;
public class Q1644 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int tmp = 2;
        int idx = 0;
        int[] primes= new int[N+1];
        boolean[] isNotPrime = new boolean[N+1];
        //소수를 구하는 여러 가지 방법.
        // 소수라면 범위안에 소수의 배수를 모두 없앤다.(체로 )
        // 소수 판별법.(에라토스테네스의 체.)
        // while(tmp <= N){
        //     if(isPrime(tmp)){
        //         primNumberArr[idx++] = tmp;
        //     }
        //     tmp++;
        // }
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for(int i = 2;i<N+1;i++){
            if(!isNotPrime[i]){
                primes[idx++] = i;
                for(int j = i*2;j<N+1;j+=i){
                    isNotPrime[j] = true;
                }
            }
        }
        //twoPointer 연속된 수의 합 중 X 찾기
        int left = 0;
        int sum = 0;
        int cnt = 0;
        for(int right =0;right<idx;right++){
            sum += primes[right];
            while(sum > N){
                sum -= primes[left++];
            }
            if(sum == N){
                cnt++;
            }
        }
        System.out.println(cnt);

    }

    // public static boolean isPrime(int x){
    //     for(int i =2;i<=Math.sqrt(x);i++){
    //         if(x%i == 0) return false;
    //     }
    //     return true;
    // }
}
