package RHS_FC.class03_binarySearch;

/*
공유기 설치 Silver I
집 N개 수직선 위, 공유기 C개 설치
가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치
가장 작은 거리 값이 가장 크도록.
N 200,000
C <=N
좌표 1000000000
문제의 변환
C개의 공유기를 설치 했을 때, 최대 인접거리(D)
어떤 거리만큼 거리를 둘 때(D), 공유기 C개를 둘 수 있는가? > Yes/No
정렬 O(NlogN)
D를 정해서 결정 문제 한번 풀때 O(N) >yes / no 판별
정답의 범위를 이분탐색하면서 풀기(O(logX)) > N * logX
총 시간 복잡도O(NlogN + NlogX)

true (an)| false
또는
false|(an) true 결정 상태에 따라 나누어짐.
 */
import java.io.*;
import java.util.*;

public class Q2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);
        System.out.println(solution(house, C));
    }

    // true (answer)|| false
    // false || true
    // 결정문제는 거리 D이상으로 C개를 설치 가능한가? Yes/No
    public static int solution(int[] house, int C) {
        int l = 1;
        int r = 1000000000;
        int m;
        while (l < r) {
            if (determination(house, m = l + r >> 1, C)) {
                l = m + 1;
            } else
                r = m;
        }
        return l - 1;
    }

    public static boolean determination(int[] house, int dist, int C) {
        int count = 1;
        int tmpDist = 0;
        for (int i = 1; i < house.length; i++) {
            if (house[i] - house[i - 1] + tmpDist < dist) {
                tmpDist += house[i] - house[i - 1];
            } else {
                count++;
                tmpDist = 0;
            }
        }
        return count >= C;
    }

}
