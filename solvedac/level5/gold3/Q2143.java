package solvedac.level5.gold3;

/*
2022.01.15 
문제번호 : Q2143     
이름 및 난이도 : 두 배열의 합 Gold III
문제이해 
-----------------
주어진 배열 A, B에 대해 A의 부배열과 B 부배열의 합이 T가 되는 모든 부 배열의 쌍의 개수를 구하시오.
sum = A[a] + B[b] 라고 할 때, 
1. sum > A[a1,a2] + B[b1,b2] 라면 
    A[(a1,a2)]또는 B[b1,b2]값이 커져야함.
    이 때 커지는 값은 최소로 작은 값부터 체킹해나가야함.
    A[a1, a2+1]값과 B[b1,b+1]값을 체크하여 더 작은 값을 늘린다.
    즉, A[a2+1]값과 B[b2+1]값 중 작은 값을 택하여야함.
    Min(A[a2+1],B[b2+1])   
2. target < A[a] + B[b]라면,
 A[a1,a2]또는 B[b1,b2]값이 작아져야함.
 이 떄 변화량을 최소로 하여야함. >> 가장 큰 것 순
 A[a1+1,a2] B[b1+1,b2] 중 큰 값.
 max(A[a1+1,a2], B[b1+1,b2])
 즉 A[a1]값과 B[b1]값중 작은 값이 빠져야함.

 sum == target일 때는 값을 키운다. 또는 작게한후 다시 진행.
제한 조건 : 
  주어지는 T의 범위는 Int형 범위
  1<=N<=1000
접근 방법 :
   >> HashMap사용 why? N*M의 구현?
   https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2143-%EC%9E%90%EB%B0%94-%EB%91%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9-BOJ-2143-JAVA
   참조

   >> 이분탐색.
    https://emoney96.tistory.com/36#recentComments
    참조

    상태공간의 이해.
     >> 우리가 가지고 놀 데이터의 공간은 결국 구간합들의 모임.

     + 중복 연산 의 경계
      1. 방법은 일단 왜안되는지도 모르겟는데 
        구조적으로 연산이 중복된다는 점에서 문제가 되긴됨.
        왜안됨.?
        

        2. 둘다 Hash쓸 때 A.get(key) * B.get(T-key) 가되게 되는데
        이 때 int형 범위를 넘어갈 수 있음.
        like 1000C2 = 499500 인데
        499500 * 499500 이면 죽어야지. 이때 죽는거엿음.
        (항상 범위를 조심하자)
*/

import java.io.*;
import java.util.*;

public class Q2143 {
    static int N, M;
    static int[] A, B;
    static int T;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        solution();
        solveByBinarySearch();
        System.out.println(ans);
    }

    public static void solveByBinarySearch() {
        // Calc A subSum & sorting
        int[] ASum = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            ASum[i] = ASum[i - 1] + A[i - 1];
        }
        int[] subSumArr = new int[(N * (N + 1)) / 2];
        int idx = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < i; j++) {
                subSumArr[idx++] = ASum[i] - ASum[j];
            }
        }
        Arrays.sort(subSumArr);
        // While Calc B subSum
        // find From A
        int[] Bsum = new int[M + 1];
        for (int i = 1; i < N + 1; i++) {
            Bsum[i] = Bsum[i - 1] + B[i - 1];
        }
        for (int i = 1; i < M + 1; i++) {
            for (int j = 0; j < i; j++) {
                int t = Bsum[i] - Bsum[j];
                int left = lower_idx(T - t, subSumArr);
                int right = upper_idx(T - t, subSumArr);
                ans += (right - left);
            }
        }
        System.out.println(ans);
    }

    public static int lower_idx(int target, int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int upper_idx(int target, int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // HashMap
    public static void solution() {
        int left = 0;
        int tmp = 0;
        int[] Asum = new int[N + 1];
        int[] Bsum = new int[M + 1];
        HashMap<Integer, Integer> Amap = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> Bmap = new HashMap<Integer, Integer>();
        Asum[0] = 0;
        for (int i = 1; i < N + 1; i++) {
            Asum[i] = tmp + A[i - 1];
            tmp = Asum[i];
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < i; j++) {
                Amap.put(Asum[i] - Asum[j], Amap.getOrDefault(Asum[i] - Asum[j], 0) + 1);
            }
        }

        tmp = 0;
        Bsum[0] = 0;
        ans = 0;
        for (int j = 1; j < M + 1; j++) {
            Bsum[j] = tmp + B[j - 1];
        }
        for (int i = 1; i < M + 1; i++) {
            for (int j = 0; j < i; j++) {
                int key = T - (Bsum[i] - Bsum[j]);
                if (Amap.containsKey(key)) {
                    ans += Amap.get(key);
                }
            }
        }

    }
}

// //이건 모든 부분합에서 구하는 것이 아님.
// //뭔말이냐하면 sorting되어 있는 것이 아니라서
// // 구하다보면 어떠한 target을 기준으로 내려갔따가
// // 다시 올라갈 수도 있음.(음수도 있고)
// // 1 3 2 -3 2 -1 target 4
// //라고하면
// // (1,3) (1 3 2 -3 2 -1) (3 2 -3 2) 이렇게 나와야되는데
// // 이게 안됨.
// //또한 양수가 있다하더라도 sorting된 값이 아니라 보장하지 못한다.
// // '용액'과 다른 문제였네.
// public static int findX(int target){
// int sum =0 ;,,,
// int left =0 ;
// int cnt =0 ;
// for(int right =0 ;right<M;right++){
// sum += B[right];
// while(sum > target && left < right){
// sum -= B[left++];
// }
// if(sum == target){
// cnt++;
// }
// }
// return cnt;
// }