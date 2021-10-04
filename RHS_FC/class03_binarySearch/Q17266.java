package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/17266
 어두운 굴다리 Silver V
 
 */
import java.io.*;
import java.util.*;

public class Q17266 {
    static int N;
    static int M;
    static int[] position;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        position = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            position[i] = Integer.parseInt(st.nextToken());

        int l = 1;
        int r = N;
        while (l <= r) {
            int mid = l + r >> 1;
            if (determination(mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(l);
    }

    public static boolean determination(int height) {
        if (position[0] - height > 0)
            return true;
        if (position[position.length - 1] + height < N)
            return true;
        for (int i = 0; i < position.length - 2; i++) {
            if (position[i] + height < position[i + 1] - height)
                return true;
        }
        return false;
    }

    public static void solution2() {
        int leftMax = position[0];
        int rightMax = N - position[position.length - 1];
        int midMax = 0;
        for (int i = 0; i < position.length - 2; i++) {
            midMax = Math.max(midMax, (position[i + 1] - position[i] + 1) / 2);
        }
        int ans = Math.max(leftMax, Math.max(midMax, rightMax));
        System.out.println(ans);
    }
}
