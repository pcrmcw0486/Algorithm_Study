package SolveByCategory.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10266 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean c1[] = new boolean[720000];
        boolean c2[] = new boolean[360000];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            c1[idx] = true;
            c1[idx + 360000] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken());
            c2[idx] = true;
        }

        System.out.println(solution(c1, c2));
    }

    public static String solution(boolean[] S, boolean[] P) {
        int[] failure = getPi(P);
        for (int i = 1, j = 0; i < S.length; i++) {
            while (j > 0 && S[i] != P[j])
                j = failure[j - 1];
            if (S[i] == P[j]) {
                if (j == P.length - 1)
                    return "possible";
                else
                    ++j;
            }
        }
        return "impossible";
    }

    public static int[] getPi(boolean[] Pattern) {
        int[] failure = new int[Pattern.length];
        for (int i = 1, j = 0; i < failure.length; i++) {
            while (j > 0 && (Pattern[i] != Pattern[j]))
                j = failure[j - 1];
            if (Pattern[i] == Pattern[j])
                failure[i] = ++j;
        }
        return failure;
    }
}
