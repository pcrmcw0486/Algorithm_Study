package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q1208
 * @문제이름 : 부분 수열의 합 2
 * @난이도 : Gold I
 * @date : 2022-02-27 오후 1:44
 * @author : pcrmcw0486
 * @문제이해
 * N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분 수열 중, 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램
 * 1<=N<=40, |s| <= 1,000,000 (음수도 있네?)
 * 주어지는 정수의 절대값은 100_000을 넘지 않는다.
 * @알고리즘
 *
 * @접근방법
 * 연속된 수열이 아닙니다. '부분 수열'입니다.
 * 다루기 쉽도록 변환이 필요하죠. 생각하기 쉽게. 1 2 3 4 라고 했을 때 근데. 5라고 생각해볼까
 * 그때 이런 비슷한 문제에 대해 투포인터로 접근했다가. 개 털린 기억이 있는데.그 때는 어떻게 풀었지?
 * 모든 부분합을 구했었던거 같다. 모든 부분합을 구해서 탐색했던거 같은데. 근데 그때는 연속한 부분 수열이였지.
 * N이 40임 그러면 2^40개의 부분 수열이 나옴.
 * 수열에서 방향성은 항상 증가하는 쪽으로 해야. 뭔가 수를 넘었을 때 더이상 보지 않아도 된다고 보장할 수 있음.
 * 2^40 =10^12 brute force? (BAD)
 *
*/
public class Q1208 {
    static Map<Integer, Long> groupA;
    static Map<Integer, Long> groupB;
    static int[] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        groupA = new HashMap<>();
        groupB = new HashMap<>();
        data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        findPartial(0,N/2,0,true);
        findPartial(N/2,data.length,0,false);
        long ans = 0L;
        //합쳐서 만들 수 있는것.
        for (Map.Entry<Integer, Long> en : groupA.entrySet()) {
            if (groupB.containsKey(S-en.getKey())) {
                ans += groupB.get(S - en.getKey()) * en.getValue();
            }
        }
        //안에서 탐색
        ans += groupA.getOrDefault(S, 0L);
        ans += groupB.getOrDefault(S, 0L);
        System.out.println(ans);
    }
    public static void findPartial(int start, int end, int val, boolean isGroupA) {
        if(start >= end) return;
        Map<Integer, Long> hm = isGroupA ? groupA : groupB;
        hm.put(val + data[start], hm.getOrDefault(val + data[start], 0L) + 1);
        findPartial(start + 1, end, val, isGroupA);
        findPartial(start + 1, end, val+data[start], isGroupA);
    }
}
