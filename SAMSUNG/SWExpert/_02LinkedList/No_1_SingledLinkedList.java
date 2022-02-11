package SAMSUNG.SWExpert._02LinkedList;

class Node{
    public int data;
    public Node next;
    public Node(int data){
        this.data =data;
        this.next = null;
    }
}

public class No_1_SingledLinkedList {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = new Node(-1);
    }

    public void addNode2Head(int data) {
            Node n = getNode(data);
            n.next = head.next;
            head.next = n;

    }

    public void addNode2Tail(int data) {
        if(nodeCnt == 0){
            addNode2Head(data);
        }else{
            Node n = head;
            while(n.next != null){
                n = n.next;
            }
            n.next = getNode(data);
        }
    }

    public void addNode2Num(int data, int num) {
        if(num == 1){
            addNode2Head(data);
        }else {
            Node prev = head;
            for (int i = 1; i < num - 1; i++) {
                prev = prev.next;
            }
            Node tmp = getNode(data);
            tmp.next = prev.next;
            prev.next = tmp;
        }
    }

    public void removeNode(int data) {
        Node p = head;
        while(p.next != null){
            if(p.next.data == data){
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
    }

    public int getList(int[] output) {
        int idx = 0;
        Node n = head;
        while(n != null){
            output[idx++] = n.data;
            n = n.next;
        }
        return idx;
    }
}
