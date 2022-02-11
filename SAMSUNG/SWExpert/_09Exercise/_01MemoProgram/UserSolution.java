package SAMSUNG.SWExpert._09Exercise._01MemoProgram;

class UserSolution {
    static class Node {
        char value;
        Node prev;
        Node next;

        Node(char value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        Node(char value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    static class DoubleLinkedList {
        int size;
        Node head;
        Node tail;
        int[] charCount;

        DoubleLinkedList() {
            charCount = new int[26];
            this.size = 0;
            this.head = null;
            this.tail = null;
        }

        public void add(char v) {
            size++;
            Node item;
            if (head != null) {
                //addLast
                item = new Node(v, tail, tail.next);
                tail.next = item;
            } else {
                item = new Node(v);
                head = item;
            }
            tail = item;
        }

        public Node get(int idx) {
            Node tmp = head;
            for (int i = 0; i < idx; i++) {
                tmp = tmp.next;
            }
            return tmp;
        }
    }

    int cursorX;
    int cursorY;
    Node cursor;
    DoubleLinkedList[] memo;
    int size;
    int maxLength;

    void init(int H, int W, char[] mStr) {
        maxLength = W;
        size = 0;
        memo = new DoubleLinkedList[H];
        for (int i = 0; i < H; i++) memo[i] = new DoubleLinkedList();
        for (int i = 0; i < mStr.length; i++) {
            if (mStr[i] == '\0') {
                size++;
                memo[i / maxLength].add('$');
                break;
            }
            memo[i / maxLength].add(mStr[i]);
            memo[i / maxLength].charCount[mStr[i] - 'a']++;
            size++;
        }
        for (int i = 0; i < size / maxLength; i++) {
            memo[i].tail.next = memo[i + 1].head;
            memo[i + 1].head.prev = memo[i].tail;
        }
        cursorX = 0;
        cursorY = 0;
        cursor = memo[0].head;
    }

    void insert(char mStar) {
        size++;
        Node item = new Node(mStar, cursor.prev, cursor);
        if (memo[cursorX].head == cursor)
            memo[cursorX].head = item;
        if (cursor.prev != null)
            cursor.prev.next = item;
        cursor.prev = item;
        memo[cursorX].size++;
        memo[cursorX].charCount[mStar - 'a']++;

        int idx = cursorX;
        while (memo[idx].size > maxLength) {
            memo[idx].size--;
            memo[idx + 1].size++;
            if (memo[idx].tail.value != '$') {
                memo[idx].charCount[memo[idx].tail.value - 'a']--;
                memo[idx + 1].charCount[memo[idx].tail.value - 'a']++;
            }
            if (memo[idx + 1].head == null)
                memo[idx + 1].head =  memo[idx + 1].tail= memo[idx].tail;
           else
                memo[idx + 1].head = memo[idx].tail;
            memo[idx].tail = memo[idx].tail.prev;
            idx++;
        }
        cursorForward();
    }

    char moveCursor(int mRow, int mCol) {
        int idx = maxLength * (mRow - 1) + mCol - 1;
        if (idx >= size) {
            cursorX = (size - 1) / maxLength;
            cursorY = (size - 1) % maxLength;
            cursor = memo[(size - 1) / maxLength].tail;
            return '$';
        }
        cursorX = mRow - 1;
        cursorY = mCol - 1;
        cursor = memo[cursorX].get(cursorY);
        return cursor.value;
    }

    int countCharacter(char mChar) {
        int cnt = 0;
        if (cursorY == 0) cnt += memo[cursorX].charCount[mChar - 'a'];
        else {
            Node tmp = cursor;
            for (int i = 0; i < memo[cursorX].size - cursorY; i++) {
                if (tmp.value == mChar) cnt++;
                tmp = tmp.next;
            }
        }
        for (int i = cursorX + 1; i <= (size - 1) / maxLength; i++)
            cnt += memo[i].charCount[mChar - 'a'];
        return cnt;
    }
    void cursorForward() {
        cursorY++;
        if (cursorY == maxLength) {
            cursorY = 0;
            cursorX++;
        }
    }
}
