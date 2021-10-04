package RHS_FC.class03_binarySearch;

/*
먹을 것인가 먹힐 것인가 Silver III
Silver III
A수 N개
B수 N개
N 최대 20,000
N^2 = 400,000,000 불가합니다.
정렬O(NlogN)
찾기 (개수는 index로 하면 되니까. log N)
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Q7795 {
    static int[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            A = new int[N];
            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++)
                B[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(B);
            int count = 0;
            for (int i = 0; i < N; i++) {
                count += lowerBound(A[i], B, 0, M);
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }

    private static int lowerBound(int key, int[] array, int l, int r) {
        int m;
        while (l < r) {
            if (key > array[m = l + r >> 1])
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}
