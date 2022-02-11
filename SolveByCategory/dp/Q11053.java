package SolveByCategory.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SolveByCategory.dp(n) n 사용하여 만들 수 있는 가장 긴 수열.
//뭐랑 비교를 해야하나 하는 거지 이제 문제는.
//내 마음에 걸렸던건 뭐지? 다시 돌아가서 봐야한다는 것
//즉 n^2이 된다는 것. 그럼 그게 시간을 초과하는지 확인하는 방법은?
// 수열 A의 크기가 최대 1000이다.
//worst case는 내림차순의 경우이다.
//최악으로 짜는 경우 n-th을 할때 n-1을 봐야한다는거?
//시그마 (i-1)이니까 i^2이 되겟네요..
//그럼 data수가 1000 이니까 많아봐야 1000000, 백만.
// 1초에 1억번 계산한다고 했을 때 시간이 넘지 않는다.
//그럼 풀면된다. 자 풀어보자.
//똑같이 상태를 생각하면됨
// 자 넣으려고 하는데
// 그전 f(n)이 있다고 봤을 때
// 데이터가 크면 그냥 넣으면 되지만 
// 데이터가 작다?dp는 1보다 크다? 무슨 말이냐
// 그전의 수가 나보다 큰 수의 수열로 되어 있을 수 있다는 말이다.
//  
//  8 9 5 10
// 10 20 1 2 3 4 5 6 7 8 9
public class Q11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (data[j] < data[i] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
        }
        int max = -1;
        for (int i = 0; i < N; i++) {
            max = dp[i] > max ? dp[i] : max;
        }
        System.out.println(max);
    }
}
