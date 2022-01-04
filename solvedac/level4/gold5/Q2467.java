package solvedac.level4.gold5;
/*
2022.01.04
문제번호 : Q2467
이름 및 난이도 : 용액 Gold V
문제이해 
-----------------
1부터 1_000_000_000 까지 산성
부호 반대 알칼리.
특성값의 합이 0에 가장 가까운 혼합 용액.

접근 방법 :
 - 탐색 종류의 문제임.
 1. Two Pointer
  왼쪽(알칼리 - ) 오른쪽(산성 +) 포인터를 두고.
  두 합이 0 에 가장 가까운 값을 찾아 나간다.
  sum[left] + sum[right] > 0  >> 큰 값을 줄인다. (right--)
  sum[left] + sum[right] <0   >> 작은 값을 줄인다. (left++)
  while(left<right)
  Math.abs 값이 가장 작은 값을 저장하는 방법.
  O(N)
 2. Binary search.(이분탐색)
    O((N/2)logN)
    정렬되어 들어오는 값
    어떠한 값 x에 대하여 -x와 가장 가까운 값을 찾는다.
    -x값보다 작은 값을 찾는 경우 해당 idx +1과도 비교해야한다.
    [x ... X_N]
 출력은 두 용액을 
제한 조건 :
 2<= N <= 100_000
 오름차순 입력
 N개의 용액 특성값은 모두 다르다. 
*/

import java.io.*;
import java.util.*;

public class Q2467 {
    static int N;
    static int[] data;
    static int ansMin, ansMax;

    public static void main(String[] args) throws IOException {
        input();
        solve_TwoPointer();
        // solve_BinarySearch();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());
    }

    public static void solve_TwoPointer() {
        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int sum = data[left] + data[right];
            if (Math.abs(sum) < min) {
                ansMin = data[left];
                ansMax = data[right];
                min = Math.abs(sum);
            }
            if (sum < 0)
                left++;
            else
                right--;
        }
        System.out.println(ansMin + " " + ansMax);
    }
}
