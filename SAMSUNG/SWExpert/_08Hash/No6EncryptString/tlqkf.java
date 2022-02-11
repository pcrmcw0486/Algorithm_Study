package SAMSUNG.SWExpert._08Hash.No6EncryptString;

public class tlqkf {
    private static int HASH_SIZE = (int)Math.pow(26,3);
    private static int POWER = (int)Math.pow(26,2);

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
    Node[] data;
    Node[] hashTable;
    char[] text;
    int TEXT_SIZE;

    @SuppressWarnings("DuplicatedCode")
    void init(int N, char init_string[]){
        TEXT_SIZE = N;
        text = new char[N];
        System.arraycopy(init_string, 0, text, 0, N);
        data = new Node[TEXT_SIZE];
        hashTable = new Node[HASH_SIZE];
        for(int i =0;i<HASH_SIZE;i++) hashTable[i] = new Node(-1);
        int hash =0;
        for(int i = N-3;i>=0;i--){
            if(i==N-3) hash = getHash(text, i);
            else hash = getPrevHash(hash,text[i]);
            Node newNode = new Node(i);
            Node tmp = hashTable[hash];
            newNode.prev = tmp;
            newNode.next = tmp.next;
            if(tmp.next != null) tmp.next.prev = newNode;
            tmp.next = newNode;
            data[i] = newNode;
        }
    }
    public int getNext(int hash, char prev, char cur){
        return (hash - POWER*(prev-'a'))*26 + (cur-'a');
    }
    public int change(char[] string_A, char[] string_B){
        int patternHash = getHash(string_A,0);
        int cnt =0;
        Node item = hashTable[patternHash].next;
        while(item != null){
            cnt++;
            //findNext
            Node nextItem = item.next;
            while(nextItem != null){
                if(nextItem.idx >= item.idx+3) break;
                nextItem = nextItem.next;
            }

            //array copy
            System.arraycopy(string_B, 0, text, item.idx, 3);

            //update affected Node
            // cur.idx -2  ~  cur.idx +2
            int hash =-1;
            for(int offset = -2 ; offset<3;offset++){
                int updateIdx = item.idx +offset;
                if(updateIdx < 0 || updateIdx > TEXT_SIZE-3) continue;
                if(hash == -1 || updateIdx ==0) hash = getHash(text, updateIdx);
                else hash = getNext(hash, text[updateIdx - 1], text[updateIdx + 2]);

                //현재 위치 끊어주기
                Node updateNode = data[updateIdx];
                updateNode.prev.next = updateNode.next;
                if(updateNode.next != null) updateNode.next.prev = updateNode.prev;
                updateNode.next = updateNode.prev = null;


                //다음 위치로 넣어주기
                Node newHead = hashTable[hash];
                while(newHead.next != null){
                    if(newHead.next.idx > updateNode.idx) break;
                    newHead = newHead.next;
                }
                updateNode.prev = newHead;
                updateNode.next = newHead.next;
                if(newHead.next != null) newHead.next.prev = updateNode;
                newHead.next = updateNode;
            }

            item = nextItem;
        }
        return cnt;
    }

    public int getHash(char[] text, int start){
        int hash =0;
        for(int i = start;i<start+3;i++){
            hash = 26 * hash + (text[i] - 'a');
        }
        return hash;
    }

    public int getPrevHash(int hash, char cur){
        return (hash-(hash%26))/26 + POWER*(cur-'a');
    }


    public void result(char ret[]){
        System.arraycopy(text,0,ret,0,TEXT_SIZE);
    }

}