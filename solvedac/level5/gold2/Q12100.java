package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2048
 * @문제이름 : 2048
 * @난이도 : GoldII
 * @date : 2022-02-18 오후 4:29
 * @author : pcrmcw0486
 * @문제이해
 * 2048게임 만들기
 * @알고리즘
 * 그냥 개 빡 구현인거 같은데
 * 백트래킹. 완탐. < > 가 같지 않기 때문에.
 * pruning 함수는 움직임이 없을때.
 * 최대 5번 이동해서 만들 수 있는 가장 큰 블록
 * @접근방법

*/
public class Q12100 {
    static int ans = 0 ;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
      //  moveRight(map);
        //moveLeft(map);
        //moveDown(map);
        //moveRight(map);

       // System.out.println(ans);
        solve(map, 0);
        System.out.println(ans);
    }
    public static void solve(int[][] map, int depth){
        if(depth ==5){
            for(int i =0;i<N;i++){
                for(int j=0;j<N;j++){
                    ans = Math.max(ans, map[i][j]);
                }
            }
//            System.out.println("--------------------");
//            for(int[] line : map){
//                for(int x : line){
//                    System.out.print(x + " ");
//                }
//                System.out.println();
//            }
            return;
        }
        //4방향으로 모두 움직여본다.
        int[][] cloneMap= new int[N][N];


        for(int i =0;i<N;i++){
            for(int j=0;j<N;j++){
                cloneMap[i][j] = map[i][j];
            }
        }

        moveUp(cloneMap) ;
        solve(cloneMap,depth+1);



        for(int i =0;i<N;i++){
            for(int j=0;j<N;j++){
                cloneMap[i][j] = map[i][j];
            }
        }
        moveRight(cloneMap);
            solve(cloneMap,depth+1);



        for(int i =0;i<N;i++){
            for(int j=0;j<N;j++){
                cloneMap[i][j] = map[i][j];
            }
        }
        moveDown(cloneMap);
        solve(cloneMap,depth+1);


        for(int i =0;i<N;i++){
            for(int j=0;j<N;j++){
                cloneMap[i][j] = map[i][j];
            }
        }
        moveLeft(cloneMap);
        solve(cloneMap,depth+1);

    }

    private static int moveRight(int[][] map) {
        int prev =0;
        int prevIdx = 0;
        boolean isFirst;
        int cnt = 0;
        for(int i =0;i<N;i++){
            prev =0;
            isFirst = true;
            prevIdx =0;
            for(int j=0;j<N;j++){
                ans = Math.max(ans, map[i][j]);
                if(map[i][j]==0) continue;
                if(isFirst){
                    prev = map[i][j];
                    ans = Math.max(ans, prev);
                    isFirst = false;
                }else{
                    if (map[i][j] == prev) {
                        map[i][j] =0;
                        map[i][prevIdx] = prev*2;
                        ans = Math.max(ans, map[i][prevIdx]);
                        prevIdx++;
                        isFirst = true;
                        cnt++;
                        prev =0;
                    }else{
                        ans = Math.max(ans,prev);
                        map[i][prevIdx] = prev;
                        prev = map[i][j];
                        ans = Math.max(ans, prev);
                        prevIdx++;
                    }
                }
                map[i][j] =0;
            }
            map[i][prevIdx] = prev;
           if(map[i][N-1] !=0) cnt++;
        }
        return cnt;
    }

    private static int moveUp(int[][] map) {
        int prev =0;
        int prevIdx = 0;
        boolean isFirst;
        int cnt = 0;
        int check =0;
        for(int i =0;i<N;i++){
            prev =0;
            isFirst = true;
            prevIdx =0;
            check =0;
            for(int j=0;j<N;j++){
                ans = Math.max(ans, map[i][j]);
                if(map[j][i]==0){
                    check++;
                    continue;
                }
                if(isFirst){
                    prev = map[j][i];
                    ans = Math.max(ans, prev);

                    isFirst = false;
                }else{
                    if (map[j][i] == prev) {
                        map[j][i] =0;
                        map[prevIdx][i] = prev*2;
                        ans = Math.max(ans, map[prevIdx][i]);
                        prevIdx++;
                        isFirst = true;
                        cnt++;
                        prev =0;
                    }else{
                        ans = Math.max(ans, prev);
                        map[prevIdx][i] = prev;
                        prev = map[j][i];
                        ans = Math.max(ans, prev);
                        prevIdx++;
                    }
                }
                map[j][i] =0;
            }
            map[prevIdx][i] = prev;
        }
        return cnt;
    }

    private static int moveLeft(int[][] map) {
        int prev =0;
        int prevIdx = 0;
        boolean isFirst;
        int cnt = 0;
        for(int i =0;i<N;i++){
            prev =0;
            isFirst = true;
            prevIdx =N-1;
            for(int j=N-1;j>=0;j--){
                ans = Math.max(ans, map[i][j]);
                if(map[i][j]==0) continue;
                if(isFirst){
                    prev = map[i][j];
                    ans = Math.max(ans, prev);

                    isFirst = false;
                }else{
                    if (map[i][j] == prev) {
                        map[i][j] =0;
                        map[i][prevIdx] = prev*2;
                        prevIdx--;
                        isFirst = true;
                        cnt++;
                        prev =0;
                    }else{
                        ans = Math.max(ans, prev);
                        map[i][prevIdx] = prev;
                        prev = map[i][j];
                        prevIdx--;
                    }
                }
                map[i][j] =0;
            }
            map[i][prevIdx] = prev;
            if(map[i][N-1] !=0) cnt++;
        }
        return cnt;
    }

    private static int moveDown(int[][] map) {
        int prev =0;
        int prevIdx = 0;
        boolean isFirst;
        int cnt = 0;
        for(int i =0;i<N;i++){
            prev =0;
            isFirst = true;
            prevIdx =N-1;
            for(int j=N-1;j>=0;j--){
                ans = Math.max(ans, map[i][j]);
                if(map[j][i]==0) continue;
                if(isFirst){
                    prev = map[j][i];
                    isFirst = false;
                }else{
                    if (map[j][i] == prev) {
                        map[j][i] =0;
                        map[prevIdx][i] = prev*2;
                        prevIdx--;
                        isFirst = true;
                        cnt++;
                        prev =0;
                    }else{
                        map[prevIdx][i] = prev;
                        prev = map[j][i];
                        prevIdx--;
                    }
                }
                map[j][i] =0;
            }
            map[prevIdx][i] = prev;
            if(map[i][N-1] !=0) cnt++;
        }
        return cnt;
    }
}
