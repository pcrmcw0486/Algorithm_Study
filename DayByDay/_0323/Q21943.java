package DayByDay._0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q21943
 * @문제이름 : 연산 최대로
 * @난이도 : Gold II
 * @date : 2022-03-23 오후 7:12
 * @author : pcrmcw0486
 * @문제이해
 * N 개의 양의 정수 X
 * N-1개 곱하기 및 덯기 연산자 (괄호는 무수히 많이 사용해도 된다)
 * 이 연산에서는 곱하기 연산자와 더하기 연산자의 우선순위가 동일하다, 즉 앞에서 뒤로 쭉 계산하면된다.
 * 1+1*3 = 2*3 = 6 이 되어야 한다. 4가아님.
 * 숫자 위치는 그대로이네요.
 * N의 범위가 8 각 숫자는 일의 자리수,
 * 연산을 잘 이용하여 값을 최대로 만들어보자.
 * @알고리즘
 * 완탐 dp?
 * @접근방법
 * 왜냐하면 일단 모든 조합을 볼 수 있는가? 7! = 5040가지의 경우의 수로 일단 정수 배치가 가능하다.
 * 연산은 6! = 720 a+b = b+a A*c c*A
 * 그렇다면 dp는? dp는 괄호 치는 용 i~ j까지 어떻게 해서 나오는 값이된다.
 * 즉 start위치와 end위치 곱하기 개수 더하기 개수를 하면 해당 위치는 최대값이 된다.
*/
public class Q21943 {
    static int[] data;
    static int[] group;
    static int P, Q,N;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        data = new int[N];
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        group = new int[Q + 1];
        ans = -1;
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int depth) {
        if(depth == N){
            int ret =1;
            for (int i = 0; i < group.length; i++) {
                ret *= group[i];
            }
            ans = Math.max(ans, ret);
            return;
        }
        for (int i = 0; i < Q + 1; i++) {
            group[i] += data[depth];
            dfs(depth+1);
            group[i] -= data[depth];
        }
    }
}
