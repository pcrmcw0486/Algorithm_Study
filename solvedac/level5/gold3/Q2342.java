package solvedac.level5.gold3;

/*
2022.01.16
문제번호 : Q2342
이름 및 난이도 : Dance Dance Revolution Gold III
문제이해 
-----------------
중점을 0 1을 위 기준 반시계 2 3 4
두 발이 같은 지점에 허락되지 않는다. 다른 한발이 3이라면 그 발로 누른다.
 - 같은 지점을 한 번 더 누를 때 (1)
 -  중앙에서 다른 지점으로 움직일 때 (2)
 - 다른 지점에서 인접한 지점으로 움직일 때(3) (위->왼,오 왼->위,아래,아->왼오, 오->위아래)
 - 반대 방향으로 움직일 때 (4)(위<-> 아래, 오 <-> 왼)
제한 조건 : 
 - 각각의 방향이 주어지는 지시사항을 만족하는 최소 힘을 구하시오.
 - 0 은 수열의 마지막을 의미.
접근 방법 :
 - greedy한가? ( 즉, 지금 최소방향으로 움직였을 때 가 모였다면 총 합이 최소가 되는가)
   방향이 먼쪽을 선택했을 때 얻을 수 있는 이득이 있는가?
   없다. (항상 최선을 선택하는 것이 옳다)
   증명) how? 현재에 집중한다. X greedy 하지 않다.
   -> <- (3 + 3)   (다른거 4 + 1) = 작음.
   그렇다면? 
    - 생각할 수 있는 건, BFS 또는 dp같은데.
     상태공간 (Point, point) + weight 
     현재 시간에서의 상태에서 가장 짧은 힘

     '상태'가 들어가야함. leftFoot과 rightFoot.
     1 2 4일때 (1,4)일수도(2,4)일수도 있으니까...?
     (1,2) -> (1,4)일수도
     (1,2) -> (2,4) 일수도 있으니까.

     여기서도 5가 들어온다치면
     (1,4) -> (1,5)
     (1,4) -> (4,5)
     (2,4) -> (2,5)
     (2,4) -> (4,5) 
     여기서 중복을 제거해주어야한다.
     여기서 5가들어오면?
     (1,5) -> (1,5)
     (4,5) -> (4,5)
     (2,5) -> (2,5)
     여기서 2가 들어오면?
     (1,5) -> (1,2) 또는 (5,2)
     (4,5) -> (4,2) 또는 (5,2)
     (2,5) -> (2,5)
     
    dp로 뭔가 이 퍼지는 걸 방지해주는 결국 [정해진 영역]에서 돌고도는.
    dp(l,r) 상태공간이 l,r이였네.
    오른발 움직일때 왼발 움직일때가 아니라 (이건 상태 공간이 아니라 move 움직임 행동이다.)
    오른발 위치 왼발 위치로 갔어야하네........(이게 문제였었거든요..)

    제한된 어떤 상태를 돌아다니는. 상태의 이전이 자주 일어나는.
    반복 ( dp memoization)

    총 상태공간은 l 0~4
                  r 0~4
        여기서 어떤 밟아야 하는 x가 들어오면 이상태는
        l -> x 또는 r->x
        그렇다면 [l][r] -> [x][r] 또는 [l][x]
        앞으로 전이됨....
        초기상태는 0 0 으로 정해져있고. 

        ( 0 0 ) - >  (1 ,0)
                    (0,1)
        2   ->  (1,2)   ( 2,0)
                (2,1)    (0,2)
        즉 어떤 상태에 a가 들어왔다면
        그 전 상태에서 a를 밟고 있는 것들 중 
        
    현재 상태 기준 
    왼발을 움직일수도 있고, 오른발을 움직일수도 있다.

*/

import java.io.*;
import java.util.*;

public class Q2342 {
    static int[] nums;
    static int size;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        size = nums.length - 1; // 끝에는 0이 있음.
        dp = new int[size][5][5];
        System.out.println(dfs(0, 0, 0));

    }

    public static void solution1() {
        dp = new int[size][5][5];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], 987654321);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < size; i++) {
            int nxt = nums[i];
            // 모든 case
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    // 같은 위치 반복 X
                    // 오른발을 nxt로 움직임.
                    int cur = dp[i][l][r];
                    if (l != nxt) {
                        dp[i + 1][l][nxt] = Math.min(dp[i + 1][l][nxt], cur + going(r, nxt));
                    }
                    // l을 nxt로 움직임.
                    if (r != nxt) {
                        dp[i + 1][nxt][r] = Math.min(dp[i + 1][nxt][r], cur + going(l, nxt));
                    }
                }
            }
        }
        int ans = 987654321;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[size - 1][i][j]);
            }
        }
        System.out.println(ans);
    }

    public static int getPower(int curDir, int nxtDir) {
        if (curDir == nxtDir)
            return 1;
        else if (curDir == 0)
            return 2;
        // 반대 방향
        else if ((curDir + 1) % 4 == nxtDir - 1)
            return 4;
        else
            return 3;
    }

    public static int dfs(int idx, int left, int right) {
        if (idx == size)
            return 0;
        if (dp[idx][left][right] != 0)
            return dp[idx][left][right];

        int goLeft = dfs(idx + 1, nums[idx], right) + going(left, nums[idx]);
        int goRight = dfs(idx + 1, left, nums[idx]) + going(right, nums[idx]);
        dp[idx][left][right] = Math.min(goLeft, goRight);

        return dp[idx][left][right];
    }

    public static int going(int start, int end) {
        if (start == 0)
            return 2;
        else if (Math.abs(start - end) == 2)
            return 4;
        else if (start == end)
            return 1;
        else
            return 3;
    }

}
