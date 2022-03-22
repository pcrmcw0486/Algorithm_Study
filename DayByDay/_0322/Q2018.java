package DayByDay._0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Q2018
 * @문제이름 : 수들의 합 5
 * @난이도 : Silver V
 * @date : 2022-03-22 오후 5:19
 * @author : pcrmcw0486
 * @문제이해
 * 어떠한 자연수 N은 몇개의 연속된 자연수 합으로 나타낸다
 * 1<=N<=10,000,000 에 대해 N을 몇개의 연속된 자연수 합으로 나타내는
 * 가짓수를 알고싶다.
 * 사용하는 자연수는 N이하여야한다.
 * @알고리즘
 *
 * @접근방법
 * 알고싶은건 가짓수.
 * 어떠한 자연수가 연속된 수로 이루어진다?
 *  x-K x-(K-1)... x ... x+K 로 만들어질 때 Ax = N 이 되어야함.
 *  즉 15 = 1 3 5 15
 *  15 / 456 / 12345/ 78? ( 5-1 5 5+1)  (5+2) (5+3)
 *  5를 만드는 연속된 개수?
 *  10? 1 2 5 10
 *  10 /  2가 5개 있어야함.
*/
public class Q2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt =1;
        if(N>1 && N%2!=0) cnt++;
        for(int i =2;i<=Math.sqrt(N);i++){
            if(N%i==0){
                if(i%2!=0) cnt++;
                if((N/i)%2 !=0) cnt++;
                if(i*i==N && i%2!=0) cnt--;
            }
        }
        System.out.println(cnt);
    }
}
