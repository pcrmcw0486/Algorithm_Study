package solvedac.level3;
/*
2021.12.03
문제번호 : Q16928
이름 및 난이도 : 뱀과 사다리게임 Silver I
문제이해 
-----------------
주사위 조작 시 최소 몇 번 만에 도착점에 도착가능할까.
주사위 (1~6)개  10*10 보드판,
1~100 순서대로 적혀있다.
 1. i위치 4칸 > i+4 칸
 2. 100칸 넘어간다면 이동할 수 없다.  ( 딱 100칸에 도착해야한다.)
 3. 도착한 칸 사다리라면, 사다리를 타고 위로 올라간다.
 4. 뱀 칸이라면 뱀을 따라 내려간다.
 5. 1칸시작 100칸 도착.

 사다리수와 뱀 수가 주어진다.

접근 방법 :
보드판 이지만 1~100까지 적혀있기 때문에 그냥 일차원 배열로 보아도 무방하다.
dp임. dp[x] x까지 가는데 걸리는 최소 횟수.
how to init? bfs/ dfs i like bfs.
제한 조건 : 

# 한 번 방문한 자리를 다시 방문 했을 때 최단거리를 만드는 경우의 수가 있는가??
visit check arr 만들기 전 check해야함.

문제풀면서 1. 우선순위에 대한 생각.ㅏ
2. visit에 대한 생각.
check의 중요성을 다시한번 느낀다.
메모리 및 시간초과는 대부분 check에서 비롯됨.

처음 문제 풀 때는 3 3 3 2 3 3 2 3 3 과 같이 처음 도착하는 것의 우선순위를 보장하지 못했음.
중복의 경우는 dp[]값을 통해 dp[x] == 0 일때만 접근하도록 하였음.

이후에 이를 PQ를 통해 해결하였으나 결국 중복의 문제 (dp가 필요하지 않다고 생각하여 제외했음)
 (2에서 3 4 5 6 7 8) 과 (1에서 2 3 4 5 6 7)과 같이 중복을 생각하지 못했음.

 로직을 변경시켜 visit배열을 활용하여 중복을 제거하고자 하였고
 사다리의 경우 2->98 이라고 할 때 2는 방문여부만 체크하면되고 98로 바로 옮기도록하였음.
 순차적인 bfs 순서가 보장이 되도록 로직을 구성하고 visit을 통해 중복을 확인함.
 
 결론) BFS의 경우 순서에 따라 최소거리 등과 같이 우선순위가 보장되도록 로직을 작성하여야함.
       또한 visit을 쓰는 것을 다른 방법을 통해 확인하면 되지 않을까 하는 생각 때문에
       꺼려하는데, 이 때는 "한번 방문한 자리를 다시 방문했을 때 조건을 만족하는 경우의 수"를
       따져서 다시한번 확인해보도록 하여야한다.
       대부분의 문제에서는 visit을 쓰는 것이 이해하기 편할 것 같다.
*/

import java.io.*;
import java.util.*;

public class Q16928 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> info = new HashMap<Integer, Integer>();
        // L과 S의 정보를 확인하기 위해 Map에 포함시킨다.
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            info.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            info.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        boolean[] visited = new boolean[101];
        int ans = -1;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 0));
        visited[1] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == 100) {
                ans = cur.dist;
                break;
            }
            for (int i = 1; i <= 6; i++) {
                int nx = cur.x + i;
                if (nx <= 100 && !visited[nx]) {
                    visited[nx] = true;
                    if (info.containsKey(nx)) {
                        nx = info.get(nx);
                        visited[nx] = true;
                    }
                    queue.add(new Point(nx, cur.dist + 1));
                }
            }
        }
        System.out.println(ans);
    }

    static class Point implements Comparable<Point> {
        int x;
        int dist;

        Point(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point o) {
            if (this.dist == o.dist)
                return this.x - o.x;
            return this.dist - o.dist;
        }
    }

}
