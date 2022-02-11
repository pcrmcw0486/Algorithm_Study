/*
https://www.acmicpc.net/problem/1197
*** 최소 스패닝 트리 Gold IV***
*** 접근 방법 ***
단순 최소 스패닝 트리를 구하는 문제이다.
문제 조건에서 간과하면 안되는 부분이 V=1 E=1이다.
즉 주어지는 간선 정보에서 A와 B는 같을 수 있다. 모두 연결되어 있지만,
무조건 N-1이 아니라는 것. 
또한 같은 간선이 여러번 주어지지 않는다고 되어 있지 않음(여러번 나올 수도)
사이클이 나오더라도, 그게 모두 음수면?
예시로 주어진 
1 2 1                              1
2 3 2                             /  \
1 3 3 에 대해                    2  - 3
만약 1 2 -1
     2 3 -2
     1 3 -3 이면? -6이 맞는거 아닌가.
그렇다면  내 생각은 다음과 같음
 > 1. 간선을 오름차순 정렬한다.
   2. 음수인 간선들에 대해 모두 추가해준다.
     2-1. 추가하면서 모두 같은 MST 집합에 정점을 추가해준다.
   3. 0인 간선은 가중치에 영향을 주지 않으므로 패스한다.
   4. 그 이후 MST집합에 속해있지 않은 정점들에 대해 정렬되어 있는 간선에서 
      모두 속할 때 까지 더한다.
기본적으로 Kruskal Algorithm을 사용하되, 종료 조건은 Prim과 마찬가지로
생각한다.
(핵심 POINT) 이렇게 저렇게 많이 데여서 생각이 많았는데,
             이러나 저러나 tree라서 cycle은 없어야됨!
    => 그래서 여기서 변경한다면 selectedVertex에 대해서 selectedEdge로 변경 후 
     V-1개 선택시 종료하면됨.
*** 알고리즘 자료구조 스킬 ***
MST, Kruskal sparse matrix로 생각.
*** 문제 조건 ***
그래프가 주어졌을 때 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
* 최소 스패닝 트리는 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 
  가중치 합이 최소인 트리를 말한다.
@input
    V : 정점개수 (1<=V<=10_000)
    E : 간선개수 (1<=E<=100_000) V기준 E가 V^2에 근사할 때 dense함.
     E개의 줄에 대해 A, B, C가 주어짐. A정점과 B정점 가중치 C인 간선.
     C는 음수일 수도 있으며 절댓값이 -1_000_000 < C < 1_000_000
     그러면 최대 간선 길이는 (10_000-1) * 1_000_000 = 10_000_000_000
     이기 때문에 (마찬가지 최소도 절댓값만 다름) int형으로 처리가 가능함.
- 그래프 정점은 1번부터 V까지 번호가 매겨져 있고, 임의의 두 정점사이에는 경로가 존재한다.
@output
 최소 스패닝 트리의 가중치를 출력한다.
 */
package SolveByCategory.tree.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge1 implements Comparable<Edge1> {
    int A;
    int B;
    int weight;

    public Edge1(int A, int B, int weight) {
        this.A = A - 1;
        this.B = B - 1;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge1 o) {
        return this.weight - o.weight;
    }
}

public class Q1197 {
    static int[] root;
    static int[] rank;
    static int[] elementCount;
    static int selectedVertex;
    static PriorityQueue<Edge1> pq;
    static int V;
    static int E;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge1(a, b, weight));
        }
        while (!pq.isEmpty()) {
            Edge1 edge = pq.poll();
            // System.out.println(edge.A + " " + edge.B + " " + edge.weight);
            if (union(edge.A, edge.B, edge.weight)) {
                // System.out.println((edge.A + 1) + " " + (edge.B + 1) + " " + edge.weight);
                result += edge.weight;
            }
            if (selectedVertex == V)
                break;
        }
        System.out.println(result);

    }

    public static void init() {
        root = new int[V];
        rank = new int[V];
        elementCount = new int[V];
        result = 0;
        pq = new PriorityQueue<>();
        selectedVertex = 0;
        for (int i = 0; i < V; i++) {
            root[i] = i;
            rank[i] = 1;
            elementCount[i] = 1;
        }
    }

    public static int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    public static boolean union(int x, int y, int weight) {
        int rootX = find(x);
        int rootY = find(y);
        int tmp = 0;
        if (rootX == rootY) {
            return false;
        }
        if (rank[rootY] > rank[rootX]) {
            tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }
        root[rootY] = rootX;
        elementCount[rootX] += elementCount[rootY];
        elementCount[rootY] = 1;
        selectedVertex = Math.max(selectedVertex, elementCount[rootX]);
        if (rank[rootY] == rank[rootX])
            rank[rootX]++;
        return true;
    }
}
