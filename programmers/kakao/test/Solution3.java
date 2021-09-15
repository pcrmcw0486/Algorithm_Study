package programmers.kakao.test;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        solution.solution(new int[] { 120, 0, 60, 591 },
                new String[] { "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN" });
    }

    class CarStatus {
        int carNum;
        boolean isIn = false;
        int accumulateTime = 0;
        int startTime = 0;
        int totalCost = 0;

        CarStatus(int carNum, int startTime) {
            this.carNum = carNum;
            this.startTime = startTime;
            isIn = true;
        }

        public void out(int endTime) {
            isIn = false;
            this.accumulateTime += endTime - startTime;
            startTime = 0;
        }

        public void update(int startTime) {
            isIn = true;
            this.startTime = startTime;
        }

        public int calFee(int baseTime, int baseCost, int unitTime, int unitCost) {
            if (isIn)
                accumulateTime += 1439 - startTime;
            System.out.println("carNum" + this.carNum + "accumulateTime" + this.accumulateTime);

            if (accumulateTime <= baseTime) {
                return baseCost;
            } else {
                return baseCost
                        + (int) Math.ceil(((double) accumulateTime - (double) baseTime) / (double) unitTime) * unitCost;
            }

        }
    }

    // 고려사항 같은 차량이 두번 이상 왔다 간 경우 틀렸을 때 생각.
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int baseTime = fees[0];
        int baseCost = fees[1];
        int unitTime = fees[2];
        int unitCost = fees[3];
        Map<Integer, Integer> carList = new HashMap<Integer, Integer>();
        ArrayList<CarStatus> statusList = new ArrayList<CarStatus>();
        Queue<int[]> costList = new PriorityQueue<int[]>((a, b) -> (a[0] - b[0]));
        int carIndex = 0;
        for (String r : records) {
            String[] tmp = r.split(" ");
            int time = makeTime(tmp[0]);
            int carNum = Integer.parseInt(tmp[1]);
            String cmd = tmp[2];
            switch (cmd) {
                case "IN":
                    if (!carList.containsKey(carNum)) {
                        carList.put(carNum, carIndex++);
                        statusList.add(new CarStatus(carNum, time));
                    } else {
                        int idx = carList.get(carNum);
                        statusList.get(idx).update(time);

                    }
                    break;
                case "OUT":
                    int index = carList.get(carNum);
                    statusList.get(index).out(time);
                    break;
            }
        }
        // 차들 계산 총 계산
        for (CarStatus status : statusList) {
            int carNum = status.carNum;
            int totalFee = status.calFee(baseTime, baseCost, unitTime, unitCost);
            costList.add(new int[] { carNum, totalFee });
        }

        answer = new int[costList.size()];
        int count = 0;
        while (!costList.isEmpty()) {
            answer[count++] = costList.poll()[1];
            System.out.println(answer[count - 1]);
        }
        return answer;
    }

    public int makeTime(String s) {
        int time = 0;
        String[] tmp = s.split(":");
        time = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
        return time;
    }
}
