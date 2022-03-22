package DayByDay._0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14889
 * @문제이름 : 스타트와 링크
 * @난이도 : Siver II
 * @date : 2022-03-21 오전 10:29
 * @author : pcrmcw0486
 * @문제이해
 * N명의 사람들을 N/2명으로 이루어진 스타트와 링크 팀으로 사람들을 나눈다.
 * 1~N까지로 사람을 배정. S[i][j] = i번사람과 j번사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치.
 * 팀 간 능력치 차이를 최소로 하려고 한다.
 * @알고리즘
19C9 calc
 * @접근방법

*/
public class Q14889 {
    static int N, ans;
    static boolean[] isTeamA;
    static int[][] ability;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        isTeamA = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isTeamA[0] = true;
        ans =Integer.MAX_VALUE;
        solve(0, 1);
        System.out.println(ans);
    }
    public static void solve(int idx, int depth) {
        if (depth == (N / 2)) {
            int teamAcnt =0;
            int teamBCnt =0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isTeamA[i] && isTeamA[i] == isTeamA[j]) {
                        teamAcnt += ability[i][j];
                    } else if (!isTeamA[i] && isTeamA[i] == isTeamA[j]) {
                        teamBCnt+= ability[i][j];
                    }
                }
            }
            ans = Math.min(ans, Math.abs(teamAcnt - teamBCnt));
            return;
        }
        for (int i = idx+1; i < N; i++) {
            isTeamA[i] = true;
            solve(i , depth + 1);
            isTeamA[i] = false;
        }
    }
}
