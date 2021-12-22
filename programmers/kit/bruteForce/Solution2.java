package programmers.kit.bruteForce;

public class Solution2 {
    static int cnt = 0;
    static int[] count;

    public static void main(String[] args) {
        int a = solution("17");
        System.out.println(a);
    }

    public static int solution(String numbers) {
        count = new int[10];
        for (int i = 0; i < numbers.length(); i++) {
            count[numbers.charAt(i) - '0']++;
        }

        for (int i = 0; i < numbers.length(); i++) {
            dfs(i + 1, 0, 0);
        }
        return cnt;
    }

    public static void dfs(int limit, int depth, int a) {
        if (depth == limit) {
            if (isPrime(a))
                cnt++;
            return;
        }
        a *= 10;
        for (int i = 0; i < 10; i++) {
            if (depth == 0 && i == 0)
                continue;
            if (count[i] != 0) {
                a += i;
                count[i]--;
                dfs(limit, depth + 1, a);
                a -= i;
                count[i]++;
            }
        }
    }

    public static boolean isPrime(int a) {
        if (a < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }
}
