/*
https://www.acmicpc.net/problem/5639
*** 이진 검색 트리 Silver I***
*** 접근 방향 ***
이진검색트리(BST)에 대한 이해를 바탕으로 문제를 풀어나간다.
leftChild subTree < Node < rightChild subTree를 만족한다.
전에 풀었던거는 100_000 인데 5s였음.
BST는 inorder시 오름차순 순으로 나오게 되어 있음.
이진 탐색 트리의 특성을 이용하면 Left와 Right를 구분하기 용이하다.
post order가 LRV이고 preorder이 VLR임.
preorder 중간에 노드 값 V기준 커지는 순간부터 R이다 >  V |  L |  R  |
V를 기록하고 R과 L 순서로 뒤에서 부터 기록해둔다.
                      [L  LV기록 ][R RV기록]   V
*** 사용 알고리즘 자료구조 스킬 ***
이진검색트리, 트리탐색. 
반복적인 문제를 크기에 따라 나누어 푼다. Divide and Conquer

++ 무한 입력이라 제출때에는 EOF가 들어와서 null처리가 되는데
아닐 때는 Ctrl + Z를 통해 했다.
*** 문제 조건 ***
이진검색트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램작성.
@time : 1s
@memory 256MB
@input 
    전위 순회한 결과가 주어짐. 같은 키를 가지는 노드는 없음(중복X)
    한줄에 하나씩 노드 수는 10_000 개 이하이다.
    @x_i : 0<= x_i < 1_000_000

 */
package SolveByCategory.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q5639 {
    public static int length;
    public static int[] preorderList;
    public static Stack<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        result = new Stack<>();
        preorderList = new int[10000];
        length = 0;
        String input = "";
        while ((input = br.readLine()) != null) {
            preorderList[length++] = Integer.parseInt(input);
        }
        solve(0, length - 1);
        while (!result.isEmpty()) {
            sb.append(result.pop()).append("\n");
        }
        System.out.print(sb);
    }

    public static void solve(int startIdx, int endIdx) {
        if (startIdx > endIdx)
            return;
        int root = preorderList[startIdx];
        result.push(root);
        int findIdx = -1;
        for (int i = startIdx; i < endIdx + 1; i++) {
            if (preorderList[i] > root) {
                findIdx = i;
                break;
            }
        }
        // findIdx == -1이라면 Right가 없음.
        if (findIdx == -1) {
            // Left
            solve(startIdx + 1, endIdx);
        } else {
            // Right Left 순서
            solve(findIdx, endIdx);
            solve(startIdx + 1, findIdx - 1);
        }
    }
}
