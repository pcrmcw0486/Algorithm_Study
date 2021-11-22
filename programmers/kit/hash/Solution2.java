package programmers.kit.hash;

/*
전화번호 목록
- 전화번호 목록이 주어질 때
 하나의 번호가 다른 번호의 접두어인가? true/ false 반환

 주어지는 번호 개수가 1_000_000이하이고
 각 전화번호 길이가 20이하임.

 생각 논리의 흐름이
 1. 하나당 검색해서 최악의 경우 1_000_000 * 999_999 * 20 크다.(번호마다 다른 번호랑 앞자리 비교)
 2. 그렇다면 있냐 없냐가 중요하니까 탐색, 확인이 빠른 Hash Set을 써야겟구나.
 3. 다 넣고 할까? 다넣지 않고 할까?
   3.1 다 넣고 한다면 검색 및 넣는 순서가 중요하니까 소팅을 해야되는구나. 중간에 끊을 수 있겟네.
   3-2. 다 넣고 하면 순서는 중요하지 않겟네. 
 */
import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        String[] phone_book = { "119", "97674223", "1195524421" };
        System.out.println(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {
        Set<String> phoneSet = new HashSet<String>();
        Arrays.stream(phone_book).forEach(pb -> phoneSet.add(pb));
        for (String pb : phoneSet) {
            for (int len = 1; len < pb.length(); len++) {
                if (phoneSet.contains(pb.substring(0, len))) {
                    return false;
                }
            }
        }
        return true;
    }
}
