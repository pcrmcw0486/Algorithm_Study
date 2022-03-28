package DayByDay._0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @문제번호 : Q21942
 * @문제이름 : 부품 대여장
 * @난이도 : Gold II
 * @date : 2022-03-28 오후 5:41
 * @author : pcrmcw0486
 * @문제이해
 * 부품 대여장에 정보를 반드시 작성해야한다.
 * 대여기간을 정하고 대여기간을 넘기면 1분당 벌금을 부여한다.
 * (returnTime - lmitTime) * cost
 * 부품 대여장에 쓰는 형식은 yyyy-MM-dd hh:mm 부품이름 동아리 회원 닉네임
 * 대여 조건
 * - 한 사람이 같은 종류의 부품을 두 개 이상 대여하고 있을 수 없다.(같은 종류는 한 종류)
 * - 같은 시각에 서로 다른 종류의 부품을 대여하는 것은 가능하다
 * 같은 사람이더라도 대여기간은 부품마다 별도로 적용된다.
 *
 * @알고리즘

 * @접근방법

*/
public class Q21942 {
    static Duration duration;
    static long cost;
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String L = st.nextToken();
        String[] a = L.split(":|\\/");
        duration = Duration.ZERO.plusDays(Integer.parseInt(a[0]))
                .plusHours(Integer.parseInt(a[1]))
                .plusMinutes(Integer.parseInt(a[2]));
        cost = Long.parseLong(st.nextToken());
        HashMap<String, Record> users = new HashMap<>();
        Map<String, Long> blackList = new TreeMap<>(String::compareTo);

        for (int i = 0; i < N; i++) {
            String[] record = br.readLine().split(" ");
            LocalDateTime d = LocalDateTime.parse(record[0]+ " " + record[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String user = record[3];
            String object = record[2];
            if (!users.containsKey(user)) {
                users.put(user, new Record());
            }
            Record findRecord = users.get(user);
            if(findRecord.hadRental(object)){
                long fine = findRecord.returnCost(object,d);
                if (fine > 0) {
                    blackList.put(user, blackList.getOrDefault(user, 0L) + fine);
                }
            }else
                findRecord.rentalItem(object,d);
        }
        if(blackList.size() ==0)
            System.out.println(-1);
        else {
            for (Map.Entry<String, Long> en : blackList.entrySet()) {
                System.out.println(en.getKey() + " " + en.getValue());
            }
        }
    }

    public static class Record{
        HashMap<String, LocalDateTime> record;

        public Record() {
            this.record = new HashMap<>();
        }

        public boolean hadRental(String object) {
            return this.record.containsKey(object);
        }
        public void rentalItem(String object, LocalDateTime rentalTime) {
            LocalDateTime limitTime = rentalTime.plus(duration);
            record.put(object, limitTime);
        }

        public long returnCost(String object, LocalDateTime returnTime) {
            LocalDateTime limitTime = record.remove(object);
            long diff = ChronoUnit.MINUTES.between(limitTime, returnTime);
            if(diff <= 0) return 0;
            return diff * cost;
        }

    }
    //2021-01-01 00:00 : 0
    //2021-12-31 23:59 >>\
    //365일 * 24* 60 + 60*23 + 59
}
