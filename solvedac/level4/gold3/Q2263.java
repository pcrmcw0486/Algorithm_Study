package solvedac.level4.gold3;
/*
2022.01.03
문제번호 : Q2263
이름 및 난이도 : 트리의 순회
문제이해 
-----------------
post order : LRV
in order :  LVR
preorder : VLR 
postorder에서 V를 찾고
해당V를 통해 inorder를 통해 L과 V를 나눈다.
이 떄 preorder에서는 V가 먼저 나오기 때문에
V를 출력하고 L과 R을 순차적으로 진행한다.
반복

[) () [] (] 구간에 따른 헷갈림 때문에 시간이 걸림.
종료조건 및 마지막 상태 때문에 헷갈림.
 >> 처음 내가 함수에서 하였던 상태에서 생각해야함.
  처음 내가 하고자 했던게 [ ) 였기 때문에.
  하나를 지칭하게 되는건 [a ,a+1)형태가 됨.
  이에 맞추어야함.
  이 때 size 는 a+1 - a = 1
  반구개는 사이즈 측정이 쉽네.
접근 방법 :
 divide & conquer
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q2263 {
    static int N;
    static int[] postorder;
    static int[] inorder;
    static int[] inIndex;
    static StringBuilder sb;

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
        inIndex = new int[N + 1];
        for (int i = 0; i < N; i++) {
            inIndex[inorder[i]] = i;
        }
        findPreorder(0, N, 0, N);
        System.out.print(sb.toString());
    }

    // 반구개 [start, end) V position = [end-1]
    // convergence > end-start = 1;
    public static void findPreorder(int postStart, int postEnd, int inStart, int inEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return;
        int V = postorder[postEnd - 1];
        sb.append(V).append(' ');
        // find LeftPreorder
        int midIdx = inIndex[V];
        int size = midIdx - inStart;
        findPreorder(postStart, postStart + size, inStart, midIdx);
        findPreorder(postStart + size, postEnd - 1, midIdx + 1, inEnd);
    }
}
