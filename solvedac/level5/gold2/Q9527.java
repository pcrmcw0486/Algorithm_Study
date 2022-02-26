package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Q9527
 * @문제이름 : 1의 개수 세기
 * @난이도 : Gold II
 * @date : 2022-02-13 오후 5:10
 * @author : pcrmcw0486
 * @문제이해 :
 * A<= x<= B인 모든 x에대하여 이진수로 표현하였을 때 1의 개수를 세보자.
 * @알고리즘
 *
 * @접근방법
 * 이진수로 표현했을 때 1 [xxxxxx] 인 부분에 대하여
 * [11111~00000]의 누적합을 미리 계산해두고 1의 개수를 더해줌.
 * 남은 부분에 대하여 반복 계산.
 *
 * 문제에서 최대 B의 크기가 10^16이고 log2를 취하면 54.xxx
 * 55자리까지의 개수를 미리 구해둔다.

*/
public class Q9527 {
    static long[] prefixSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        prefixSum = new long[55];

        for(int i=1;i<55;i++){
            prefixSum[i] = prefixSum[i-1] * 2 + (long)Math.pow(2, i-1);
        }
        //edge정하는건 아직도 어지럽다.
        //long ans = find(Long.parseLong(data[1]));
        long ans = find(Long.parseLong(data[1])) - find(Long.parseLong(data[0]) - 1);
        System.out.println(ans);
    }

    public static long find(long num){
        int pos = 55;
        long ans =0;
        while(num>0){
            if ((num & (1L << pos)) > 0) {
                ans += prefixSum[pos] + ((num - (1L << (pos)) + 1));
                num -= (1L<<pos);
            }
            pos--;
        }

        return ans;
    }
}
