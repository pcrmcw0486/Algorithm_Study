// /*
// *** 미확인 도착지 Gold II ***
// *** 접근 방법 ***
// - S에서 출발해서
// - g-h 사이를 무조건 거쳐서
// - 목적지에 도착해야함.
// 정리) s-> (g, h) -> 목적지
// 가 최단 거리인가?
// 또는 s에서 목적지까지가는 최단 거리 중
// g, h를 거치는가?
// s, g, h에 대하여 dijkstra를 통해서 길이를 구한다.
// - s -> (g h) -> 목적지
// s-> (h g) > 목적지
// 의 최소값이 s > 목적지의 최단거리와 길이가
// 같은가 에 대한 문제이다.

// + 한 간선만 확인하기 좋은 방법?
// 짝수와 홀수 이용하여 지나야하는 간선을 홀수로 만들고
// 시작점으로부터 해당 도착지까지의 최단거리가 홀수라면 무조건 홀수인 간선을 지났다는 뜻이므로
// 체크가 가능해진다.

// *** 활용 알고리즘 및 활용 방법 ***
// - dijkstra?
// - s, g, h에 대하여 길이를 구한다.
// - 목적지도 PQ에 담는다.
// - 인접리스트를 활용하여 그래프를 구현한다.
// - dijkstra를 총 세번하여 길이를 구한다.
// - 계산한다.
// *** 문제의 조건 ***
// - 예술가 한쌍이 이동(두명)
// - 어디로 가고 있는지 알아낸다
// - S지점에서 출발
// - 목적지 후보 중 하나가 목적지
// - 우회하지 않고 최단거리로 감.
// - 그들이 g와 h 교차로 사이의 도로를 지나갔다

// *** 입출력 ***
// - Test case 개수
// - n, m, t (교차로 V, 도로 E, 목적지 후보 target의 개수)
// - s, g, h (예술가 출발지, g<->h 힌트, 사이의 도로)
// - u, v, w (양방향 도로)
// - t개의 각 줄마다 정수 X의 목적지 후보
// 출력 :
// 목적지 후보들 중 불가능한 경우를 제외한 목적지들을
// 공백으로 분리시킨 오름차순의 정수
// */
// package graph.ShortestPath;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.PriorityQueue;
// import java.util.StringTokenizer;
// import java.util.stream.Stream;

// public class Q9370 {
// static int INF = Integer.MAX_VALUE;

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// int T = Integer.parseInt(br.readLine());
// StringBuilder sb = new StringBuilder();

// while (T-- > 0) {
// StringTokenizer st = new StringTokenizer(br.readLine());
// int V = Integer.parseInt(st.nextToken());
// int E = Integer.parseInt(st.nextToken());
// int target_count = Integer.parseInt(st.nextToken());

// int[] starting_point = Stream.of(br.readLine().split("
// ")).mapToInt(Integer::parseInt).toArray();
// int[][] dist = new int[3][];

// ArrayList<Node>[] graph = new ArrayList[V];
// PriorityQueue<Integer> target_list = new PriorityQueue<>();
// for (int i = 0; i < V; i++)
// graph[i] = new ArrayList<Node>();

// for (int i = 0; i < E; i++) {
// st = new StringTokenizer(br.readLine());
// int u = Integer.parseInt(st.nextToken()) - 1;
// int v = Integer.parseInt(st.nextToken()) - 1;
// int weight = Integer.parseInt(st.nextToken());
// graph[u].add(new Node(v, weight));
// graph[v].add(new Node(u, weight));
// }

// for (int i = 0; i < target_count; i++)
// target_list.offer(Integer.parseInt(br.readLine()) - 1);

// // data 1step 다 받고 거리 생성
// for (int i = 0; i < starting_point.length; i++) {
// dist[i] = dijkstra(graph, starting_point[i] - 1, V);
// }

// // 생성된 거리를 통해 체크하기 (target 오름차순 순으로 검사)
// while (!target_list.isEmpty()) {
// int target = target_list.poll();
// int result = INF;
// int g = starting_point[1] - 1;
// int h = starting_point[2] - 1;
// if (dist[0][g] != INF || dist[1][h] != INF || dist[2][target] != INF)
// result = Math.min(result, dist[0][g] + dist[1][h] + dist[2][target]);
// if (dist[0][h] != INF || dist[2][g] != INF || dist[1][target] != INF)
// result = Math.min(result, dist[0][h] + dist[2][g] + dist[1][target]);
// if (result == dist[0][target])
// sb.append(target + 1).append(" ");
// }
// sb.append("\n");

// }
// System.out.print(sb.toString());
// }

// public static int[] dijkstra(ArrayList<Node>[] graph, int start, int V) {
// int[] dist = new int[V];
// boolean[] visit = new boolean[V];
// PriorityQueue<Node> pq = new PriorityQueue<>();
// Arrays.fill(dist, INF);

// dist[start] = 0;
// pq.offer(new Node(start, 0));

// while (!pq.isEmpty()) {
// Node now = pq.poll();
// if (!visit[now.to]) {
// visit[now.to] = true;
// for (Node next : graph[now.to]) {
// if (dist[now.to] + next.weight < dist[next.to]) {
// dist[next.to] = dist[now.to] + next.weight;
// pq.offer(new Node(next.to, dist[next.to]));
// }
// }
// }
// }
// return dist;
// }

// class Node implements Comparable<Node> {
// public int to;
// public int weight;

// public Node(int to, int weight) {
// this.to = to;
// this.weight = weight;
// }

// @Override
// public int compareTo(Node o) {
// // TODO Auto-generated method stub
// return weight - o.weight;
// }
// }
// }
