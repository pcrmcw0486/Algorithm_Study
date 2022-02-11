package SAMSUNG.SWExpert._06_Tree;

import java.util.ArrayList;

class No_5_Directory {

    private final static int NAME_MAXLEN = 6;
    private final static int PATH_MAXLEN = 1999;
    public static Node root;

    void init(int n) {
        //parent 이름이 ""
        root = new Node(null,"");
    }

    //path에 맞는 Node를 반환.
    public Node findDirectoryByPath(char[] path) {
        String[] route = String.valueOf(path, 0, path.length - 1).split("/");
        Node ret = root;
        if (route.length == 0)
            return root;
        for(int i = 1;i<route.length;i++){
            for(Node n : ret.child){
                if(n.name.equals(route[i])){
                    ret = n;
                    break;
                }
            }
        }
        return ret;
    }


    void cmd_mkdir(char[] path, char[] name) {
        Node parent = findDirectoryByPath(path);
        parent.child.add(new Node(parent, String.valueOf(name, 0, name.length - 1)));
    }

    void cmd_rm(char[] path) {
        Node target = findDirectoryByPath(path);
        target.parent.child.remove(target);
        target.parent = null;
    }

    //얕은 복사와 깊은 복사.
    // 저번의 문제점은 어차피 그 아래 친구들도 다 다시 shallow로 복사되었기 때문이죠.
    //복사를 해야함.
    void cmd_cp(char[] srcPath, char[] dstPath) {
        Node dst = findDirectoryByPath(dstPath);
        Node src = Node.copy(findDirectoryByPath(srcPath), dst);
        dst.child.add(src);
    }

    void cmd_mv(char[] srcPath, char[] dstPath) {
        Node src = findDirectoryByPath(srcPath);
        Node dst = findDirectoryByPath(dstPath);
        src.parent.child.remove(src);
        src.parent = dst;
        dst.child.add(src);
    }

    int cmd_find(char[] path) {
        Node target = findDirectoryByPath(path);
        return Node.findCnt(target);
    }


    static class Node {
        Node parent;
        ArrayList<Node> child;
        String name;
        Node(Node parent, String name){
            this.parent = parent;
            this.name = name;
            child = new ArrayList<Node>();
        }
        //p를 부모로하는
        public static Node copy(Node src, Node p){
            Node dst = new Node(p, src.name);
            for(Node n : src.child){
                dst.child.add(copy(n,dst));
            }
            return dst;
        }
        public static int findCnt(Node src){
            int cnt = src.child.size();
            for(Node n : src.child){
                cnt += findCnt(n);
            }
            return cnt;
        }
    }
    //	 The below commented methods are for your reference. If you want
//	 to use it, uncomment these methods.
//
//	int mstrcmp(char[] a, char[] b) {
//		int i;
//		for (i = 0; a[i] != '\0'; i++) {
//			if (a[i] != b[i])
//				return a[i] - b[i];
//		}
//		return a[i] - b[i];
//	}
//
//	int mstrncmp(char[] a, char[] b, int len) {
//		for (int i = 0; i < len; i++) {
//			if (a[i] != b[i])
//				return a[i] - b[i];
//		}
//		return 0;
//	}
//
//	int mstrlen(char[] a) {
//		int len = 0;
//
//		while (a[len] != '\0')
//			len++;
//
//		return len;
//	}
//
//	void mstrcpy(char[] dest, char[] src) {
//		int i = 0;
//		while (src[i] != '\0') {
//			dest[i] = src[i];
//			i++;
//		}
//		dest[i] = src[i];
//	}
//
//	void mstrncpy(char[] dest, char[] src, int len) {
//		for (int i = 0; i < len; i++) {
//			dest[i] = src[i];
//		}
//		dest[len] = '\0';
//	}

}
