package RHS_FC.class07_topologicalSort;

/*
다리를 지나는 트럭
트럭 여러대가 강을 가로지르는 일차선 다리를 정해진 순서대로
모든 트럭이 건너는데 몇초가 걸림?

무게를 10kg까지 견딘다
트럭은 2대 까지 가능하다.
7 4 5 6
7
4
4 5
5
6 >> 8초
일단 naiive하게 생각하면
다리 위를 하나 두고 위의 무게를 추적하면서
length 범위동안 가도록.
7 > 2초
4 5 > 1 1 1 
6 > 2 초
마지막 X 8초
시간계산을 어떻게 할것인가.
 */
public class Solution3 {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 100;
        int[] truck_weights = { 7, 4, 5, 6 };
        int a = solution(bridge_length, weight, truck_weights);
        System.out.println(a);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int[] time = new int[truck_weights.length];
        int endIndex = 0;
        int lastTime = 0;
        int onWeight = 0;
        for (int i = 0; i < truck_weights.length; i++) {
            while (endIndex < truck_weights.length) {
                if (onWeight + truck_weights[endIndex] > weight)
                    break;
                onWeight += truck_weights[endIndex];
                time[endIndex] = lastTime;
                lastTime++;
                endIndex++;
            }
            lastTime = time[i] + bridge_length;
            onWeight -= truck_weights[i];
        }
        return time[truck_weights.length - 1] + bridge_length + 1;

    }

}
