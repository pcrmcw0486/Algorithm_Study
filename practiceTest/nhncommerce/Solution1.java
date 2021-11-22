package practiceTest.nhncommerce;

import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        String[] logs = { "0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90",
                "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90" };
        solution(logs);
    }

    public static String[] solution(String[] logs) {
        Map<String, LogList> logMap = new HashMap<String, LogList>();
        for (String s : logs) {
            String[] log = s.split(" ");
            String name = log[0];
            int number = Integer.parseInt(log[1]);
            int score = Integer.parseInt(log[2]);
            if (!logMap.containsKey(name)) {
                LogList loglist = new LogList(name);
                loglist.psMap.put(number, score);
                logMap.put(name, loglist);
            } else {
                logMap.get(name).psMap.put(number, score);
            }
        }
        PriorityQueue<LogList> pq = new PriorityQueue<LogList>();

        ArrayList<String> answer = new ArrayList<String>();
        LogList prevStudent = pq.poll();
        while (!pq.isEmpty()) {
            LogList curStudent = pq.poll();
            if (prevStudent.getSize() < 5) {
                prevStudent = curStudent;
                continue;
            }
            if (prevStudent.getSize() == curStudent.getSize()) {
                HashMap<Integer, Integer> curMap = curStudent.psMap;
                for (Map.Entry<Integer, Integer> entry : prevStudent.psMap.entrySet()) {
                    Integer key = entry.getKey();
                    Integer val = entry.getValue();
                    if (curMap.containsKey(key)) {
                        if (curMap.get(key) != val)
                            continue;
                    } else {
                        continue;
                    }
                }
            }
            // 모든 조건 만족 둘다 의심됨.
            answer.add(prevStudent.name);
            answer.add(curStudent.name);
            prevStudent = curStudent;
        }
        for (String s : answer) {
            System.out.println(s);
        }
        String[] ans = {};
        return ans;
    }

    static class LogList implements Comparable<LogList> {
        String name;
        int size;
        HashMap<Integer, Integer> psMap = new HashMap<Integer, Integer>();

        LogList(String name) {
            this.name = name;
        }

        public void put(int number, int score) {
            psMap.put(number, score);
        }

        public int getSize() {
            return size = psMap.size();
        }

        @Override
        public int compareTo(LogList l) {
            if (l.getSize() == this.size) {
                return this.name.compareTo(l.name);
            }
            return this.size - l.size;
        }
    }

}
