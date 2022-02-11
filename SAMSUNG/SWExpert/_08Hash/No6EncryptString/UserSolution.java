package SAMSUNG.SWExpert._08Hash.No6EncryptString;

/**
 * 주어진 암호화 규칙에 따라 변환하는 프로그램을 구현하고자한다.
 * 부분 문자열 A를 B로 변환하는 규칙이다.
 *
 * @implSpec  규칙 적용 방법<br>
 *  1. 전체 문자열을 처음 index부터 탐색하여, 부분 문자열이 A인지 확인한다. <br>
 *  2. A가 아닐 경우 다음 index의 부분 문자열을 탐색한다.<br>
 *  3. A일 경우 해당 index의 부분 문자열을 B로 변환하고, index를 A길이 만큼 증가시킨 후 탐색을 계속한다.<br>
 * <br>
 * @implNote  규칙이 주어지는 순서에 따라 문자열을 변환하여 최종 문자열을 구하라.
 * */

public class UserSolution {
    private static int HASH_SIZE = (int)Math.pow(26,3);
    private static int DIV = HASH_SIZE-1;
    private static int power = (int)Math.pow(26,2);

    char[] text;
    Node[] data;
    Node[] hashTable;
    public void init(int N, char[] init_string) {
        int hash =0;
        data = new Node[N];
        hashTable = new Node[HASH_SIZE];
        text = new char[N];
        //dummy Node
        for(int i =0;i<HASH_SIZE;i++) hashTable[i] = new Node(-1);
        for(int i =0;i<N;i++) text[i] = init_string[i];
        //initialize
        for(int i = N-3;i>=0;i--){
            if(i == N-3) hash = getHash(text,i,3);
            else hash = getPrev(hash, text[i + 3]-'a', text[i]-'a');
            Node newNode = new Node(i);
            Node head = hashTable[hash];
            newNode.prev = head;
            newNode.next = head.next;
            if(head.next != null) head.next.prev = newNode;
            head.next = newNode;
//            newNode.prev = hashTable[hash];
//            newNode.next = hashTable[hash].next;
//            hashTable[hash].next = newNode;
            data[i] = newNode;
        }
    }

    public int getHash(char[] text, int start, int size){
        int hash =0;
        for(int i = start;i<start+size;i++){
            hash = hash*26 + (text[i]-'a');
        }
        return hash;
    }
    public static int getPrev(int hash, int prev, int cur){
        return cur*power + (hash-prev)/26;
    }

    public static int getNext(int hash, int prev, int cur){
        return 26*(hash-power*prev)+cur;
    }
    public int change(char[] string_A, char[] string_B) {
        int patternHash = getHash(string_A,0,3);
        int newHash = getHash(string_B,0,3);
        //hashTable을 이용하여 같은 hash값을 가진 노드들로 설정.
        //해당 index를 순회하면서 (낮은 자리 부터)
        // 값을 바꿔주고 새로 넣기.
        // 새로 넣은 후에 자신과 관련된 노드들을 다시 재배치
        Node cur = hashTable[patternHash].next;
        int cnt =0;
        while(cur != null){
            cnt++;
            //미리 받아놓기.
            Node nextNode = cur.next;
            while(nextNode != null){
                if(nextNode.idx < cur.idx + 3){
                    nextNode = nextNode.next;
                }else break;
            }
            //값을 바꿔주기
            System.arraycopy(string_B, 0, text, cur.idx, cur.idx + 3 - cur.idx);
            cur.prev.next = cur.next;
            if(cur.next != null)
                cur.next.prev = cur.prev;
            cur.prev = cur.next =null;
            inputNewHash(cur, newHash);
            // i-2 i-1 i i+1 i+2 i+3

            int hash =0;
            for(int i = -2;i<4;i++){
                int affectedIdx = cur.idx +i;
                if(affectedIdx-1 < 0 || affectedIdx+2 > data.length-1) continue;
                if(hash == 0 || affectedIdx == 0) hash = getHash(text,affectedIdx,3);
                else hash = getNext(hash, text[cur.idx+i-1]-'a',text[cur.idx + i+2]-'a');
                if(i!=0){
                    Node n = data[affectedIdx];
                    n.prev.next = n.next;
                    if(n.next != null) n.next.prev = n.prev;
                    n.prev=n.next = null;
                    inputNewHash(n,hash);
                }
            }
            cur = nextNode;
        }

        return cnt;
    }

    private void inputNewHash(Node cur, int hash) {
//        cur.prev = null;
//        cur.next = null;
        Node tmp = hashTable[hash];
        while(tmp.next != null){
            if(tmp.next.idx == cur.idx) return;
            if(tmp.next.idx > cur.idx) break;
            tmp = tmp.next;
        }
        cur.next = tmp.next;
        cur.prev = tmp;
        if(tmp.next!= null) tmp.next.prev = cur;
        tmp.next = cur;
        return;
    }

    public void result(char ret[]){
        System.arraycopy(text,0,ret,0,text.length);
    }

    static class Node{
        int idx;
        Node prev;
        Node next;

        Node(int idx){
            this.idx = idx;
            this.prev = null;
            this.next = null;
        }
    }
}
