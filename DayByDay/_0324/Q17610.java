package DayByDay._0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q17610
* @문제이름 : 양팔저울
 * @난이도 : Silver I
 * @date : 2022-03-24 오후 2:49
 * @author : pcrmcw0486
 * @문제이해
 * 무게가 서로 다른 k개의 추와 빈 그릇이 있다.
 * 모든 추의 무게는 정수이고 그릇의 무게는 0이다.
 * 양팔 저울을 한번만 이용하여 원하는 무게의 물을 그릇에 담으려고 한다.
 * @알고리즘
 * 완전탐색
 * @접근방법
 * 양팔저울 한번을 통해 잴 수 있는 무게는
 * 어떤 추들의 합 x에 대해 X와 S-X를 만들어 낼 수 있다.
 * 어떤 추들의 합X는 추 집합 K의 부분 집합들이다.
 * K가 13개가 주어질 때, 1~S중 양팔 저울을 한번만 이용하여 측정이 불가능한 경우의 수를 찾아보자.
 * 불가능한 경우의 수 구하기.
 * 정해진 정답의 범위는 1~S
 * k가 13일 때 나올 수 있는 부분집합 개수는 2^13 = 8192개이다.
 * 근데 과연 다 구하는게 좋을까? 최적화할 수 있는 방법은 없을 까?
 * 절반만 뽑으면 모든 경우의수를 보는 것과 같다. X  S-X이기 때문에.
 * 그리고 같을 수도 있다.S의 최대크기는 2,600,000
 *
 * 조금 더 쉽게 생각하면 왼쪽에 올린다 오른쪽에 올린다 안올린다 3가지 상태...
*/
public class Q17610 {
    static int N, S, cnt, TOT;
    static int[] data;
    static int[] arr;
    static boolean[] pos;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); S=0;
        data = new int[N];
        TOT =1<<N;
        arr = new int[TOT];
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            S += data[i];
        }
        arr[TOT-1] = S;
        pos = new boolean[S+1];
        solve(0,0,0);
        for (int i = 1; i < TOT; i++) {
            for (int j = 0;j < TOT; j++) {
                if((i & j) == 0){
                    set.add(Math.abs(arr[i] - arr[j]));
                }
            }
        }
        set.remove(0);
        System.out.println(S-set.size());
    }

    private static void solve(int depth, int value, int status) {
        if( depth == N){
            arr[status] = value;
            arr[status^(TOT-1)] = S-value;
            return;
        }
        solve(depth+1,value,status);
        solve(depth+1,value+ data[depth],(status | (1<<depth)));
    }

    private static void dfs(int depth, int weight){
        if(depth == N){
            if(weight > 0){
                pos[weight]= true;
            }
            return;
        }
        dfs(depth+1, weight);
        dfs(depth + 1, weight + data[depth]);
        dfs(depth + 1, weight - data[depth]);
    }
}
