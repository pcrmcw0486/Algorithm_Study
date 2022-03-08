package solvedac.level5.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_1806
 * @문제이름 : 부분합
 * @난이도 : Gold IV
 * @date : 2022-03-01 오전 4:54
 * @author : pcrmcw0486
 * @문제이해
 * 10,000 이하 자연수로 이루어진 길이 N짜리 수열이 있다.(자연수)
 * 연속된 수들의 부분합 중 그 합이 S이상이 되는 것 중 가장 짧은 것의 길이를 구하는 프로그램.
 * 10<=N<=100,000 O(N)이면 충분히 가능하다.
 * 0<S<=100,000,000  >> int 형으로 가능하다.
 * @알고리즘
 * twoPointer
 * @접근방법
 * '연속된 부분합' + '가장짧은' 에 초점을 맞춘다.
 * left 이전 시작 지점 right현재 내가 보는 지점.
 * [left ~right]까지의 합을 O(N)으로 구할 수 있고, 만약 S보다 커진다면 사이 길이를 계산하고,
 * S보다 작아지도록 낮춰준다.
 * 이러한 방식이 가능한 이유는 자연수 이기 때문이다.
 * 만약 음수가 섞여있다면, 보장하지 못한다. 양수이기때문에 S는 항상 양수이고 빼주었을 때 낮아지기 때문이다.
*/
public class Review_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int ans =Integer.MAX_VALUE;
        int[] data = new int[N];
        for (int i = 0; i < N; i++) data[i] = Integer.parseInt(st.nextToken());
        int left =0, right=0, sum=0;
        while(true){
            if(sum<S){
                if(right == N) break;
                sum += data[right++];
            }else{
                ans = Math.min(ans, right - left);
                sum -= data[left++];
            }
        }
        System.out.println(ans==Integer.MAX_VALUE?0:ans);
    }
}
