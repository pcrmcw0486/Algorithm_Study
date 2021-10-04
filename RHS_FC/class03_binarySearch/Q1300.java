package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/1300
K번째 수
N*N 배열 A를 만들어서 A[i][j] = i *j 일때 1차원 배열 B로 만들어 오름차순 정렬할 때
K번째 수는?

접근 방법
1. 전체 구해서 O(1) 탐색 N^2 O(1)인데
   N의 범위가 10^5 라서 10^10 이므로 시간제한에 걸림.
2. 그렇다면 K번째 수를 구하는 것이 아닌 X가 K번째 수인지 구해보자.
    막혔던게 그럼 X-1번째 까지 개수가 몇개인지를 구해야하는데
    약수구하는 걸로 생각해서 N *(N/2)로 하면 똑같이 N^2에
    log N 번해야되서 너무 커짐.
    그래서 개수 구하는게 포인트였는데 이를 O(N)에 가능.
    만약 N= 4이고 정한 수가 5라고 하면 K=10이라면.
    Min(4,5-1) 4
    4/4 = 1
    4/3 = 1
    4/2 = 2
    4/1 = 4
    5나오기전에 총 8개가 나옴. 5는 9 부터 있다는 뜻이고
    
    최대 (10^5-1)^2이 Int 범위 넘어가니까 long으로 해야함.
 */
import java.io.*;
import java.util.*;

public class Q1300 {
    static long N;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        K = Long.parseLong(br.readLine());
        long l = 1;
        long r = N * N;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (determination(mid))
                l = mid + 1;
            else
                r = mid;
        }
        System.out.println(l);
    }

    public static boolean determination(long key) {
        long x = Math.min(N, key);
        long count = 0;
        for (long i = 1; i < x + 1; i++) {
            count += key / i >= x ? x : key / i;
        }
        return count < K;
    }
}
