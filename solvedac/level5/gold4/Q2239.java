package solvedac.level5.gold4;
/*
2022.01.13
문제번호 : Q2239
이름 및 난이도 : 스도쿠 Gold IV
문제이해 
-----------------
하다 만, 스도쿠 퍼즐이 주어질 때 마저 끝내는 프로그램을 작성하시오.
숫자가 채워지지 않은 칸에는 0이 주어진다.

제한 조건 :
 답이 여러개라면 사전식으로 앞서는 것을 출력한다.
 N^2 해봐야 81
접근 방법 :
  DFS를 통한 백트래킹 + 사전식이기 때문에 1부터 증가하는 순으로 확인한다.
  + 위 -> 아래 && 왼 -> 오 순으로 가야 사전순임.   
   칸의 check
    - 어떤 하나의 칸(i,j)에 들어가기 위해 확인해야하는 조건은 총 3가지이다.
    - i행에 x가 존재하는가. (가로)
    - j열에 x가 존재하는가 (세로)
    - 해당 칸이 존재하는 블럭에 x가 존재하는가?

    1. bit를 통한 존재여부 확인.
      checkRow[9];
      checkCol[9];
      checkBlock[9];
      1.1 i,j칸의 block확인 방법.
      block의 구분을
      0 1 2
      3 4 5
      6 7 8 로 한다고 가정하면,
      (i/3)*3 + (j/3) 을 통해 block 구분이 가능하다.

    FLOW
    1. data input
    2. data init ( 현재 상태 저장) O(N^2) * 3
    3. backtracking
*/

import java.io.*;
import java.util.*;

public class Q2239 {
    static int map[][];
    static int checkRow[];
    static int checkCol[];
    static int checkBlock[];
    static ArrayList<Point> emptySpace;
    static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        input();
        sudoku(0);
        printAns();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        checkRow = new int[9]; // 가로 i
        checkCol = new int[9]; // 세로 j
        checkBlock = new int[9];
        emptySpace = new ArrayList<Point>();
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 0) {
                    emptySpace.add(new Point(i, j));
                }
            }
        }

        // checkRow && col
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkRow[i] |= 1 << map[i][j];
                checkCol[j] |= 1 << map[i][j];
                checkBlock[getBlockNum(i, j)] |= 1 << map[i][j];
            }
        }
    }

    public static int getBlockNum(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    public static void sudoku(int num) {
        if (num == emptySpace.size()) {
            isEnd = true;
            return;
        }
        Point cur = emptySpace.get(num);
        int blockNum = getBlockNum(cur.x, cur.y);
        for (int i = 1; i < 10; i++) {
            int status = 1 << i;
            // check = checkRow[cur.x] | checkCol[cur.y] | checkBlock[blockNum]
            // check & status > 0  continue 로 한번에 해도됨.
            if ((checkRow[cur.x] & status) > 0)
                continue;
            if ((checkCol[cur.y] & status) > 0)
                continue;
            if ((checkBlock[blockNum] & status) > 0)
                continue;
            checkRow[cur.x] |= status;
            checkCol[cur.y] |= status;
            checkBlock[blockNum] |= status;
            map[cur.x][cur.y] = i;
            sudoku(num + 1);
            if (isEnd)
                return;
            // map[cur.x][cur.y] = 0;
            checkRow[cur.x] ^= status;
            checkCol[cur.y] ^= status;
            checkBlock[blockNum] ^= status;
        }
    }

    public static void printAns() {
        StringBuilder sb = new StringBuilder();
        //System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}