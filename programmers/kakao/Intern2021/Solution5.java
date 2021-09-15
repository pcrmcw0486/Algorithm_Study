package programmers.kakao.Intern2021;

public class Solution5 {
    public static void main(String[] args) {

    }

    int l[] = new int[10005];
    int r[] = new int[10005];
    int x[] = new int[10005];
    int p[] = new int[10005];
    int n;
    int root;
    int cnt = 0;

    public int solution(int k, int[] num, int[][] links) {
        int n = num.length, root = n * (n - 1) / 2;
        N = num;
        L = links;
        sum = new int[n][2];
        for (int link[] : links) {
            for (int l : link) {
                if (l > 0)
                    root -= l;
            }
        }

        int en = N[0];
        for (int i = 0; i < n; i++)
            en += N[i];
        int st = en / k;
        while (st < en) {
            int mid = (st + en) / 2;
            if (solve(mid) <= k)
                en = mid;
            else
                st = mid + 1;
        }
        return st;
    }

    public int solve(int lim) {
        cnt = 0;
        dfs(root, lim);
        cnt++;
        return cnt;
    }

    public int dfs(int cur, int lim) {
        int lv = 0;
        if (L[cur][0] != -1)
            lv = dfs(L[cur][0], lim);
        int rv = 0;
        if (L[cur][1] != -1)
            rv = dfs(L[cur][1], lim);
        if (N[cur] + lv + rv <= lim)
            return N[cur] + lv + rv;
        if (N[cur] + Math.min(lv, rv) <= lim) {
            cnt++;
            return N[cur] + Math.min(lv, rv);
        }
        cnt += 2;
        return N[cur];
    }

    int cnt1, N[], L[][], sum[][];
    boolean tooMin;

    int solution2(int k, int[] num, int[][] links) {
        int n = num.length, root = n * (n - 1) / 2;
        N = num;
        L = links;
        sum = new int[n][2];
        for (int link[] : links) {
            for (int l : link) {
                if (l > 0)
                    root -= l;
            }
        }

        int e = getSum(root), s = e / k;
        while (s <= e) {
            tooMin = false;
            int mid = (s + e) / 2;
            search(root, mid);
            if (tooMin || cnt1 > k)
                s = mid + 1;
            else
                e = mid - 1;
        }
        return s;
    }

    int getSum(int cur) {
        int ret = N[cur];
        for (int i = 0; i < 2; i++) {
            if (L[cur][i] != -1) {
                ret += (sum[cur][i] = getSum(L[cur][i]));
            }
        }
        return ret;
    }

    // count 세면서 각각의 합.
    int search(int cur, int limit) {
        int n = N[cur], l = sum[cur][0], r = sum[cur][1];
        if (l > limit)
            l = search(L[cur][0], limit);
        if (r > limit)
            r = search(L[cur][1], limit);
        if (l > limit || r > limit || tooMin)
            tooMin = true;
        else if (n + l + r > limit) {
            cnt++;
            if (n + l > limit && n + r > limit) {
                cnt++;
                return n;
            } else
                return n + Math.min(r, l);
        }
        return n + r + l;
    }

}
