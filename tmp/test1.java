package tmp;

/*  KK RECOMMEND 1 KGROUP ASCENDING MAX */
public class test1 {
    public static void main(String[] args) {
        int k = 3;
        int[] prices = { 1, 2, 3, 3, 4, 5 };
        int idx = 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        int max = 0;
        int ans = 0;
        while (true) {
            int prev = right;
            if (left == right)
                sum = 0;
            idx++;
            while (right + 1 < prices.length && right - left <= k) {
                right++;
                if (prices[prev] < prices[right]) {
                    sum += prices[prev];
                    prev = right;
                } else {
                    idx--;
                    sum = 0;
                    left = right;
                    break;
                }
            }
            System.out.println(idx + " " + sum);
            if (sum > max) {
                max = sum;
                ans = idx;
            }
            left++;
            if (left > prices.length - k)
                break;
            sum -= prices[left];
        }

        System.out.println(ans);
    }
}
