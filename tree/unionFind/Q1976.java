/*
https://www.acmicpc.net/problem/1976
*** 여행가자 Gold IV ***
*** 접근방법 ***
길찾기 문제 처럼 보이는 disjoint set 문제이다.
중복 방문이 가능하기 때문에 cycle이 있어서 최소, 최대 길이를 구하지 못함.
그 도시가 연결되어 있는지에 대한 여부가 중요함. 다른말로
같은 그룹인가.임.
*** 사용 알고리즘 자료구조 스킬 ***
dijoint set, union-find

*** 문제 조건 ***
@ time : 2s
@ memory : 128MB
@ 문제 이해 : 
    한국에 도시 N개 임의의 두 도시 사이에 길이 있을 수도 없을 수도(양방향)
    여행 일정이 주어질 때, 이 여행 경로가 가능한 것인지 알아보자.
    E- A - B - C 일때, 계획이 ECBCD라면 EABCBCBD라는 여행경로로 가능하다.
        \ /
         D
    도시 개수와 도시 연결여부가 주어져있고, 여행계획에 속한 도시가 주어질 때,
    가능한지 여부를 판별하는 프로그램을 작성, 같은 도시 여러번 가능.
@input 
    N : 도시의 수 (N<=200)
    M : 여행 계획에 속한 도시들 수 M (<=1000)
    이후 N개 의 줄에 N개의 정수가 주어짐.
    i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미
    1이면 연결 O , 0이면 연결 X
    A와 B가 연결이면 B와 A도 연결.
    마지막 줄에는 여행계획이 주어진다.
    도시 번호는 1부터 N까지이다.
 */
package tree.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q1976 {
    static int[] root;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        int[] travelRoute = new int[M];
        String str;

        init(N);
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < N; ++j) {
                if (str.charAt(j * 2) == '1')
                    union(i, j);
            }
        }

        travelRoute = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int root = find(travelRoute[0] - 1);
        boolean check = true;
        for (int i = 1; i < travelRoute.length; i++) {
            if (root != find(travelRoute[i] - 1))
                check = false;
        }
        System.out.println(check ? "YES" : "NO");
    }

    static void init(int N) {
        root = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    static int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        int temp = 0;
        if (rootU == rootV)
            return;
        if (rank[rootV] > rank[rootU]) {
            temp = rootU;
            rootU = rootV;
            rootV = temp;
        }
        root[rootV] = rootU;
        if (rank[rootV] == rank[rootU])
            rank[rootU]++;
    }
}
