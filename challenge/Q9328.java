/*
https://www.acmicpc.net/problem/9328
열쇠 Gold I 
위상정렬의 이해와 동굴탐험 문제 해결을 위한 문제
*** 접근 방법 및 문제 조건 ***
*** 알고리즘 자료구조 스킬 ***
. 빈공간 || * 벽 || $ 훔칠 문서 || 대문자 문 || 소문자 열쇠
열쇠가 하나도 없다면 0으로 주어짐.
첫 위치는 빌딩의 밖이며 빌딩 가장자리의 빈 공간이나 문을 통해
안팎으로 드나들 수 있음. 
각각의 문에 대해 열쇠의 개수는 0, 1... 이며 
각각의 열쇠에 대해서 그 열쇠로 열 수 있는 문도 0, 1... 이다.
열쇠는 여러번 중복 사용 가능하다.

- 중복 제거해서 열쇠 받아와야하고.

 */
package challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Point1 {
    int x;
    int y;

    Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Q9328 {
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static char[][] graph;
    static int w;
    static int h;
    static Queue<Point1> candidate;
    static Set<Character> keySet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            graph = new char[w][h];
            for (int i = 0; i < w; i++)
                graph[i] = br.readLine().toCharArray();
            keySet = new HashSet<>();
            String keys = br.readLine();
            for (int i = 0; i < keys.length(); i++)
                keySet.add(keys.charAt(i));
            Queue<Point1> queue = new LinkedList<>();
            candidate = new LinkedList<>();
            // 입구 찾기 &queue에 넣기.
            // dfs 돌리기.끝? 일단 순서가 있어서 dfs는 맞는거 같음.
            for (int i = 0; i < h; i++) {
                if (graph[0][i] == '.' || keySet.contains(Character.valueOf(graph[0][i]))) {
                    queue.offer(new Point1(0, i));
                }
                if (graph[w - 1][i] == '.' || keySet.contains(Character.valueOf(graph[w - 1][i]))) {
                    queue.offer(new Point1(w - 1, i));
                }
            }
            for (int i = 0; i < w; i++) {
                if (graph[i][0] == '.' || keySet.contains(Character.valueOf(graph[i][0]))) {
                    queue.offer(new Point1(i, 0));
                }
                if (graph[i][h - 1] == '.' || keySet.contains(Character.valueOf(graph[i][h - 1]))) {
                    queue.offer(new Point1(i, h - 1));
                }
            }
            visited = new boolean[w][h];
            while (!queue.isEmpty()) {
                Point1 start = queue.poll();
                if (!visited[start.x][start.y]) {
                    visited[start.x][start.y] = true;
                    dfs(start.x, start.y);
                }
            }
        }
    }

    // 갈 수 있는 곳을 모두 간다.
    // 가지 못하는 벽은 제외한다.
    // 열쇠가 있으면 갈 수 있는 곳은?
    // 두번째 시작지점에서 첫번째 시작지점의 후보지역의 키를 획득한다면?

    public static void dfs(int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx > w - 1 || ny > h - 1)
                continue;
            if (graph[nx][ny] == '*')
                continue;
            // *이 아니고 갈 수 있는 경우 총 세가지
            // . 이거나 문(대문자)이거나 열쇠(소문자)이거나.
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                if (graph[nx][ny] == '*') {

                } else if (Character.isUpperCase(graph[nx][ny])) {
                    if (keySet.contains(Character.valueOf(Character.toLowerCase(graph[nx][ny])))) {
                        // todo
                    }
                } else if (Character.isLowerCase(graph[nx][ny])) {

                }
            }
        }
    }
}
