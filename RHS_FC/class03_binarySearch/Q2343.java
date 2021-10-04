package RHS_FC.class03_binarySearch;

/*
기타레슨 Silver I 공유기 설치와 비슷한 느낌이네.
블루레이 총 N개 강의가 들어감(강의 순서 바뀌면 X)
i번과 j를 같이 녹화하려면 i와 j사이도 다 있어야함.

블루레이 개수를 가급적 줄이려고 한다
M개의 블루레이에 녹화
이 때 블루레이의 크기(녹화 가능길이)를 최소로.
M개의 블루레이는 모두 같은 크기.
가능한 블루레이 크기 중 최소를 구하는.
N -> M개로 만드는데 M개로 분할 할 때 
가장 짧은 길이로
Point를 분에 잡아야함.
X분 이하로 M개의 블루레이를 만들 수 있는가?Yes/No
가 가능한 최소 X분

데이터 타입 N 100_000, 강의 길이 10_000
다 해봐야 10^9 int 가능.


 */
import java.io.*;
import java.util.*;

public class Q2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(st.nextToken());
        int l = 1;
        int r = Integer.MAX_VALUE;
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (determination(data, M, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans + 1);
    }

    /*
     * false ||(ans) true true ||(ans) false > 여기서 true는 X분 이하로 M개의 블루레이 못만듦.
     */
    public static boolean determination(int[] data, int target, int len) {
        int count = 0;
        int tmpLen = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > len)
                return true;
            if (data[i] + tmpLen > len) {
                tmpLen = 0;
                count++;
            }
            tmpLen += data[i];
        }
        count++;
        return count > target;
    }
}
