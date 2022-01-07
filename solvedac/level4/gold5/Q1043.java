package solvedac.level4.gold5;
/*
2021.12.19
문제번호 : Q1043
이름 및 난이도 : 거짓말 Gold IV
문제이해 
-----------------
지민이는 과장해서 말한다. 거짓말쟁이는 되고 싶지 않다.
진실을 아는 사람이 있다면 진실을 이야기한다.
어떤 사람이 어떤 파티에서 진실을 듣고 또 다른 파티에서는 과장된 이야기를 들으면
지민이는 거짓말쟁이가 된다.
진실을 아는 사람은 진실만 들어야하고 거짓말을 듣는 사람은 거짓말만 들어야하는 구나.
사람의 수 N이 주어질 때 진실을 아는 사람이 주어진다.
지민이는 모든 파티에 참가해야한다.
지민이가 거짓말 쟁이로 알려지지 않으면서 과장된 이야기를 할 수 있는 파티 개수의 최댓값.

사람수 N과 파티 수 M이 주어진다.

접근 방법 :
진실을 아는 사람 앞에서만 진실만 얘기하면된다.
근데 함정은 거짓말을 다하고 뒤에 아는 사람들이 포함될 때..

진실을 알아야하는 집합과 아닌 집합을 일단 구분한다.

진실을 알아야하는 집합에서 진실을 알게된 사람을 구하고
말할 수 있는 집합에서 다시 체크한다..?

disjoint set 높이 낮추기 및 가장 대표 되는 번호를 통해 해결 가능한 문제.
제한 조건 : 
2초.
*/

import java.io.*;
import java.util.*;

public class Q1043 {
    static int N, M;
    static int ans = 0;
    static int[][] party;
    static int[] knownPeople;
    static int[] group;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // Known People
        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        knownPeople = new int[size];
        for (int i = 0; i < size; i++)
            knownPeople[i] = Integer.parseInt(st.nextToken());

        // Party
        party = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            party[i] = new int[size];
            for (int j = 0; j < size; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Data init
        group = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            group[i] = i;

    }

    public static void solution() {
        if (knownPeople.length == 0) {
            System.out.println(M);
            return;
        }
        // party 사람끼리 묶어주기.
        for (int i = 0; i < M; i++) {
            // union-find with groupLeader
            int groupLeader = party[i][0];
            for (int j = 1; j < party[i].length; j++) {
                union(groupLeader, party[i][j]);
            }
        }
        // ans계산
        int cnt = M;
        boolean knowLie = false;
        // tree 높이 줄이기 +
        for (int i = 1; i < N + 1; i++)
            find(i);
        for (int i = 0; i < M; i++) {
            knowLie = false;
            for (int j = 0; j < knownPeople.length; j++) {
                if (group[party[i][0]] == group[knownPeople[j]]) {
                    knowLie = true;
                    break;
                }
            }
            if (knowLie)
                cnt--;
        }

        System.out.println(cnt);
    }

    public static int find(int x) {
        if (x == group[x])
            return x;
        return group[x] = find(group[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        group[y] = x;
    }

    // static int[] parent;
    // static Set<Integer> truePeople;
    // public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // StringTokenizer st = new StringTokenizer(br.readLine());
    // int N = Integer.parseInt(st.nextToken());
    // int M = Integer.parseInt(st.nextToken());
    // truePeople = new HashSet<Integer>();
    // ArrayList<int[]> partyList = new ArrayList<int[]>();
    // parent = new int[N + 1];
    // for (int i = 1; i < N + 1; i++)
    // parent[i] = i;

    // st = new StringTokenizer(br.readLine());
    // int K = Integer.parseInt(st.nextToken());
    // for (int i = 0; i < K; i++) {
    // // 진실을 아는 사람
    // truePeople.add(Integer.parseInt(st.nextToken()));
    // }
    // int ans = 0;
    // if (K == 0)
    // ans = M;
    // else {
    // // 파티 data
    // for (int i = 0; i < M; i++) {
    // st = new StringTokenizer(br.readLine());
    // int partySize = Integer.parseInt(st.nextToken());
    // int[] subParty = new int[partySize];
    // if (partySize != 0) {
    // int prev = Integer.parseInt(st.nextToken());
    // subParty[0] = prev;
    // for (int j = 1; j < partySize; j++) {
    // int cur = Integer.parseInt(st.nextToken());
    // subParty[j] = cur;
    // union(prev, cur);
    // }
    // }
    // partyList.add(subParty);
    // }
    // int cnt = 0;
    // for (int[] tmp : partyList) {
    // for (int num : tmp) {
    // if (!check(num)) {
    // cnt++;
    // break;
    // }
    // }
    // }
    // ans = M - cnt;
    // }
    // System.out.println(ans);
    // }

    // public static boolean check(int num) {
    // num = find(num);
    // for (int item : truePeople) {
    // if (num == find(item))
    // return false;
    // }
    // return true;
    // }

    // public static int find(int x) {
    // if (parent[x] == x)
    // return x;
    // return parent[x] = find(parent[x]);
    // }

    // public static void union(int x, int y) {
    // int p1 = find(x);
    // int p2 = find(y);
    // if (p1 == p2)
    // return;
    // else
    // parent[p1] = p2;
    // }

}
