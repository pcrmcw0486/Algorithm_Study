package RHS_FC.class09_dp;

/*
파일 합치기 Gold III
문제 이해
소설 여러장(chapter)로 쓴는데 각 장을 각각 다른 파일에 저장한다.
소설의 모든 장을 쓰고 각 장이 쓰여진 파일을 합쳐서 완성본의 한 개의 파일을 만듬
두 개의 파일을 합쳐서 임시파일을 만들고
이 임시파일을 계속 두개씩 합쳐서 여러장들이 연속되도록 파일을 합친다.
두개의 파일을 합칠 때 필요한비용이 두 파일 크기의 합이라고 가정
한 개의 파일을 만드는데 필요한 비용 계싼
 C1 C2 C3 C4 40 30 30 50
 C2 C3 -> X1 60   ( 30 30)
 C1 X1 -> X2 100  (30 30  40)
 X2 C4 -> 끝 150  (30 30 40 50)
 60 + 100 + 150 = 310

 C1 C2 Y1 70 (40 30)
 C3 C4 Y2 80 (30 50)
 Y1 Y2 150 (40 30 30 50)
최소비용 구하기.

짝수개라고 했을 때
12 34  56 78  9 10 (11)
[a][b] [c][d] [e]
 [x1]  [x2] [e] -> 여기서도 x1 x2중 x2를 가장싸게 만들어야할것임.
   [x1] [x2+e]  -> x1+e가 싼가 x2 +e가 싼가. 등. 만약 11이 있다면 x1과 x2+e비교도..
     [총합]
K는 500 크기 10_000 Int형 가능.
고려할 것은 작은 값이 가장 많이 계산되도록 해야함.
소팅 해서 가장 작은 값이 앞으로 오도록?
상태공간을 생각해보자
무조건 두개씩 묶는게 좋은게 아닌게
2 2 2 2 2 98 이면 묶게되면 더 많이 나옴
2 5개로 하는게 더 적음.
또는 like
 1 2 3 4 5 6 7 8 9 60 같은 경우도 그럼.
 dp[i][j] i~j 까지 최소값.
  */
import java.io.*;
import java.util.*;

public class Q11066 {
    static int[][] dp;
    static int[][] back;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            dp = new int[K + 1][K + 1];
            back = new int[K + 1][K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < K + 1; i++) {
                dp[i][i] = Integer.parseInt(st.nextToken());
            }
            find(1, K);
            int ans = sum(1, K);
            sb.append(ans).append('\n');
            for (int[] line : dp) {
                for (int a : line) {
                    sb.append(String.format("%2d ", a));
                }
                sb.append('\n');
            }
            sb.append("=====================\n");
            for (int[] line : back) {
                for (int a : line) {
                    sb.append(String.format("%2d ", a));
                }
                sb.append('\n');
            }
            sb.append('\n');
        } // iteration for Test
        System.out.print(sb);
    }

    public static void find(int start, int end) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < end - start; i++) {
            if (dp[start][start + i] == 0)
                find(start, start + i);
            if (dp[start + i + 1][end] == 0)
                find(start + i + 1, end);
            if (ans >= dp[start][start + i] + dp[start + i + 1][end]) {
                ans = dp[start][start + i] + dp[start + i + 1][end];
                back[start][end] = start + i;
                if (dp[start][back[start][end]] > dp[start][start + i])
                    back[start][end] = start + i;
            }
        }
        dp[start][end] = ans;
    }

    public static int sum(int start, int end) {
        int sum = 0;
        if (start == end)
            return 0;
        sum += sum(start, back[start][end]) + sum(back[start][end] + 1, end);
        return sum += dp[start][end];
    }
}
