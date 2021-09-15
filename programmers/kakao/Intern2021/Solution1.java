package programmers.kakao.Intern2021;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.solution("onetwo330fivezerozeronine0"));
    }

    public int solution(String s) {
        String[] strArr = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        for (int i = 0; i < strArr.length; i++) {
            s = s.replaceAll(strArr[i], Integer.toString(i));// Integer.toString이랑 String.valueOf 누가 빠를까
        }
        return Integer.parseInt(s);
    }

}
