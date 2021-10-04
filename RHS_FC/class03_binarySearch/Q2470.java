package RHS_FC.class03_binarySearch;

/*
두 용액 Gold V
두가지 방법으로 접근 가능
Naiive 하게 N^2으로 돈다
였는데 두가지 용액을 선택! 하는 것에서 착안하여
1. Two Pointer로 접근 
2. 하나를 선택하고 나머지 하나를 찾는 방법(이분탐색을 이용 logN) * N

 */
import java.io.*;
import java.util.*;

public class Q2470 {
    static int[] data;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(data);

        solution_TwoPointer();
        solution_Binary();
    }

    private static void solution_TwoPointer() {
        int start = 0;
        int end = data.length - 1;
        int min = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        while (start < end) {
            boolean check = data[end] + data[start] >= 0 ? true : false;
            int candidate = Math.abs(data[end] + data[start]);
            if (candidate < min) {
                min = candidate;
                v1 = data[start];
                v2 = data[end];
            }
            if (check)
                end--;
            else
                start++;
        }
        System.out.println(v1 + " " + v2);
    }

    private static void solution_Binary() {
        int result = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        for (int left = 0; left < N - 1; left++) {
            int candidate = lowerBound(-data[left], left + 1, N - 1); // index
            // 값 비교를 통한 update 두가지값이 나옴.
            // 두가지값 중 가장 작은 것을 골라 설정한다.
            // 작은 값 먼저
            int lessIndex = candidate != left + 1
                    ? (Math.abs(data[left] + data[candidate]) < Math.abs(data[left] + data[candidate - 1])) ? candidate
                            : candidate - 1
                    : left + 1;
            if (result > Math.abs(data[left] + data[lessIndex])) {
                v1 = data[left];
                v2 = data[lessIndex];
                result = Math.abs(data[left] + data[lessIndex]);
            }
        }
        System.out.println(v1 + " " + v2);
    }

    private static int lowerBound(int key, int l, int r) {
        int m;
        while (l < r) {
            if (data[m = l + r >> 1] < key)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}
