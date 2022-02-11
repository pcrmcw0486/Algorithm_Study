/*
https://www.acmicpc.net/problem/4195
 *** 친구 네트워크 Gold II ***
 *** 접근 방법 ***
disjoint set 문제 중에서도 각 집합의 개수를 구하는 문제이다.
최적화를 위한 rank배열과 개수를 저장할 elementCount 배열이 필요하다.
union-find에서 응용하여 문제를 해결한다.

다만 사용자의 명수가 정해져서 있지 않고, 중복해서 나오지만
중복으로 처리하면 안됨. 고유의 값을 주고 이를 이용한다.
 *** 사용 알고리즘 자료구조 스킬 ***
 dijoint set, union-find 
 중복 키를 허용하지 않고 각 키마다 정해진 고유의 id를 지정하기 위한
 Map 자료구조를 사용한다. Clas를 생성하여 Set구조를 사용할 수도 있겟다.
 *** 문제 조건 ***
 @ time : 3s
 @ memory : 256MB
 @ 문제이해 : 
    어떤 사이트의 친구 관계가 생긴 순서대로 주어질 때, 두 사람의 네트워크에
    몇 명이 있는지 구하는 프로그램을 작성하시오.
    친구네트워크? 친구 관계만으로 이동할 수 있는 사이
@ 간략화 : dijointset element 개수 구하기. 문자열로 하기.
@ input
    T : Test case
    {
        F : 친구 관계 수 (<100_000) > 최대 200_000명의 다른 이용자.
        친구 관계 (Fred Barney) F개
        아이디 : Alpahbet Upper,Lower <= 20 문자열
    }
@output
    두 사용자가 주어졌을 때 각 네트워크의 친구 숫자.
 */
package SolveByCategory.tree.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q4195 {
    static int MAX_USER = 200_000;
    static int[] root;
    static int[] rank;
    static int[] elementCount;
    static Map<String, Integer> users;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            init();
            int F = Integer.parseInt(br.readLine());
            int pk = 0;
            while (F-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String p1 = st.nextToken();
                String p2 = st.nextToken();
                if (!users.containsKey(p1))
                    users.put(p1, pk++);
                if (!users.containsKey(p2))
                    users.put(p2, pk++);
                int p1Id = users.get(p1);
                int p2Id = users.get(p2);
                sb.append(union(p1Id, p2Id)).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    static void init() {
        root = new int[MAX_USER];
        rank = new int[MAX_USER];
        elementCount = new int[MAX_USER];
        users = new HashMap<>();
        for (int i = 0; i < MAX_USER; i++) {
            root[i] = i;
            rank[i] = 1;
            elementCount[i] = 1;
        }
    }

    static int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    static int union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        int temp = 0;
        if (rootX == rootY)
            return elementCount[rootX];
        if (rank[rootY] > rank[rootX]) {
            temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        root[rootY] = rootX;
        if (rank[rootX] == rank[rootY])
            rank[rootX]++;
        elementCount[rootX] += elementCount[rootY];
        elementCount[rootY] = 1;
        return elementCount[rootX];
    }

}
