package backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q14889 {
    static int[][] ability;
    static boolean[] team;
    static int N;
    static int result = Integer.MAX_VALUE;
    static int half;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        team = new boolean[N];
        half = N >> 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        team[0] = true;
        solution(0, 1);
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int person_num, int count) {
        if (count == half) {
            result = Math.min(result, get_Answer());
            return;
        }

        for (int i = person_num + 1; i < N; i++) {
            team[i] = true;
            solution(i, count + 1);
            team[i] = false;
        }
    }

    public static int get_Answer() {
        int start_team = 0;
        int link_team = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (team[i] && team[j])
                    start_team += ability[i][j];
                else if (!team[i] && !team[j])
                    link_team += ability[i][j];
            }
        }

        return Math.abs(start_team - link_team);
    }
}
