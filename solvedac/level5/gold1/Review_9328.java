package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Review_9328 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C;
    static char[][] map;
    static String keySet;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input(br);
            sb.append(solve()).append('\n');
        }
        System.out.println(sb);
    }

    private static void input(BufferedReader br) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R + 2][C + 2];
        Arrays.fill(map[0], '.');
        Arrays.fill(map[R + 1], '.');
        for (int i = 1; i < R + 1; i++) {
            map[i][0] = map[i][C + 1] = '.';
            String line = br.readLine();
            for (int j = 1; j < C + 1; j++) {
                map[i][j] = line.charAt(j-1);
            }
        }
        keySet = br.readLine();
    }

    public static int solve() {
        boolean[] keys = new boolean[26];
        boolean[][] isVisited = new boolean[R + 2][C + 2];
        Queue<Point> queue = new LinkedList<>();
        ArrayDeque<Point> readyQueue = new ArrayDeque<>();
        //KeySet 설정
        if (!keySet.equals("0")) {
            for (int i = 0; i < keySet.length(); i++) {
                keys[keySet.charAt(i) - 'a'] = true;
            }
        }

        int ans = 0;
        isVisited[0][0] = true;
        queue.add(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dir[i][0];
                int ny = cur.y + dir[i][1];
                if (nx < 0 || ny < 0 || nx > R + 1 || ny > C + 1) continue;
                if (isVisited[nx][ny]) continue;
                //case 1 벽
                if (map[nx][ny] == '*') continue;
                //case 2 빈 곳
                if (map[nx][ny] == '.') {
                    isVisited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
                //case 3 문서
                else if (map[nx][ny] == '$') {
                    isVisited[nx][ny] = true;
                    ans++;
                    map[nx][ny] = '.';
                    queue.add(new Point(nx, ny));
                }
                //case 4 열쇠
                else if (isLower(map[nx][ny])) {
                    int key = map[nx][ny] - 'a';
                    //case 4-1 처음 발견한 열쇠
                    if (!keys[key]) {
                        keys[key] = true;
                        // 대기 중인 문들 열기.
                        int size = readyQueue.size();
                        for (int j = 0; j < size; j++) {
                            Point p = readyQueue.peek();
                            if (map[p.x][p.y] - 'A' == key)
                                queue.add(readyQueue.poll());
                            else
                                readyQueue.addLast(readyQueue.poll());
                        }
                    }
                    //case 4-2 가지고 있던 열쇠 (공통 처리)
                    isVisited[nx][ny] = true;
                    map[nx][ny] = '.';
                    queue.add(new Point(nx, ny));
                }
                //case 5 문
                else {
                    int doorNum = map[nx][ny] - 'A';
                    if (keys[doorNum]) {
                        //문을 열 수 있을 때
                        isVisited[nx][ny] = true;
                        map[nx][ny] = '.';
                        queue.add(new Point(nx, ny));
                    } else {
                        //문을 열 수 없을 때
                        readyQueue.add(new Point(nx, ny));
                    }
                }
            }
        }
        return ans;
    }

    public static boolean isLower(char a) {
        return a <= 'z' && a >= 'a';
    }

    public static boolean isUpper(char a) {
        return a <= 'Z' && a >= 'A';
    }
}
