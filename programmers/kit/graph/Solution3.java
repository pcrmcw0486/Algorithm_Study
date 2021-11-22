package programmers.kit.graph;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        int[] arrows = { 6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0 };
        System.out.println(solution(arrows));
    }

    public static int solution(int[] arrows) {
        int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
        int cnt = 0;
        Point pointHC = new Point(0, 0);

        // 노드와 해당 노드에 연결된 노드(엣지)관리를 위한 hashMap
        HashMap<Point, ArrayList<Point>> visited = new HashMap<>();
        for (int i = 0; i < arrows.length; i++) {
            for (int j = 0; j < 2; j++) { // scale-up ex) 모래시계 모양
                Point newPointHC = new Point(pointHC.x + dir[i][0], pointHC.y + dir[i][1]);
                // 처음 방문하는 경우
                if (!visited.containsKey(newPointHC)) {
                    visited.put(newPointHC, makeEdgeList(pointHC));
                    if (visited.get(pointHC) == null) {
                        visited.put(pointHC, makeEdgeList(newPointHC));
                    } else {
                        visited.get(pointHC).add(newPointHC);
                    }
                    // 재방문의 경우 // 이전에 왔던 간선으로 온다면 X
                    // 새로운 간선으로 다시 방문할 경우 닫힌도형생성

                } else if (visited.containsKey(newPointHC) && !visited.get(newPointHC).contains(pointHC)) {
                    visited.get(newPointHC).add(pointHC);
                    visited.get(pointHC).add(newPointHC);
                    cnt++;
                }
                pointHC = newPointHC;
            }
        }
        return cnt;
    }

    public static ArrayList<Point> makeEdgeList(Point pointHC) {
        ArrayList<Point> edge = new ArrayList<>();
        edge.add(pointHC);
        return edge;
    }

    static class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // HashMap안에서 equals로 hashCode로 비교하게 되는데
        // 따로 정해주지 않으면 주소값으로 비교하기 때문에
        // 주소값 A Point(0,0)과
        // 새로 들어온 주소값 B Point(0,0)을
        // 다르게 처리하게 된다.
        /*
         * public boolean containsKey(Object key) { return getNode(hash(key), key) !=
         * null; }
         */
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
        }
    }
}
