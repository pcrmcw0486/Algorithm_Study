package solvedac.level5.platinum5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @문제번호 : Q14939
 * @문제이름 : 불 끄기
 * @난이도 : Platinum V
 * @date : 2022-03-08 오후 3:32
 * @author : pcrmcw0486
 * @문제이해
 * 전구 100개가 정사각형 모양으로 늘어서 있다.
 * 스위치를 누르면 그 전구와 위, 아래, 왼쪽, 오른쪽 전구의 상태가 바뀐다.
 * 전구 100개의 상태가 주어지면 모든 전구를 끄기 위해 최소한으로 눌러야하는 스위치의 개수
 * @알고리즘
그리디, 브루트 포스...
일반 브루트 포스로 생각하면 2^100가지 경우가 나온다.
몇가지 생각할 것들이 좀 있다.
버튼을 누르는 것에 순서가 있는가? 하나의 버튼이 두번씩 눌린다면 그렇다 이지만 한번만 눌린다면 그렇지 않다.
모든 전구를 끄는데에 눌러서 끈 전구를 다시 킬 이유가 없다. 한 버튼이 두번이상 눌리지 않는다 라

라는 개념에 접근하기 위해 일단 기본적으로
모두 눌러봐야되지 않나? 라는 생각이 기본적으로 들어야 한다. 줄여나가야함.
다 눌러보는거 OK 근데 그냥 하면 너무 큰데.
다른건 모르겟고 하나에 대해서만 처리를 하자.
윗줄만 우리가 다 꺼주고 나도 내 밑에서 다 꺼주면 결국 다 꺼진다. 라는 개념으로 접근해야함.
그렇다면 한 버튼이 두번 이상 눌러서 최소한으로 꺼지는 경우가 있는가?
윗줄에 종속적이라면 두번 이상 눌리지 않는다.

처음 윗줄에 대해서만 완탐을 하고 나머지는 그리디하게 진행한다.
 * @접근방법

*/
public class Q14939 {
    static char[][] map, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[10][10];
        tmp = new char[10][10];
        for (int i = 0; i < 10; i++)
            map[i] = br.readLine().toCharArray();
        int ans = 101;
        int cnt =0;
        //총 2^10 가지 수
        for (int cand = 0; cand < 1024; cand++) {
            copyMap(map, tmp);
            cnt =0;
            //첫째줄 push
            for (int i = 0; i < 10; i++) {
                if ((cand & (1 << i)) != 0) {
                    push(0,i);
                    cnt++;
                }
            }
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (tmp[i - 1][j] == 'O') {
                        push(i, j);
                        cnt++;
                    }
                }
            }
            boolean isComplete = true;
            for (int i = 0; i < 10; i++) {
                if (tmp[9][i] == 'O') {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                ans = Math.min(ans, cnt);
            }
        }
        System.out.println(ans==101?-1:ans);
    }
    public static void change(int x, int y) {
        tmp[x][y] = tmp[x][y]== '#' ? 'O' : '#';
    }
    public static void push(int x, int y) {
        change(x, y);
        if(x-1 >=0) change(x-1, y);
        if(y-1>=0) change(x, y - 1);
        if(x+1<10) change(x + 1, y);
        if(y+1<10) change(x, y + 1);
    }

    public static void copyMap(char[][] src, char[][] dest) {
        for (int i = 0; i < 10; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, 10);
        }
    }
}
