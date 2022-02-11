package SAMSUNG.SWExpert._06_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2_Validcheck4operate {
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tt = 1; tt<=10;tt++){
            sb.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            tree = new Node[N+1];
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int curNum = Integer.parseInt(st.nextToken());
                char value = st.nextToken().charAt(0);
                tree[curNum] = new Node(value);
                while (st.hasMoreTokens()) {
                    int childNum = Integer.parseInt(st.nextToken());
                    tree[curNum].addChild(childNum);
                }
            }

            sb.append(validCheckInorder(1)?1:0).append('\n');
        }
        System.out.println(sb.toString());
    }
    //올바르지 않은 상태
    // 자식이 있는데, 숫자인 경우
    // 자식이 없는데, 알파벳인 경우 (연산을 하려면 양쪽에 모두 있어야함)

    public static boolean validCheckInorder(int num){
        boolean leftCheck =true;
        boolean rightCheck = true;
        Node cur = tree[num];
        //숫자인데 자시이 있는 경우
        if(Character.isDigit(cur.value) && (cur.left != -1 || cur.right != -1)) return false;
        //자식이 하나도 없는데 알파벳인경우
        if(Character.isAlphabetic(cur.value) && (cur.left ==-1 && cur.right == -1)) return false;

        if(cur.left  != -1) leftCheck = validCheckInorder(cur.left);
        if(cur.right != -1) rightCheck = validCheckInorder(cur.right);
        return leftCheck&&rightCheck;
    }
    static class Node{
        char value;
        int left;
        int right;
        Node(char value){
            this.value = value;
            left = -1;
            right = -1;
        }
        public void addChild(int child){
            if(left == -1)
                left = child;
            else
                right = child;
        }
    }
}
