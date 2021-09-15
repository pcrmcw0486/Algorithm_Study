/*
Q1520 트리 Gold V
여러 생각이 많아서 하나로 정리하지 못해 생기는 문제점이 두드러지는 대표적인 문제인 듯 하다.
가장 효율적인 방법을 생각하려고 하다보니 여러 길에서 하나로 정하지 못하게 되고 그래서
시간이 오래 걸리는 문제임.

1. 트리를 구성하고 탐색하면서 삭제된 노드일 경우 가지 않으므로써 개수를 세는 방법 
2. 트리를 구성안하고 배열 만으로 해결하는 방법을 생각함.
결국
1. 트리를 구성한다.
2. 자신의 리프노드(child)개수를 저장한다.

나는 이렇게 하세요는 잘하는데, 알아서 해보세요에 좀 약한 편임.
내가 나한테 정해줘야할듯.
일단 하고 생각하자.
 */
package tmp;

import java.io.*;
import java.util.*;

public class Q1068 {
    static ArrayList<Integer>[] tree;
    static int cutNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int root = 0;
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int u = Integer.parseInt(st.nextToken());
            if (u == -1)
                root = i;
            else {
                tree[u].add(i);
            }
        }
        cutNode = Integer.parseInt(br.readLine());
        System.out.println(findLeaf(root));
    }

    private static int findLeaf(int cur) {
        int count = 0;
        if (cur == cutNode)
            return 0;
        if (tree[cur].size() == 0)
            return 1;
        for (int child : tree[cur]) {
            count += findLeaf(child);
        }
        return count == 0 ? 1 : count;
    }
}
