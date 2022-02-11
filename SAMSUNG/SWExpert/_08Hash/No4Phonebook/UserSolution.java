package SAMSUNG.SWExpert._08Hash.No4Phonebook;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class UserSolution {

    private static class Record {
        int idx;
        String name;
        String number;
        String birthday;
        String email;
        String memo;

        public Record(int idx, String name, String number, String birthday, String email, String memo) {
            this.idx = idx;
            this.name = name;
            this.number = number;
            this.birthday = birthday;
            this.email = email;
            this.memo = memo;
        }

        public void change(int field, String str) {
            switch (field) {
                case NAME:
                    this.name = str;
                    break;
                case NUMBER:
                    this.number = str;
                    break;
                case BIRTHDAY:
                    this.birthday = str;
                    break;
                case EMAIL:
                    this.email = str;
                    break;
                case MEMO:
                    this.memo = str;
                    break;
                default:
                    break;
            }
        }

        public String getInfo(int field) {
            switch (field) {
                case NAME:
                    return this.name;
                case NUMBER:
                    return this.number;
                case BIRTHDAY:
                    return this.birthday;
                case EMAIL:
                    return this.email;
                case MEMO:
                    return this.memo;
            }
            return null;
        }
    }

//    enum Field {
//        NAME, NUMBER, BIRTHDAY, EMAIL, MEMO
//    }

    public final static int NAME = 0;
    public final static int NUMBER = 1;
    public final static int BIRTHDAY = 2;
    public final static int EMAIL = 3;
    public final static int MEMO = 4;


    static final int MAX = 50000;
    boolean[] isValid = new boolean[MAX];
    Record[] db = new Record[MAX];
    HashMap<String, Set<Integer>>[] indexTable;
    int idx = 0;

    @SuppressWarnings("unchecked")
    void InitDB() {
        isValid = new boolean[MAX];
        indexTable = (HashMap<String, Set<Integer>>[]) new HashMap[5];
        for (int i = 0; i < 5; i++) indexTable[i] = new HashMap<String, Set<Integer>>();
        idx = 0;
    }

    void Add(String name, String number, String birthday, String email, String memo) {
        Record item = new Record(idx, name, number, birthday, email, memo);
        db[idx] = item;
        addIndexTable(name, NAME,idx);
        addIndexTable(number, NUMBER,idx);
        addIndexTable(birthday, BIRTHDAY,idx);
        addIndexTable(email, EMAIL,idx);
        addIndexTable(memo, MEMO,idx);
        isValid[idx] = true;
        idx++;
    }

    private void addIndexTable(String data, int field, int id) {
        if (indexTable[field].containsKey(data)) {
            Set<Integer> set = indexTable[field].get(data);
            set.add(id);
        } else {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(id);
            indexTable[field].put(data, newSet);
        }
    }


    int Delete(int field, String str) {
        //field number type은 맞춰놓음
        int cnt = 0;
        if (this.indexTable[field].containsKey(str)) {
            Set<Integer> set = this.indexTable[field].get(str);
            Integer[] deleteArr = set.toArray(new Integer[0]);
            for (int id : deleteArr) {
                if (isValid[id]) {
                    isValid[id] = false;
                    cnt++;
                }
            }
            this.indexTable[field].remove(str);
            return cnt;
        } else
            return 0;
    }

    int Change(int field, String str, int changefield, String changestr) {

        if (this.indexTable[field].containsKey(str)) {
            int cnt = 0;
            Set<Integer> set = this.indexTable[field].get(str);
            Integer[] findArr = set.toArray(new Integer[0]);
            for (int id : findArr) {
                if (!isValid[id]) {
                    set.remove(id);
                    continue;
                }
                Record r = db[id];
                this.indexTable[changefield].get(r.getInfo(changefield)).remove(id);
                r.change(changefield, changestr);
                cnt++;
                //바꿔주었으니 없애고 updage해야지.
                if(field == changefield)
                    set.remove(id);
                addIndexTable(changestr,changefield,id);
            }
            this.indexTable[field].put(str, set);
            return cnt;
        }
        return 0;
    }

    Solution.Result Search(int field, String str, int returnfield) {
        Solution.Result result = new Solution.Result();
        result.count = 0;
        if (this.indexTable[field].containsKey(str)) {
            int cnt = 0;
            String info = "";
            Set<Integer> set = this.indexTable[field].get(str);
            Integer[] findArr = set.toArray(new Integer[0]);
            for (int id : findArr) {
                if (!isValid[id]) {
                    set.remove(id);
                    continue;
                }
                Record r = db[id];
                info = r.getInfo(returnfield);
                cnt++;
            }
            this.indexTable[field].put(str, set);
            if (cnt == 1) result.str = info;
            result.count = cnt;
        }
        return result;
    }


}
