package RHS_FC.class05_graph;

/*
케빈 베이컨의 6단계 법칙
유저의수 N명에 대해 각각 자신을 제외한 다른 사람과의 거리를 계산한 값을
통해 케빈 베이컨 값을 구할 수 있고 이를 통해서 점수 계산.

bfs 로 풀어도 되지만 floyd warshall 로 풀었음.
 */
import java.io.*;
import java.util.*;

public class Q1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answerIdx = -1;
        int answerValue = Integer.MAX_VALUE;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        int[][] point = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            // SolveByCategory.graph.add(new ArrayList<Integer>());
            Arrays.fill(point[i], 200);
            point[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // SolveByCategory.graph.get(u).add(v);
            // SolveByCategory.graph.get(v).add(u);
            point[u][v] = 1;
            point[v][u] = 1;

        }
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (point[i][k] + point[k][j] < point[i][j]) {
                        point[i][j] = point[i][k] + point[k][j];
                    }
                }
            }
        }
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                sum += point[i][j];
            }
            if (sum < answerValue) {
                answerValue = sum;
                answerIdx = i;
            }
        }
        System.out.println(answerIdx);
    }
}
