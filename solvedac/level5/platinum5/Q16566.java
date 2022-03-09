package solvedac.level5.platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q16566
 * @문제이름 : 카드 게임
 * @난이도 : Platinum V
 * @date : 2022-03-08 오후 4:53
 * @author : pcrmcw0486
 * @문제이해
 * N개의 빨간색 카드 (1~N) M개를 고른다.
 * N개의 파란색 카드(1~N) 빨간색에서 고른 번호와 같은 파란색카드 M개를 고른다.
 * 철수 - 빨간색 // 민수 - 파란색카드
 * 고른 카드 중 한장씩 비교하여 번호가 큰 사람이 이긴다.
 * 이 동작을 K번 해서 더 많이 이긴 사람이 승리한다.(한번 쓴 카드는 버린다.)
 *
 * 철수는 본인이 낼 카드를 조작할 수 있다.
 * 1. 카드 버리고 몰래 다시 들고 오기
 * 2. 민수한테 없는 카드 내기
 *
 * 민수는 철수가 낼 카드를 알수 있따
 * 1. 철수가 낼 카드보다 큰 카드가 있다면, 가장 작은 카드를 낸다
 *
 * K번 동안 철수가 낼 카드가 입력으로 주어질 때 민수는 어떤 카드를 낼지 출력하라.
 * N <= 4,000,000  K(M,10000)
 * @알고리즘
 * 이분탐색 disjoint set?
 * K개에 대하여 M개를 이분탐색 하여야함 그래서 총 탐색시간은 O(NlogN) *2
 * disjoint set
 *  전처리 O(N) 나머지는 O(1)이라고 보던데.
 * @접근방법
 * 가장 큰 대원칙 : 한번 쓴 카드는 버린다. (항상 지켜짐)
 * 철수는 꼴리는 대로 낸다 그냥.
 * 민수는 그거에 맞추어서 낸다
 * 민수는 '철수가 낼 카드보다 큰 카드가 있다면 큰 카드들 중 가장 작은 카드' >> 이분탐색
 *  2 3 4 5 7 8 9
 *  4 1 1 3 8
 *  이게 없어져야한다가 Point.
 *  2 3 5 5 5 6 7
 *  2 3 4 5 7 8 9
*/
public class Q16566 {
    static int[] data;
    static int[] parent;
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        data = new int[M];
        parent = new int[M+1];
        int[] card = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) data[i] = Integer.parseInt(st.nextToken());
        for(int i =0;i<M+1;i++)parent[i] = i;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) card[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        Arrays.sort(data);
        for (int i = 0; i < K; i++) {
            int pos = findCard(card[i]);
            int ans = find(pos);
            sb.append(data[ans]).append('\n');
            union(ans, ans + 1);
        }
        System.out.print(sb);
    }

    public static int findCard(int value) {
        int l =0, r = M;
        int mid;
        while (l <= r) {
            mid = (l+r)>>1;
            if (data[mid] <= value) {
                l = mid +1;
            }else{
                r = mid -1;
            }
        }
        return l;
    }

    public static int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    public static void union(int x, int y) {
        y = find(y);
        parent[x] = y;
    }
}
