package DayByDay._0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2250
 * @문제이름 : 트리의 높이와 너비
 * @난이도 : Gold II
 * @date : 2022-04-16 오전 6:25
 * @author : pcrmcw0486
 * @문제이해
 * 이진트리에서 같은 레벨에 있는 노드는 같은 행에 위치한다.
 * 한 열에는 한 노드만 존재한다.
 * leftsubtree는 해당 노드보다 왼쪽열에 위치하고 right는 해당 오른쪽 열에 위치한다.
 * 비어있는 열은 없다.
 * 너비가 가장 넓은 레벨과 그 레벨의 너비를 계산하려고 한다.
 * 15 , 3 => 13
 * 19, 2 || 1, 18 -> 18
 * 4, 16 > 13
 * 가장 넓은 레벨과 그 레벨의 너비를 출력하시오.
 *  어떤 node의 왼쪽에는 왼쪽 서브트리 개수 만큼 와야하고 오른쪽 서브트리는 오른쪽 서브트리 개수
 *  level은 신경쓰지 않고 넓이, 위치만 계산한다.
 *  LVR로 순회하면서 번호 매겨주면된다. 이후 bfs levelorder로 계산한다.
 *  루트는 항상 1인가요? 아닌거 같은데요.
 * @알고리즘
 * @접근방법

*/
public class Q2250 {
    static int LEFT =0;
    static int RIGHT = 1;
    static int[][] tree;
    static int[] colNum, parent;
    static int index, N;
    public static void main(String[] args) throws IOException {
        input();
        int root = findRoot();
        inorder(root);
        findBreadth(root);

    }

    private static void findBreadth(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        int maxBreadth = 0;
        int selectedLevel=0;
        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelMax =0;
            int levelMin = N+2;
            for (int i = 0; i < levelSize; i++) {
                int nodeNumber = queue.poll();
                levelMax = Math.max(levelMax, colNum[nodeNumber]);
                levelMin = Math.min(levelMin, colNum[nodeNumber]);
                if(tree[nodeNumber][LEFT] != -1) queue.add(tree[nodeNumber][LEFT]);
                if(tree[nodeNumber][RIGHT] != -1) queue.add(tree[nodeNumber][RIGHT]);
            }
            level++;
            if (levelMax - levelMin + 1 > maxBreadth) {
                selectedLevel = level;
                maxBreadth = levelMax - levelMin + 1;
            }
        }
        System.out.println(selectedLevel + " " + maxBreadth);
    }

    private static int findRoot() {
        int root = -1;
        for (int i = 1; i < N + 1; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }
        return root;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];
        colNum = new int[N + 1];
        parent = new int[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());
            if(leftChild != -1) parent[leftChild] = nodeNum;
            if(rightChild != -1) parent[rightChild] = nodeNum;
            tree[nodeNum][LEFT] = leftChild;
            tree[nodeNum][RIGHT] = rightChild;
        }
        index =1;
    }

    private static void inorder(int nodeNum) {
        if(nodeNum == -1) return;
        inorder(tree[nodeNum][LEFT]);
        colNum[nodeNum] = index++;
        inorder(tree[nodeNum][RIGHT]);
    }
}
