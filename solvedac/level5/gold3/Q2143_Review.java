package solvedac.level5.gold3;
/*
2022.01.20
문제번호 : Q2143_Review
이름 및 난이도 : 두 배열의 합 Gold III
문제이해 
-----------------
한 배열에 대해서 A[i] ~ A[j] (i ~ j)
A
이문제의 Point는 각각의 부분합을 구한다는 것
처음 내가 범했던 오류는 left right로 모든 경우의 수를 구하는 건데
O(A) 에서나올 수 있는 모든 경우의수 와 B에서 나올 수 있는 모든 경우의 수의 조합을 해야 답인데
left +right로 모든 걸 다 하려고 했음. A 에서 1 1이 나오고 B에서 232 가 나오고 답이 3이라면
1 + 2 1+2 1+2 1+2 로 총 4개가 나와야되는데
1 + 2 1 + 2인 두가지 경우밖에 구하지 못함
A에서 앞에서 뒤로보고 B에서 앞에서 뒤로보기 때문
하여 경우의수를 모두 구해놓고 확인한다.
이때 확인하는 방법은
    1. Binary Search (B를 소팅하여 몇개있는지 확인)
    2. Map을 이용한 개수 정해놓기

제한 조건 : 
접근 방법 :
*/

import java.io.*;
import java.util.*;

public class Q2143_Review {
    static int target;
    static int N, M;
    static int[] A;
    static int[] B;
    static Map<Long, Long> Amap;
    static Map<Long, Long> Bmap;

    public static void main(String[] args) throws IOException {
        input();
        // solution1();
        solution2();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void solution1() {
        Amap = new HashMap<Long, Long>();
        Bmap = new HashMap<Long, Long>();
        long val = 0;
        for (int i = 0; i < N; i++) {
            val = A[i];
            Amap.put(val, Amap.getOrDefault(val, 0L) + 1);
            for (int j = i + 1; j < N; j++) {
                val += A[j];
                Amap.put(val, Amap.getOrDefault(val, 0L) + 1);
            }
        }
        for (int i = 0; i < M; i++) {
            val = B[i];
            Bmap.put(val, Bmap.getOrDefault(val, 0L) + 1);
            for (int j = i + 1; j < M; j++) {
                val += B[j];
                Bmap.put(val, Bmap.getOrDefault(val, 0L) + 1);
            }
        }
        long ans = 0;
        for (Map.Entry<Long, Long> e : Amap.entrySet()) {
            if (Bmap.containsKey(target - e.getKey())) {
                ans += (e.getValue() * Bmap.get(target - e.getKey()));
            }
        }
        System.out.println(ans);
    }

    static int totalAsum[];

    public static void solution2() {
        int[] Asum = new int[N + 1];
        int tmp = 0;
        for (int i = 1; i < N + 1; i++) {
            Asum[i] = Asum[i - 1] + A[i - 1];
        }
        totalAsum = new int[(N * (N + 1)) >> 1];
        int idx = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < i; j++) {
                totalAsum[idx++] = Asum[i] - Asum[j];
            }
        }
        Arrays.sort(totalAsum);

        tmp = 0;
        int[] Bsum = new int[M + 1];
        for (int i = 1; i < M + 1; i++) {
            Bsum[i] = Bsum[i - 1] + B[i - 1];
        }

        long ans = 0;
        for (int i = 1; i < M + 1; i++) {
            for (int j = 0; j < i; j++) {
                int t = target - (Bsum[i] - Bsum[j]);
                ans += upperBound(t) - lowerBound(t);
            }
        }
        System.out.println(ans);
    }

    public static long upperBound(int x) {
        int ans = -1;
        int left = 0;
        int right = totalAsum.length;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (totalAsum[mid] <= x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static long lowerBound(int x) {
        int ans = -1;
        int left = 0;
        int right = totalAsum.length;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (totalAsum[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;

    }

}
