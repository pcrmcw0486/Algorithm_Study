package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/1637
날카로운 눈 Platinum V
정수더미가 있다. 그 안에 특정한 정수 하나만 홀수개 존재한다.
나머지 정수는 짝수개 존재한다. 홀수개 존재하는 정수를 찾아야하는 문제
A C B a + kB <C
즉, 1 10 1이면
1, 1+1, 1 + 2*1 .. .1+9*1 = 10;
인식이다.
4 4 1이면
4  4 +1*1>4 (X)
6 10 1이면
6 6+1*1 6+1*2 6+1*3 6+1*4 = 10 까지
홀수개 존재하는 정수를 출력하고, 정수가 몇개 들어있는지 출력한다.
N은 1~20_000 
A,B,C는 int 범위안.
최악의 경우
20_000 (2 * 10^4)
1 2147483647 1 10^3 == 2^10 10^9
1 2147483647 1
1 2147483647 1
다구하면 2*10^4 * 10^9 다 구하는 건 말이 안된다.
그렇다면?
O(N) 으로 확인은 가능.
1~ 2147483647 [L...R]
근데 1~2147483647에 따라 개수가 순차적으로 증가하진 않음.
12345678910
4
12345
6 7 8 9 10
2 2 2 3 2 2 2 2 2 2 라서 순차적이진 않음.
L은 A중 가장 작은 것일꺼고 R은 C중 가장 큰거인데
NlogN
변화에 조금 더 집중했어야함. 합에 정신팔려가지고 그러면 시간이 너무 짧게 구해짐.
합은 맞는데 수열이라는거에 너무 집중했다리.
특정 숫자에서 변화가 있다면
T|F 로 만들 수있다는 것인데
이걸 개수로 생각하면 어떤 숫자든 O(N)으로 모든 수열에서 검색 했을 때
개수를 구할 수 있고 어느 시점부터 개수의 총 합이 홀수로 변화하는 시점이 있어서
T|F로 변화할 수 있다는 거.......................................
괜히 플레문제가 아니엿음. 시발 아니 플레라기보다는 아직 덜 분석햇다??
 */
import java.io.*;
import java.util.*;

public class Q1637 {
    static int N;
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        info = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        long l = 1;
        long r = Integer.MAX_VALUE;
        long ans = 0;
        long ansCnt = 0;
        while (l <= r) {
            long mid = (l + r) >> 1;
            if (determination((int) mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (ans == 0)
            System.out.println("NOTHING");
        else {
            for (int i = 0; i < N; i++) {
                if (info[i][0] <= ans && ans <= info[i][1] && (ans - info[i][0]) % info[i][2] == 0)
                    ansCnt++;
            }
            System.out.println(ans + " " + ansCnt);
        }
    }

    public static boolean determination(int candidate) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += count(info[i][0], info[i][1], info[i][2], candidate);
        }
        return sum % 2 == 1;
    }

    public static int count(int A, int C, int B, int X) {
        if (X < A)
            return 0;
        if (C < X)
            return (C - A) / B + 1;
        return (X - A) / B + 1;
    }
}
