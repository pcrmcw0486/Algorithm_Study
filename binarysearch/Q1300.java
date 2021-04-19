/*
https://www.acmicpc.net/problem/1300
*** K 번째 수 ***
*** 접근 방법 ***
- 어떠한 수 X를 골랐을 때 그 X보다 작은 수가 K개 있다면
 X는 K에 위치할 수 있다.(가능성)
- 작은 수를 구하는 방법?(막혔던 부분)
  나누기!
- 작은 수를 구했을 때 나오는 값은 어떠한 수 X를 포함하는 작거나같은
숫자의 개수(즉 자신을 포함하고 있음)
- K-th 수는 자신보다 작은 수를 k개 이상 가지는 수 중 가장 작은수
 > 결정문제 : 자신보다 작은 수를 K개 가지는 수 중 가장 작은 수를 고르시오.
- 참조) 7*7 배열에서 12보다 작은 수는 25개, 13보다 작은 수도 25개이므로
그러한 수를 가지는 수 중 가장 처음 나오는 수(lower_bound)를 구한다.
*** 사용 알고리즘 , 자료구조, 스킬 ***
- 이분탐색, lower_bound, parametric search(조건)
- 배열은 사용하지 않아도 가능하다.
*** 문제 조건 ***
N*N 배열 A , A[i][j] = i *j => 1차원 배열 B 오름차순으로 정럴
B[k]는? 
 - A와 B의 인덱스는 1부터 시작한다.
 - 크기 1<=N<=10^5
 - 위치 k = min(10^9, N^2)
 N^2 10^10이네 long까지 가야하는 구나.
 그래서 틀렸네.
 */
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        long left = 1;
        long right = K; // int 범위를 넘지 않음.
        long ans = Long.MAX_VALUE;
        while (left <= right) {
            // System.out.println(left + " " + right);
            long mid = (left + right) / 2;
            if (condition(mid, N, K)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean condition(long ans, int N, int K) {
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            count += Math.min(ans / i, N);
        }
        return count >= K;
    }

}
