package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/1253
좋다 Gold IV
N개의 수 중에서 어떤 수가 다른 두 수의 합으로 나타낼 수 있다면 그 수를 GOOD
N개의 수가 주어질 때 그 중에서 좋은 수의 개수는 몇개인지 출력
수의 위치가 다르면 값이 같아도 다른 수이다.
N < 2_000
각 값은 int형 범위 안에서 가능.
계산과정중에 int형 안넘어가긴 함.
최악의 Naiive 한 경우 O(N^3)

 */
import java.io.*;
import java.util.*;

public class Q1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(data);
        int count = 0;
        for (int mid = 0; mid < data.length; mid++) {
            if (set.contains(data[mid])) {
                // System.out.println(data[mid]);
                count++;
                continue;
            }
            int l = 0;
            int r = data.length - 1;
            while (l < r) {
                int sum = data[l] + data[r];
                if (l == mid)
                    l++;
                else if (r == mid)
                    r--;
                else {
                    if (sum < data[mid])
                        l++;
                    else if (sum > data[mid])
                        r--;
                    else {
                        count++;
                        set.add(data[mid]);
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
