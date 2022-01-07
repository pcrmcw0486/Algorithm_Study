package solvedac.level4.gold3;
/*
2022.01.07
문제번호 : Q2263_Review
이름 및 난이도 : 트리의 순회 Gold II
문제이해 
-----------------
1~n 인오더와 포스트 오더가 주어질 때 프리오더를 구하시오
preorder : VLR
inorder : LVR
postorder : LRV

Point는 트리의 사이즈에 따른다.
inorder의 쓰임은 사이즈를 계산하기 위한 두개의 포인터가 필요하고 (범위설정 영향)
postorder의 쓰임은 값을 V에서 뽑아내기 위해 필요하다.(값)
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q2263_Review {
    static int N;
    static int inorder[];
    static int postorder[];
    static StringBuilder sb;
    static int idx[];

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void solve() {
        initData();
        findPreorder(0, N, 0, N);
        System.out.println(sb.toString());
    }

    public static void initData() {
        idx = new int[N + 1];
        for (int i = 0; i < N ; i++){ 
            idx[inorder[i]] = i;
        }
    }

    // 반개구간 [ ) >> size를 구하기 편리함 [a,b-1] == (a,b) size: b-a
    public static void findPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        // size가 하나일 때 (TEST) 에서 끝내는 것이 맞지만,
        // Right 또는 LEFT가 없는 경우를 보면 여기서 끝나지 않음.
        // if (postEnd - postStart == 1)
        // return;
        if (postEnd <= postStart)
            return;

        // find Value from postorder
        int V = postorder[postEnd - 1];
        sb.append(V).append(" ");
        int dividePoint = -1;

        // find divide Size from inorder >> 시간을 많이 잡아먹는다.
        // Algorithm Skill (미리 구해놓기)
        // for (int i = 0; i < N; i++) {
        // if (inorder[i] == V) {
        // dividePoint = i;
        // break;
        // }
        // }
        dividePoint = idx[V];

        // inorder의 경우 dividePoint를 기준으로 나뉘게 된다.
        // postorder의 경우 inorder에서 구한 사이즈를 기준으로 나뉘게 된다.
        int size = dividePoint - inStart; // leftSize

        // LEFT
        findPreorder(inStart, dividePoint, postStart, postStart + size);
        // RIGHT
        findPreorder(dividePoint + 1, inEnd, postStart + size, postEnd - 1);
    }

}
