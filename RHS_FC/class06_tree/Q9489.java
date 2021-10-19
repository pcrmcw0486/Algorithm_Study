package RHS_FC.class06_tree;

/*
사촌 Gold IV
증가하는 정수 수열을 이용하여 트리를 만드는 방법
 > 첫번째 정수는 트리의 루트노드이다.
 > 다음 등장하는 연속된 수의 집합은 루트의 자식.
    > 이 집합의 포함되는 수의 첫번째 수는 항상 루트노드 + 1 보다 크다.
 > 그 다음 부터는 모든 연속된 수의 집합은 아직 자식이 없는 노드의 자식이된다.
   >> 그러한 노드가 여러가지인 경우 가장 작은 수를 가지는 노드의 자식이됨.
 > 집합은 연속하지 않는 곳에서 구분된다. 아 구분된다.
 1 3 4 5 8 9 15 30 31 32
 1(첫번재 정수는 트리의 루트노드이다. )
 그다음 연속 (3, 4, 5)
 8 9 / 15 / 30 31 32 ㅇㅋㅇㅋ
 부모가 다르지만 두 부모가 형제일때 두 노드를 사촌이라고 함.
 수열 특정 노드 번호 k가 주어질 때 k의 사촌 수를 구하시오.

 */
import java.io.*;
import java.util.*;

public class Q9489 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    while (true) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      if (n == 0 && k == 0)
        break;
      int levels[] = new int[n + 1];
      levels[0] = -1;
      int level = -1;
      int targetIdx = 0;
      int prev = -1;
      st = new StringTokenizer(br.readLine());
      for (int i = 1; i < n + 1; i++) {
        int num = Integer.parseInt(st.nextToken());
        if (num == k)
          targetIdx = i;
        if (num != prev + 1)
          ++level;
        levels[i] = level;
        prev = num;
      }
      int result = 0;
      for (int i = 1; i < n + 1; i++) {
        if (levels[i] != levels[targetIdx] && levels[levels[i]] == levels[levels[targetIdx]])
          result++;
      }
      sb.append(result).append('\n');
    }
    System.out.print(sb);
  }
}
