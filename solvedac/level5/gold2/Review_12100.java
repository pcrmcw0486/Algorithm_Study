package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Review_12100 {
    static int N;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        solve(map, 0);
        System.out.println(max);
    }

    public static void solve(int[][] map, int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }
        solve(moveUp(map), depth + 1);
        solve(moveRight(map), depth + 1);
        solve(moveDown(map), depth + 1);
        solve(moveLeft(map), depth + 1);
    }

    private static int[][] moveLeft(int[][] map) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            int idx = N-1;
            for (int j = N-1; j >=0; j--) {
                if (map[i][j] == 0) continue;
                if(stack.isEmpty()) stack.push(map[i][j]);
                else{
                    if (stack.firstElement() == map[i][j]) {
                        newMap[i][idx--] = map[i][j] * 2;
                        stack.pop();
                    } else {
                        newMap[i][idx--] = stack.pop();
                        stack.push(map[i][j]);
                    }
                }
            }
            while (!stack.isEmpty()) {
                newMap[i][idx--] = stack.pop();
            }
        }
        return newMap;
    }

    private static int[][] moveDown(int[][] map) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            int idx = N-1;
            for (int j = N-1; j >=0; j--) {
                if (map[j][i] == 0) continue;
                if(stack.isEmpty()) stack.push(map[j][i]);
                else{
                    if (stack.firstElement() == map[j][i]) {
                        newMap[idx--][i] = map[j][i] * 2;
                        stack.pop();
                    } else {
                        newMap[idx--][i] = stack.pop();
                        stack.push(map[j][i]);
                    }
                }
            }
            while (!stack.isEmpty()) {
                newMap[idx--][i] = stack.pop();
            }
        }
        return newMap;
    }


    private static int[][] moveRight(int[][] map) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                if(stack.isEmpty()) stack.push(map[i][j]);
                else{
                    if (stack.firstElement() == map[i][j]) {
                        newMap[i][idx++] = map[i][j] * 2;
                        stack.pop();
                    } else {
                        newMap[i][idx++] = stack.pop();
                        stack.push(map[i][j]);
                    }
                }
            }
            while (!stack.isEmpty()) {
                newMap[i][idx++] = stack.pop();
            }
        }
        return newMap;
    }

    public static int[][] moveUp(int[][] map) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            int idx = 0;
            for (int j = 0; j < N; j++) {
                if (map[j][i] == 0) continue;
                if(stack.isEmpty()) stack.push(map[j][i]);
                else{
                    if (stack.firstElement() == map[j][i]) {
                        newMap[idx++][i] = map[j][i] * 2;
                        stack.pop();
                    } else {
                        newMap[idx++][i] = stack.pop();
                        stack.push(map[j][i]);
                    }
                }
            }
            while (!stack.isEmpty()) {
                newMap[idx++][i] = stack.pop();
            }
        }
        return newMap;
    }
}
