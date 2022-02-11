package SAMSUNG.SWExpert._09Exercise._01MemoProgram;
import java.util.LinkedList;

class REUserSolution {
    int maxLength;
    int maxLine;
    LinkedList<Character>[] memo;
    int size;
    int cursorX;
    int cursorY;
    int[][] countChar;

    void init(int H, int W, char mStr[]) {
        maxLength = W;
        maxLine = H;
        size =0;
        memo = new LinkedList[H];
        countChar = new int[H][26];
        for (int i = 0; i < H; i++) memo[i] = new LinkedList<>();
        for (char c : mStr) {
            if (c == '\0')
                break;
            size++;
            int h = (size-1)/maxLength;
            memo[h].add(c);
            countChar[h][c-'a']++;
        }
        cursorX = 0;
        cursorY =0;
    }

    void insert(char mChar) {
        size++;
        int idx = cursorX;
        if(cursorY < memo[cursorX].size()) {
            memo[cursorX].add(cursorY, mChar);
        }
        else
            memo[cursorX].addLast(mChar);
        countChar[idx][mChar-'a']++;

        while(memo[idx].size() > maxLength){
            char nextChar = memo[idx].removeLast();
            countChar[idx][nextChar-'a']--;
            memo[idx+1].addFirst(nextChar);
            countChar[idx+1][nextChar-'a']++;
            idx++;
        }
        cursorForward();
    }

    char moveCursor(int mRow, int mCol){
        Character a;
        if(mCol-1 >= memo[mRow-1].size()) {
            cursorX = (size - 1) / maxLength;
            cursorY = (size - 1) % maxLength;
            cursorForward();
            return '$';
        }
        cursorX = mRow-1;
        cursorY = mCol-1;
        return memo[cursorX].get(cursorY);
    }

    int countCharacter(char mChar){
        int cnt =0;
        int idx =0;
        for(char c : memo[cursorX]){
            if(c==mChar && idx >= cursorY) cnt++;
            idx++;
        }
        for(int i = cursorX+1;i<maxLine;i++) cnt += countChar[i][mChar-'a'];
        return cnt;
    }
    void cursorForward(){
        cursorY++;
        if(cursorY == maxLength){
            cursorY = 0;
            cursorX++;
        }
    }
}
