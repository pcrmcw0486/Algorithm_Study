/*
https://www.acmicpc.net/problem/1717
*** 집합의 표현 Gold IV ***
*** 접근 방법 ***
*** 사용 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***
{0} {1} ... {n} 이 각각 n+1개의 집합을 이루고 있을 때, 합집합 연산과
두 원소가 같은 집합에 포함되어 있는지 확인하는 연산을 수행하려고 한다.
@input
    @ N : 집합 개수 1<= N <= 1_000_000
    @ M : 입력으로 주어지는 연산 개수 1<= M <= 100_000
      합집합 0 a b (a포함 집합과 b 포함 집합 합침.)
      같은집합 확인 1 a b (a와 b가 같은 집합에 포함되어 있는지 확인)
    a와 b는 0<= a,b <= n 의 정수, a와 b 는 같을 수도 있다.
@output
    1로 시작하는 입력에 대해 한 줄에 하나씩 YES/NO로 출력.
 */

package tree.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1717 {
    static int[] parent;
    static int[] rank;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        rank = new int[N + 1];

        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (option == 0) {
                if (a == b)
                    continue;
                union(a, b);
            } else if (option == 1) {
                if (a == b) {
                    sb.append("YES\n");
                    continue;
                }
                String str = (find(a) == find(b)) ? "YES\n" : "NO\n";
                sb.append(str);
            }
        }
        System.out.print(sb.toString());
    }

    static void init() {
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        int temp = 0;
        if (rootU == rootV)
            return;
        // make rootU>rootV always
        if (rank[rootV] > rank[rootU]) {
            temp = rootU;
            rootU = rootV;
            rootV = temp;
        }
        parent[rootV] = rootU;
        if (rank[rootV] == rank[rootU])
            rank[rootU]++;
    }
}
