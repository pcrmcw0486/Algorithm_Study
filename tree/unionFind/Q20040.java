/*
https://www.acmicpc.net/problem/20040
*** 사이클 게임 Gold IV ***
*** 접근 방법 ***
문제가 길고, 만약 union-find 카테고리에 없엇다면
주어지는 입력 점들에 대해 그래프를 생성해서 cycle여부를 확인 했을 수
도 있겠다. 그렇게 생각하기에는 시간이 1sec라는 것에서 의심하고 
주어진 input 값의 범위가 너무 크지 않나라고 생각하고
다른 방법이 없나라고 고민했을 수 있을 것 같다.

쉽게 문제를 간단히 하면 하나의 줄로 이어진 선분을 하나의 집합으로보고
집합에 속한 원소들끼리 연결이 될 경우 fail로 처리하면된다.

*** 알고리즘 자료구조 스킬 ***
disjoint set, union-find
*** 문제 조건 ***
@time : 1sec
@memory : 512 MB
@문제 이해
 선플레이어 - 홀수, 후 플레이어-짝수
 0~n-1 까지 고유한 번호가 부여된 평면 상의 점 n개가 주어짐.
 이 중 어느 세점도 일직선 위에 놓이지 않는다.
 매 차례마다 플레이어는 두 점을 선택하여 이를 연결하는데,
 이전에 그린 선분을 다시 그을 수는 없지만 이미 그린 선분과 교차하는 것은
 가능. 처음으로 사이클을 완성하는 순간 게임이 종료.
 사이클 C는 플레이어들이 그린 선분들의 부분 집합으로
 C에 속한 임의의 선분의 한 끝점에서 모든 선분을 한 번씩만 지나서
 출발점으로 되돌아 올 수 있다.
 게임의 진행상황이 주어질 때, 몇 번째 차례에서 사이클이 완성되었는지,
 혹은 게임이 진행 중인지 판단하는 프로그램을 작성

@input
    @ n : 점의 개수 (3<= n <= 500_000) ( 0부터 n-1)
    @ m : 진행된 차례의 수 (3<=m <=1_000_000)
      m개의 입력줄 > i번째(1~m) 차례에 해당 플레이어가 선택한 두점의 번호
@output
    m번째 차례까지 게임을 진행한 상황에서 이미 게임이 종료되었따면
    사이클이 처음으로 만들어진 차례의 번호를 양의 정수로 출력
    m번 이후 종료되지 않았다면 0
 */
package tree.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20040 {
    static int[] root;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int step = 0;
        init(N);

        for (step = 1; step < M + 1; step++) {
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken());
            int point2 = Integer.parseInt(st.nextToken());
            if (!union(point1, point2))
                break;
        }
        System.out.println(step == M + 1 ? 0 : step);

    }

    public static void init(int N) {
        root = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public static int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    public static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        int temp = 0;
        if (rootX == rootY)
            return false;
        if (rank[rootY] > rank[rootX]) {
            temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        root[rootY] = rootX;
        if (rank[rootX] == rank[rootY])
            rank[rootX]++;
        return true;
    }

}
