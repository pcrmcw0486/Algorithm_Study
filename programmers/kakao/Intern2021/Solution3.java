package programmers.kakao.Intern2021;

import java.util.*;
import java.util.LinkedList;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String s = solution.solution(8, 2, new String[] { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" });
        System.out.println(s);
        String t = solution.solution2(8, 2, new String[] { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" });
        System.out.println(t);
        String k = solution.solution3(8, 2, new String[] { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" });
        System.out.println(k);
    }

    public String solution(int n, int k, String[] cmd) {
        Node cur = initList(n);
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }
        for (String s : cmd) {
            char command = s.charAt(0);
            int distance;
            switch (command) {
                case 'U':
                    distance = Integer.parseInt(s.substring(2));
                    while (distance-- > 0)
                        cur = cur.prev;
                    break;
                case 'D':
                    distance = Integer.parseInt(s.substring(2));
                    while (distance-- > 0)
                        cur = cur.next;
                    break;
                case 'C':
                    stack.add(cur);
                    cur.remove();
                    cur = cur.hasNext() ? cur.next : cur.prev;
                    break;
                case 'Z':
                    stack.pop().restore();
                    break;
            }
        }
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop().idx, 'X');
        }
        return sb.toString();

    }

    private class Node {
        int idx;
        Node prev, next;

        public Node(int idx) {
            this.idx = idx;
        }

        boolean hasNext() {
            return next.idx != -1;
        }

        public void restore() {
            prev.next = this;
            next.prev = this;
        }

        public void remove() {
            prev.next = next;
            next.prev = prev;
        }
    }

    private Node initList(int n) {
        Node start = new Node(-1);
        Node prev = start;
        Node cur = null;
        for (int i = 0; i < n; i++) {
            cur = new Node(i);
            prev.next = cur;
            cur.prev = prev;
            prev = cur;
        }
        cur.next = new Node(-1);
        return start.next;
    }

    int N, bit[], arr[];

    void update(int i, int n) {
        while (i <= N) {
            bit[i] += n;
            i += i & -i;
        }
    }

    int sum(int i) {
        int ret = 0;
        while (i > 0) {
            ret += bit[i];
            i -= i & -i;
        }
        return ret;
    }

    int findSum(int target) {
        int s = 1, e = N;
        while (s <= e) {
            int mid = (s + e) / 2, n = sum(mid);
            if (n >= target)
                e = mid - 1;
            else
                s = mid + 1;
        }
        return s - 1;
    }

    public String solution2(int n, int k, String[] cmd) {
        N = n;
        arr = new int[n];
        bit = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(arr, 1);
        for (int i = 1; i <= n; i++) {
            bit[i] = i & -i;
            System.out.print(bit[i] + " ");
        }
        System.out.println();
        for (String c : cmd) {
            switch (c.charAt(0)) {
                case 'D':
                    k = findSum(sum(k + 1) + Integer.parseInt(c.substring(2)));
                    break;
                case 'U':
                    k = findSum(sum(k + 1) - Integer.parseInt(c.substring(2)));
                    break;
                case 'C':
                    stack.add(k);
                    update(k + 1, -1);
                    arr[k] = 0;
                    if (sum(k + 1) == n - stack.size())
                        while (arr[k] == 0)
                            k--;
                    else
                        while (arr[k] == 0)
                            k++;
                    break;
                case 'Z':
                    int z = stack.pop();
                    arr[z] = 1;
                    update(z + 1, 1);
            }
            System.out.println(c);
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println();
            for (int a : bit) {
                System.out.print(a + " ");
            }
            System.out.println();
            System.out.println("cursor " + k);
        }

        StringBuilder sb = new StringBuilder();
        for (int a : arr) {
            sb.append(a == 1 ? "O" : "X");
        }
        return sb.toString();
    }

    private int currentRow;
    private int currentSize;
    private Stack<Integer> removed = new Stack<>();

    public String solution3(int n, int k, String[] cmd) {
        currentRow = k;
        currentSize = n;
        for (String input : cmd) {
            char command = input.charAt(0);
            switch (command) {
                case 'U':
                    currentRow -= Integer.parseInt(input.substring(2));
                    break;
                case 'D':
                    currentRow += Integer.parseInt(input.substring(2));
                    break;
                case 'C':
                    removed.push(currentRow);
                    currentSize--;
                    if (currentRow == currentSize)
                        currentRow--;
                    break;
                case 'Z':
                    int row = removed.pop();
                    currentSize++;
                    if (row <= currentRow)
                        currentRow++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder("O".repeat(currentSize));
        while (!removed.isEmpty()) {
            sb.insert(removed.pop(), "X");
        }
        return sb.toString();
    }
}
