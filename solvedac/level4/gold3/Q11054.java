package solvedac.level4.gold3;
/*
2021.12.29
문제번호 : Q11054
이름 및 난이도 : 가장 긴 바이토닉 수열 Gold III
문제이해 
-----------------
 1 2 3 5 3 2 1 과 같은 수열을 바이토닉 수열이라고 한다.
 주어진 수열 A에서 가장 긴 바이토닉 부분수열을 구하여라.

접근 방법 :
 가장 긴 증가하는 부분수열(ASC)와 가장 긴 감소하는 부분수열(DESC)를 구한다.
 각각 asc[x] desc[x] 는 x에서 가질 수 있는 가장 긴 증가 부분수열의 사이즈와 vice versa
  중복되는 원소 X에 대하여 ASC[X] + DESC[X] 가 가장 큰 원소 X에서의 값을 구한다.
   이 때 X가 중복적으로 구해진다. ASC[X] + DESC[X]  - 1 이 답이 된다.

   하여 이 문제에서의 가장 Point는 가장 긴 증가하는 부분수열을 얼마나 빨리 구할 수 있는가이다.
   LIS를 구하는 방법에는 DP와 lowerBound를 활용한 BinarySearch 방법이 있다.
   DP의 경우 O(N^2) lowerBound(O(NlogN)
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] asc = new int[N];
        int[] desc = new int[N];
        Arrays.fill(asc, 1);
        Arrays.fill(desc, 1);
        // make asc, desc arr
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] > data[j]) {
                    asc[i] = Math.max(asc[i], asc[j] + 1);
                }
                if (data[(N - 1) - i] > data[(N - 1) - j]) {
                    desc[(N - 1) - i] = Math.max(desc[(N - 1) - i], desc[(N - 1) - j] + 1);
                }
            }
        }

        // @TEST PRINTING
        // System.out.println("ASC");
        // for (int i = 0; i < N; i++) {
        // System.out.print(asc[i] + " ");
        // }
        // System.out.println("\nDESC");
        // for (int i = 0; i < N; i++) {
        // System.out.print(desc[i] + " ");
        // }
        // System.out.println();

        // find best bitonic seq
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (asc[i] + desc[i] >= max) {
                max = asc[i] + desc[i];
            }
        }
        System.out.println(max - 1);

        solveLisByBinarySearch(data);
    }

    public static void solveLisByBinarySearch(int[] data) {
        int N = data.length;
        int[] tmp = new int[N]; // 각 index마다 가질 수 있는 lis 최대 원소
        int[] lis = new int[N]; // 임의로 사용될 LIS arr
        int ans = 0;
        int k, l = 0;
        for (int i = 0; i < N; i++) {
            int v = data[i];
            if ((k = binarySearch(lis, 0, l - 1, v)) == l)
                l++;
            lis[k] = v;
            tmp[i] = l;
        }

        k = l = 0;
        for (int i = N - 1; i >= 0; i--) {
            int v = data[i];
            if ((k = binarySearch(lis, 0, l - 1, v)) == l)
                l++;
            lis[k] = v;
            ans = Math.max(ans, l + tmp[i]);
        }
        System.out.println(ans - 1);
    }

    // lowerBound
    public static int binarySearch(int[] arr, int l, int r, int v) {
        int m = 0;
        while (l <= r) {
            if (arr[m = (l + r) >> 1] < v)
                l = m + 1;
            else
                r = m - 1;
        }
        return l;
    }
}
