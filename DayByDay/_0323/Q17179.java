package DayByDay._0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q17179
 * @문제이름 : 케이크 자르기
 * @난이도 : Silver I
 * @date : 2022-03-23 오후 4:13
 * @author : pcrmcw0486
 * @문제이해
 * 생일을 맞이한 주성이가 생일파티를 준비한다.
 * 특정 위치에서만 자를 수 있다. 파티에 올 사람의 수만큼 준비하고 싶어서, 가장 작은 조각의 크기를 미리 알아보고싶다.
 * 생일파티에 몇명이 참석하는지 알려주지 않는다.
 * 몇 개의 수를 목록에 적어 각 수만큼 조각을 만들었을 때, 가장 작은 조각의 길이의 최댓값을 구하려고 한다.
 * 정리 케이크 길이 L에서 자를 수 있는 지점이 주어질때, x만큼 자를 수 있는 조각을 잘랐을 때,
 * 나올 수 있는 가장 작은 조각의 최대 길이
 * Si는 오름차순으로 주어진다.
 * @알고리즘
 * 이분탐색, 파라메트릭 서치
 * @접근방법
 * 최적문제 'X만큼 자를 때 나올 수 있는 가장 작은 조각의 최대 길이 L '
 * 결정문제 '어떤 길이 L보다 크게 자를 때 X횟수로 자를 수 있을까?' 가능한 L중 가장 작은 길이.
 * 길이가 L 이니까 logL만큼 확인하는데
 * logL마다 O(N)만큼 봐야하니까 O(NlogL)이라고 일단 간단하게 가능하고, 근데 정확하게 그 지점이여야한다는 조건
 * 해당 연산을 총 O(M)번 해야한다.
 * N = 1000 M= 1000 logL = 6.6
 * O(NMlogL) = 6,600,000 정도 된다.
*/
public class Q17179 {
    static int M,L;
    static int[] pos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        pos = new int[M];
        for (int i = 1; i < M+1; i++) pos[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());
            int ans = solve(K);
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static int solve(int K) {
        int l = 0;
        int r = L;
        int mid, ans = -1;
       while(l<=r){
           mid = (l+r)>>1;
           if(isSatisfied(mid,K)){
               ans= mid;
               l = mid+1;
           }else{
               r = mid -1;
           }
       }
        return ans;
    }

    public static  boolean isSatisfied(int partitionSize, int limitCnt){
        int partition =pos[0];
        int cnt =0;
        for (int i = 1; i < M + 2; i++) {
            partition += (pos[i]-pos[i-1]);
            if (partition >= partitionSize && i != M+1) {
                partition =0;
                cnt++;
            }
        }
       return cnt>=limitCnt;
    }
}
