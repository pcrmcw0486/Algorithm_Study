package RHS_FC.class03_binarySearch;

/*
수 찾기 Silver IV
N개의 정수가 주어질 때 이 안에 X라는 정수가 존재하는지
찾아본다.
이분탐색 가장 기본 문제
N개 100,000
M개 
모든 정수의 범위는 Int형으로 가능하다. 존재하면 1을 존재하지 않으면 0을 출력한다.
 */
import java.io.*;
import java.util.*;

public class Q1920 {
    static int N;
    static int[] list;
    static int M;
    static int[] data;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new int[N];
        for (int i = 0; i < N; i++)
            list[i] = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        data = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            data[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(list);
    }

    public static void solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int key = data[i];
            sb.append(find(key) ? 1 : 0).append("\n");
        }
        System.out.print(sb);
    }

    public static boolean find(int key) {
        int l = 0;
        int r = list.length;
        int m;
        while (l < r) {
            if (list[m = l + r >> 1] == key) {
                return true;
            }
            if (list[m] < key) {
                l = m + 1;
            } else
                r = m;
        }
        return false;
    }
}
