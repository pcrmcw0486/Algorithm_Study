package solvedac.level5.gold4;
/*
2022.01.11
문제번호 : Q1806
이름 및 난이도 : 부분합 Gold IV
문제이해 
-----------------
부분합 문제이다. 
10_000 이하의 자연수로 이루어진 길이 N 수열.
연속된 수들 의 부분합 중 그 합이 S이상이 되는 것 중 가장 짧은 길이를 구하시오.


접근 방법 :
 - 부분합




  이 문제의 경우 다양한 case 에서 특정 case를 구해야하기 때문에 결국 다 보아야하므로
  TwoPointer를 사용하는 것이 편해 보인다.

   left : 시작지점
   right : 값을 갱신하면서 증가.
     >> data 값을 받으면서 할 수도 있고
     >> 다 받은 후에 할 수도 있음.
       (코딩테스트를 준비한다면 다 받은 후 하는 것이 옳고)
       (시간을 생각한다면 data를 받으면서 진행하는 것이 좋음)
  - 길이 구하기 [a,b)  b-a == length

제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        // type I
        // right가 하나씩 증가하고 left를 당기는 방식.
        // 이 경우는 [a,b] 라서 a-b+1 해야 길이임.
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int right = 0; right < N; right++) {
            data[right] = Integer.parseInt(st.nextToken());
            sum += data[right];
            if (sum >= S) {
                while (true) {
                    if (sum - data[left] < S) {
                        break;
                    }
                    sum -= data[left];
                    left++;
                }
                ans = Math.min(ans, right - left + 1);
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);

        // type II
        // 다받은 후 right증가시키면서 left 줄이기.
        left = 0;
        int right = 0;
        sum = data[left];
        int min = Integer.MIN_VALUE;
        while (true) {
            if (sum < S) {
                right++;
                if (right >= N)
                    break;
                sum += data[right];
            } else {
                min = Math.min(ans, right - left + 1);
                sum -= data[left];
                left++;
            }
        }
        System.out.println(min == Integer.MIN_VALUE ? "0" : min);
    }
}
