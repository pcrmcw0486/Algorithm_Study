package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_16742
 * @문제이름 : 피리 부는 사나이
 * @난이도 : Gold II
 * @date : 2022-02-26 오후 2:42
 * @author : pcrmcw0486
 * @문제이해
 * 정해진 방향으로 움직일 때 (U,D, L, R)
 * SAFEZONE에 가면 더 이상 움직이지 못하게 할대, 최소의 SAFEZONE 설치 개수
 * 어디 있더라도 들어가게 해야한다.
 * @알고리즘
 * 지도 안에서 어디든 계손해서 움직이는 cycle이 항상 존재한다.
 * cycle개수를 파악해야하는데, 조금 더 정확하게는
 * cycle과 해당 cycle에 연결된, 칸들을 '하나의 그룹'으로 묶어야 한다.
 * @접근방법
 * 신박하게 두 가지 방법이 있다.
 * 정석적으로 DFS를 통해 grouping을 하는 방법. using number
 * 또는 disjoint set을 활용해서 grouping을 하는 방법.
*/
public class Review_16742 {
    static char[][] map;
    static int[][] groupMap;
    static boolean[][] isVisited;
    static int groupNum =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        groupMap = new int[N][M];
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        for(int i =0;i<N;i++){
            for(int j=0;j<M;j++){
                if(!isVisited[i][j]){
                    findGroup(i, j);
                }
            }
        }
        for (int[] line : groupMap) {
            for (int c : line) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("==========");
        System.out.println(groupNum);
    }

    private static void findGroup(int i, int j) {
        isVisited[i][j] = true;
        int nx =i;
        int ny = j;
        switch (map[i][j]) {
            case 'U':
                nx-=1;
                break;
            case 'R':
                ny+=1;
                break;
            case 'D':
                nx+=1;
                break;
            case 'L':
                ny-=1;
                break;
        }
        if (isVisited[nx][ny]) {
            if(groupMap[nx][ny] ==0){
                groupMap[i][j] = ++groupNum;
            }else{
                groupMap[i][j] = groupMap[nx][ny];
            }
        }else{
            findGroup(nx, ny);
            groupMap[i][j] = groupMap[nx][ny];
        }
    }

}
