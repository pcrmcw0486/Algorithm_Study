/*
https://www.acmicpc.net/problem/1991
*** 트리 순회 Silver I ***
*** 접근 방법 ***
단순 이진트리 탐색 방법 연습 문제임
트리 탐색 시간 복잡도
*** 사용 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***
이진트리를 입력받아 preorder, inorder postorder한 결과 출력
@Input
    N : 노드 개수 (1<=N<=26)
    N개 줄의 걸쳐 각 노드와 그의 왼쪽, 오른쪽 자식 노드가 주어짐.
    항상 A가 루트 노드가 되고 자식노드가 없다면 . 으로 표현
@output
    전위
    중위
    후위  N개의 알파벳을 공백 없이 출력.
 */
package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1991 {
    static int[][] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        tree = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0) - 'A';
            int leftChild = st.nextToken().charAt(0) - 'A';
            int rightChild = st.nextToken().charAt(0) - 'A';
            tree[parent][0] = leftChild;
            tree[parent][1] = rightChild;
        }
        preorder(0);
        sb.append("\n");
        inorder(0);
        sb.append("\n");
        postorder(0);
        System.out.println(sb.toString());
    }

    // VLR
    public static void preorder(int root) {
        if (root < 0)
            return;
        sb.append((char) (root + 'A'));
        preorder(tree[root][0]);
        preorder(tree[root][1]);
    }

    // LVR
    public static void inorder(int root) {
        if (root < 0)
            return;
        inorder(tree[root][0]);
        sb.append((char) (root + 'A'));
        inorder(tree[root][1]);
    }

    // LRV
    public static void postorder(int root) {
        if (root < 0)
            return;
        postorder(tree[root][0]);
        postorder(tree[root][1]);
        sb.append((char) (root + 'A'));
    }
}
