package programmers.naver;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution6 {

    public static void main(String[] args) {

    }

    public String[] solution(String[] records, int k, String date) {
        ArrayList<Record> recordTable = new ArrayList<Record>();

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1] == 0 ? a[2] - b[2] : a[1] - b[1]); // pid
                                                                                                                    // 재구매율,
                                                                                                                    // 총
                                                                                                                    // 구매수
        Date endDate = makeDate(date);
        Date startDate = minus(endDate, k);
        Set<Integer> pidList = new HashSet<Integer>();
        for (String record : records) {
            String[] r = record.split(" ");
            Date recordDate = makeDate(r[0]);
            int uid = Integer.parseInt(r[1].substring(3));
            int pid = Integer.parseInt(r[2].substring(3));
            if (recordDate.year >= startDate.year && recordDate.code >= startDate.code
                    && recordDate.year <= endDate.year && recordDate.code <= endDate.code) {
                recordTable.add(new Record(recordDate, uid, pid));
                pidList.add(pid);
            }
        }

        for (Integer pid : pidList) {
            List<Record> searchList = recordTable.stream().filter(record -> record.pid == pid)
                    .collect(Collectors.toList());
            int count = recordTable.size();// 총 구매
            int size = 0; // 한번 이상 구매
            int reBye = 0;// 재구매
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (Record r : searchList) {
                if (map.containsKey(r.pid)) {
                    reBye++;
                } else {
                    map.put(r.pid, 1);
                    size++;
                }
            }
            pq.add(new int[] { pid, (int) (reBye * 100) / size, count });
        }

        String[] answer = new String[pq.size()];
        int count = 0;
        while (!pq.isEmpty()) {
            int tmp = pq.poll()[0];
            answer[count] = "pid" + String.valueOf(tmp);
            count++;
        }
        return answer;
    }

    class Record {
        Date date;
        int uid;
        int pid;

        Record(Date date, int uid, int pid) {
            this.date = date;
            this.uid = uid;
            this.pid = pid;
        }
    }

    class Date {
        int year;
        int code;

        Date(int year, int month, int date) {
            this.year = year;
            this.code = 30 * (month - 1) + date - 1;
        }

        Date(int year, int code) {
            this.year = year;
            this.code = code;
        }
    }

    Date makeDate(String s) {
        String[] tmp = s.split("-");
        return new Date(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
    }

    Date minus(Date prev, int k) {
        int nextDate = prev.code - k + 1;
        int year = prev.year;
        if (nextDate < 0) {
            nextDate = 360 - nextDate;
            year--;
        }
        return new Date(year, nextDate);
    }
}
