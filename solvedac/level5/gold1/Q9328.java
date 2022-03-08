package solvedac.level5.gold1;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q9328
 * @문제이름 : 열쇠
 * @난이도 : Gold I
 * @date : 2022-02-28 오후 10:38
 * @문제이해 상근이는 1층 빌딩에 침입해 문서를 훔치려고 한다.
 * 문선의 위치가 모두 나타나있다.
 * 문을 열려면 열쇠가 필요하고, 상근이는 일부 열쇠를 들고 있다.
 * 일부 열쇠는 바닥에 있다.
 * 훔칠 수 있는 문서의 최대 개수를 구핫오. (상하좌우만 움직일 수 있다.)
 * @알고리즘 그래프 탐색.
 * @접근방법 W, H < = 100
 * . 빈공간, *벽, 대문자(문) 소문자(열쇠) 열쇠가 하나도 없다면 0 으로 주어진다.
 * 빌딩 밖에서 '빌딩 가장자리 벽이 아닌 곳을 통해 드나들 수 있다.'
 * 더이상 갈 곳이 없으면 종료해야겟죠.
 * 상근이의 상태는 어떻게 정의 되는가?
 * 상근이의 상태는 위치 (x,y)와 들고 있는 열쇠로 정의된다.
 * 똑같은 열쇠를 들고 똑같은곳에 다시 갈 필요가 없기 때문이다.
 * 몇가지 생각을 해보자.
 * 1. 시작하는 지점
 * 시작하는 지점을 동시에 탐색하게 되는 것이 맞는가?
 * 서로 의존관계가 있을 수 있다.
 * 그렇다면 시작은 어떻게 되야하는가.
 * 통로 두개라고 생각해보자. 1번통로를 가야 2번통로에서 문을 열수 있고
 * 2번 통로를 가보아야 1번 통로의 문을 열수있는 반복적인 작업이 있다면?
 * 열쇠를 찾은 시점에서.그 전 상태들에 대해 무시하여야 한다.?
 * 같은 시간대에 두방향으로 나뉘어 갔을 때 각각 다른 열쇠를 가지게 된다면?
 * 하나는 ax이고 하나는 ab이고 전체로 생각했을 때 axb가 될 수 잇다면
 * 이 부분은 어떻게 생각할건가.
 * 대기중인 Queue와
 * 진행하는 Queue 두가지로 나뉜다.
 * 열쇠를 get할시. 대기중인 queue를 깨워 새로 탐색queue에 넣어준다...
 * 열쇠를 중복으로 먹을 수도 있기 때문에 처음 열쇠를 발견시 열쇠를 없애준다.
 */
public class Q9328 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            //input
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            map = new char[H+2][W+2];
            for (int i = 0; i < H + 2; i++) map[i][0] = map[i][W+1] = '.';
            for (int i = 0; i < W + 2; i++) map[0][i] = map[H+1][i] = '.';
            for (int i = 1; i < H+1; i++) {
                String line = br.readLine();
                for (int j = 1; j < W+1; j++) {
                    map[i][j] = line.charAt(j-1);
                }
            }
            String line = br.readLine();
            int key =0;
            if(!line.equals("0")){
                for (int i = 0; i < line.length(); i++) {
                    key |= (1 << (line.charAt(i) - 'a'));
                }
            }
            int cnt = 0;
            Queue<Point> queue = new LinkedList<>();
            //중복체크 배열
            boolean[][] visited = new boolean[H+2][W+2];
            //문 중복 방지
            boolean[][] doorLocked = new boolean[H+2][W+2];
            int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            visited[0][0] = true;
            queue.add(new Point(0, 0));
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
               // System.out.println(cur.x + " " + cur.y);
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dir[i][0];
                    int ny = cur.y + dir[i][1];
                    if(nx<0||ny<0||nx>H+1||ny>W+1) continue;
                    if(map[nx][ny] == '*' || visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    char option = map[nx][ny];
                    //case 1 : Doc
                    if (option == '$') {
                       // System.out.println(T+" ("+nx+" , " + ny + " ) ");
                        cnt++;
                        map[nx][ny] = '.';
                    }
                    //case 2 : Door
                    else if (option >= 'A' && option <= 'Z') {
                        //can open
                        if ((key & (1 << (option - 'A'))) != 0) {
                            map[nx][ny] = '.';
                           doorLocked[nx][ny] = false;
                        } else{
                            doorLocked[nx][ny] = true;
                            continue;
                        }
                    }
                    //case 3 : key
                    else if (option >= 'a' && option <= 'z') {
                        if ((key & (1 << (option - 'a'))) == 0) {
                            key |= (1 << (option - 'a'));
                            visited = new boolean[H+2][W+2];
                            visited[nx][ny]= true;
                            queue.clear();
                            queue.add(new Point(nx, ny));
                            continue;
                        } else {
                            map[nx][ny] = '.';
                        }
                    }
                    //default (feat. empty)
                    queue.add(new Point(nx, ny));
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
