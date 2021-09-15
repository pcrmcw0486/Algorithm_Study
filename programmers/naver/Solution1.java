package programmers.naver;

public class Solution1 {

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int answer = solution.solution(new int[] { 0, 1, 0, 0 }, 1);
        System.out.println(answer);
    }

    public int solution(int[] student, int k) {
        int answer = 0;
        int startPoint = -1;
        int endPoint = -1;
        int leftCount = 0;
        int rightCount = 0;
        int kCount = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == 1) {
                if (kCount == 0)
                    startPoint = i;
                kCount++;
                if (kCount == k)
                    endPoint = i;
                else if (kCount == k + 1) {
                    kCount--;
                    answer += (leftCount + 1) * (rightCount + 1);
                    leftCount = endPoint - startPoint - 1;
                    rightCount = 0;
                    startPoint = endPoint;
                    endPoint = i;
                }

            } else {
                if (kCount == 0) {
                    leftCount++;
                } else if (endPoint != -1 && i >= endPoint) {
                    rightCount++;
                }
            }
        }
        if (kCount == k)
            answer += (leftCount + 1) * (rightCount + 1);
        return answer;
    }

}
