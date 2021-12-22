package programmers.kit.hash;

/*
스파이들은 매일 다른 옷을 조합한다.
동그라안경, 긴코드, 파란색 티셔츠를 입었다면
청바지를 추가로 입거나, 동그란 안경대신 검정 선글라스를 착용해야한다.

생각의 흐름이
1. 개수만 있으면 될 것 같다.
2. 결국 그냥 조합문제 인 것 같다. 근데 개수만 세면된다.
조합 계산식으로 풀면되는 문제다.
가장큰 값은?
n종류 30/n씩 가지고 있다치면
f(x) = (30/n +1) ^n -1이 결국 값인데 이거 최댓값어케 구함.
f`(x) = n * (1+ 30/n)^(n-1) (n!=0) 일단 int형으로 간다.
*/
import java.util.*;
import java.util.stream.Collectors;

public class Solution3 {
    public static void main(String[] args) {
        String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
                { "greenturban", "headgear" } };
        int a = solution(clothes);
        System.out.println(a);
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }
        int ans = 1;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans *= (entry.getValue() + 1);
        }
        return ans - 1;
    }

    // 근데 확실히 시간은 오래걸리네
    public static int solutionStream(String[][] clothes) {
        return Arrays.stream(clothes)
                .collect(Collectors.groupingBy(p -> p[1], Collectors.mapping(p -> p[0], Collectors.counting())))
                .values().stream().collect(Collectors.reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }

}
