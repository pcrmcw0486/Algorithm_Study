package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/3273
두수의 합 Silver III
두 용액 문제와 비슷함.
n 개의 서로 다른 양의 정수 각 값은 1~1_000_000 (int 형으로 가능함)
자연수 x가 주어질 때, ai + aj를 만족하는 ai, aj 쌍의 수를 구하라.
i!= j 
 
두 용액과 마찬가지로 두 가지 방법으로 구할 수 있다.
1. Two Pointer
정렬된 배열에서 start < end 인동안 
data[start] + data[end] > X 이면 값이 크므로 큰 값을 줄이고, 
data[start] + data[end] < X이면 값이 작으므로 작은 값을 늘리면 됨.
O(N)
2. Binary Search
어떠한 값 i에 대하여 정렬된 배열에서 X-i를 구하면됨.
근데 다 구하면 안됨.X-i > i인 동안만 구하면 됨.
i와 j는 다르므로 i +j = X일 때 i != j 이므로 j>i라고 할 때 X-i > i
O(NlogN * NlogN)
 */
import java.io.*;
import java.util.*;

public class Q3273 {
    static int N;
    static int[] data;
    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(br.readLine());
        Arrays.sort(data);

        solution_TwoPointer();
        solution_Binary();
    }

    public static void solution_TwoPointer() {
        int start = 0, end = N - 1;
        int count = 0;
        while (start < end) {
            int sum = data[start] + data[end];
            if (sum == X)
                count++;
            if (sum < X)
                start++;
            else
                end--;
        }
        System.out.println(count);
    }

    public static void solution_Binary() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (X - data[i] <= data[i])
                break;

            if (find(X - data[i]))
                count++;

        }
        System.out.println(count);
    }

    public static boolean find(int target) {
        int l = 0, r = data.length;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (data[m] == target)
                return true;
            if (data[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return false;
    }
}
