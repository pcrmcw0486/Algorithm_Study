package programmers.kakao.Blind2022;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
입차, 출차 기록 주차요금 계산
기본 시간, 기본 요금 단위시간 단위요금

어떤 차량이 입차 후 출차내역이 없다면 23:59에 출차된 것 간주
누적 주차 시간이 기본 시간 이하라면, 기본 요금을 청구
누적 주차 시간이 기본 시간 초과면 추가. 올림해서 계산

대충 뭐 27분.
* */
public class Solution3 {
    public static void main(String[] args) {
        int[] fees = new int[]{1, 461, 1, 10};
        String[] records = {
                "00:00 1234 IN"
        };
        int[] solution = solution(fees, records);
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }

    static int TIME_LIMIT = 1439;
    private static int[] solution(int[] fees, String[] records) {
        StringTokenizer st;
        TreeMap<Integer, Record> recordByCar = new TreeMap<>();

        for (String record : records) {
            st = new StringTokenizer(record);
            String time = st.nextToken();
            int carNum = Integer.parseInt(st.nextToken());
            boolean isParked = st.nextToken().equals("IN");
            if (!recordByCar.containsKey(carNum)) {
                recordByCar.put(carNum, new Record());
            }
            Record beforeRecord = recordByCar.get(carNum);
            if (isParked) {
                //입차 과정
                beforeRecord.getIn(time);
            }else{
                //출차 과정
                beforeRecord.getOut(time);
            }
        }

        int[] ans = new int[recordByCar.size()];
        int idx =0;
        for (Record record : recordByCar.values()) {
            int totalTime = record.accumulatedTime;
            if (record.isParked) {
                totalTime += TIME_LIMIT - record.startTime;
            }
            if (totalTime < fees[0]) {
                ans[idx++] = fees[1];
            }else{
                int bonusTime = totalTime-fees[0];
                int overTime =bonusTime/fees[2];
                if (bonusTime % fees[2] != 0) {
                    overTime++;
                }

                ans[idx++] = fees[1] +
                        overTime * fees[3];
            }
        }

        return ans;
    }

    public static class Record{
        int carNum;
        int accumulatedTime;
        int startTime;
        boolean isParked;

        Record() {
            accumulatedTime = 0;
            startTime = -1;
            isParked = false;
        }

        public void getIn(String time) {
            startTime = translateTime(time);
            isParked = true;
        }

        public void getOut(String time) {
            int endTime = translateTime(time);
            accumulatedTime += endTime - startTime;
            startTime = 0;
            isParked = false;
        }

        public int translateTime(String time) {
            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            return hour * 60 + minute;
        }
    }
}
