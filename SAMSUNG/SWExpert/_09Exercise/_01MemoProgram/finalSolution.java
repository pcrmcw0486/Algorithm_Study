package SAMSUNG.SWExpert._09Exercise._01MemoProgram;

import java.util.LinkedList;

public class finalSolution {
    LinkedList<Character> memo[];
    int[][] count;
    int curX;
    int curY;
    int maxLength;
    int maxLine;
    int size;

    public void init(int H, int W, char[] str) {
        maxLength = W;
        maxLine = H;
        count = new int[H + 1][26];
        memo = new LinkedList[H + 1];
        size =0;
        for (int i = 0; i < H + 1; i++) memo[i] = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            size++;
            if (str[i] == '\0') {
                memo[i / maxLength].add('$');
                break;
            }
            memo[i / maxLength].add(str[i]);
            count[i / maxLength][str[i] - 'a']++;
        }
        curX = 0;
        curY = 0;
    }

    public void insert(char mChar) {
        size++;
        memo[curX].add(curY, mChar);
        count[curX][mChar-'a']++;
        int line = curX;
        while (memo[line].size() > maxLength) {
            char u = memo[line].removeLast();
            memo[line + 1].addFirst(u);
            if (u != '$') {
                count[line][u - 'a']--;
                count[line + 1][u - 'a']++;
            }
            line++;
        }
        curY++;
        if (curY == maxLength) {
            curY = 0;
            curX++;
        }
    }

    public char moveCursor(int mRow, int mCol) {
        if(mCol-1 < memo[mRow-1].size()){
            curX = mRow-1;
            curY = mCol-1;
            return memo[curX].get(curY);
        }
        curX = (size-1)/maxLength;
        curY = (size-1)%maxLength;
        return '$';
    }

    public int countCharacter(char mChar) {
        int cnt = 0;
        if (curY == 0) cnt += count[curX][mChar - 'a'];
        else
            for (int i = 0; i < memo[curX].size(); i++)
                if (memo[curX].get(i) == mChar && i >= curY) cnt++;
        for (int i = curX + 1; i < maxLine; i++)
            cnt += count[i][mChar - 'a'];
        return cnt;
    }
}
