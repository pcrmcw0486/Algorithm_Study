package programmers.kakao.year2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

class Position1 {
    int x;
    int y;
    // curve count
    int curveCount;
    int prevDir;
    int cost;

    Position1(int x, int y, int curveCount, int prevDir, int cost) {
        this.x = x;
        this.y = y;
        this.curveCount = curveCount;
        this.prevDir = prevDir;
        this.cost = cost;
    }
}

public class makeRaceTrack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++)
            board[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int result = solution(board);
        System.out.println(result);
    }

    public static int solution(int[][] board) {
        return 1;
    }
}
