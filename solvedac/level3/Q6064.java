package solvedac.level3;
/*
2021.12.02
문제번호 : Q6064
이름 및 난이도 : 카잉 달력 Silver I
문제이해 
-----------------
카잉제국 달력
 M과 N보다 작거나 같은 x,y 로 각 년도를 <x:y>로 표현.
 첫번째 해 <1:1> 두번째해 <2:2>
 <x:y>의 다음 년도를 <x':y'>라고 할 때
 x<M이면 x' = x+1 그렇지 않다면 x' = 1
 y<N이면 y' = y+1 그렇지 않다면 y' = 1
 <M:N>은 그들의 마지막 년도로 이 년도에 세상 종말.
 1:1 2:2 3:3 >>> 10:10 1:11 2:12 3:1
 공약수네.
접근 방법 :
각 수는 정해진 limit의 mod값.
M*a + x == N*b+y가 된다면 맞는 값임.
한쪽을 고정시켜서 계산하고 나누어서 값이 맞는지 확인하면 되는데,
범위는 최소 공배수까지이다.
제한 조건 : 
1<= M ,N <= 40,000 
*/

import java.io.*;
import java.util.*;

public class Q6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(findDay(M, N, x, y)).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static int findDay(int M, int N, int x, int y) {
        int lcm = findLCM(M, N);
        int tmp = 1;
        int idx = 0;
        while (true) {
            tmp = M * idx + x;
            if (tmp > lcm)
                break;
            // 0 check 해줬어야지..
            int option = tmp % N;
            if (option == 0)
                option = N;
            if (N == y) {
                return tmp;
            }
            idx++;
        }
        return -1;
    }

    /*
     * 최대공약수 : 유클리드 호제법을 사용하여 구한다.
     * a, b 최대공약수를 구하고자 하는 두수
     * r : a를 b로 나눈 나머지 (a%b)
     * gcd(a,b) = gcd(b,r)
     */
    public static int findLCM(int a, int b) {
        int x = a;
        int y = b;
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return (a * b) / x;
    }
}
