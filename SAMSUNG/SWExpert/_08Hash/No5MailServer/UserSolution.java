package SAMSUNG.SWExpert._08Hash.No5MailServer;

import java.util.ArrayList;

/*
포인트는 받은 제목을 어떻게 잘 hashing 할 것인가
제목을 해싱할 때 각각의 단어들은 어떻게 다룰 것인가.
메일안의 단어 개수는 최대 10개
각단어는 영어 소문자로 구성됨. djb2

mail
mail 제목 hashCode()값.
mail을 이루는 각각의 hashCode()값

mailBox
3가지가 있어야 할 것 같네요.
어차피 들어오는 순서대로 하면 time은 됩니다.

그냥 단어 안나누고 쭉 해서 해시값만 만들고
라빈카프로 search 돌리는게 나았을 지도.
delete같은거 그냥 해시값비교해보면되니까..
아 하. 그렇게 할걸. 너무 '단어'에 꽃혀가지고.
* */
public class UserSolution {
    static final int HASH_SIZE = 1<<30;
    static final int DIV = HASH_SIZE-1;

    public static class Mail{
        String[] subject;
        int[] wordHash;

        Mail(String[] subject){
            this.subject = subject;
            this.wordHash = new int[subject.length];
            for (int i = 0; i < subject.length;i++) {
                wordHash[i] = hash(subject[i]);
            }
        }

        //String to int hashing
        public static int hash(String s) {
            int hash =5381;
            for (int i = 0; i < s.length(); i++) {
                hash = ((hash << 5) + hash) + s.charAt(i);
            }
            return (int)hash%DIV;
        }

        public boolean isContainWords(String s){
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            //단어들 해시값으로 해시 만들기.
            for (int j : wordHash) {
                hash = (hash << 4) + hash + j;
            }
            return (int)hash%DIV;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Mail){
                Mail m = (Mail)obj;
                if(this == obj) return true;
                if(this.hashCode() != m.hashCode()) return false;
                if(this.subject.length != m.subject.length) return false;
                boolean isEqual = true;
                for(int i =0;i<this.subject.length;i++){
                    if(!this.subject[i].equals(m.subject[i])){
                        isEqual = false;
                        break;
                    }
                }
                return isEqual;
            }
            return false;
        }

    }

    public static class MailBox{
        ArrayList<Mail> inbox;
        int size;

        public MailBox(){
            inbox = new ArrayList<Mail>();
            size =0;
        }

        //오래될수록 앞에 쌓임.
        public void addMail(String[] subject){
            Mail newMail = new Mail(subject);
            if(size == MAX_SIZE){
                inbox.remove(0);
                size--;
            }
            inbox.add(newMail);
            size++;
        }

        public int deleteMail(String[] subject){
            Mail checkMail = new Mail(subject);
            int idx =0 ;
            int cnt =0;
            while(idx < size){
                if (inbox.get(idx).equals(checkMail)) {
                    inbox.remove(idx);
                    idx--;
                    size--;
                    cnt++;
                }
                idx++;
            }
            return cnt;
        }

        //이건 카프라빈으로 구현하는게 나을 거 같긴한데.
        //이미 해놧는데 뭐..
        public int searchMail(String subject){
            int cnt =0;
            for(Mail m : inbox){
                for(String sub : m.subject){
                    if (sub.equals(subject)) {
                        cnt++;
                        break;
                    }
                }
            }
            return cnt;
        }

    }


    static int MAX_SIZE;
    MailBox[] userInbox;
    /**
    * N명의 유저가 메일서버를 이용한다.
    * 한 명당 저장할 수 있는 메일 개수는 K개이다.
    *
    * */
    public void init(int N, int K){
        userInbox = new MailBox[N];
        for(int i =0;i<N;i++){
            userInbox[i] = new MailBox();
        }
        MAX_SIZE =K;
    }
    /**
    * subject[] 제목의 메일 전송을 요청함.
     * subject[] 제목의 매일을, rlDs[] 수신인들의 받은 메일함에 저장.
     * 수신함의 메일 개수가 K개일 경우, 가장 오랜된 메일이 삭제되고
     * subject[] 제목의 메일이 저장된다.
     *
     * @param subject
     * 1개 이상, 10개 이하의 단어로 구성, 단어가 2개 이상이라면 ' '으로 구분된다.
     * 영어 소문자로 구성되며 길이는 3이상 10 이하이다.
     * subject[]는 \0로 끝난다.
     * @param rIds
     * 서로다른 사용자의 id
    * */
    public void sendMail(char[] subject, int uId, int cnt, int rIds[]){
        int size = getStringSize(subject);
        String[] subjects = String.valueOf(subject, 0, size).split(" ");

        /*System.out.print("Send Mail from Uid to rlDs...." + "\n subject : ");
        for (String s : subjects) {
            System.out.print(s + " ");
        }
        System.out.println();*/
        for (int i =0;i<cnt;i++) {
            int rid = rIds[i];
            userInbox[rid].addMail(subjects);
        }
    }

    /**
     * 메일함에 있는 메일 수를 반환한다.
     * 삭제된 메일은 포함되지 않는다.
    * */
    public int getCount(int uID){
        return userInbox[uID].size;
    }

    /**
     * uId 유저의 받은 메일함에서, subject[]와 일치하는 제목을 가진 메일을 삭제하고
     * 삭제한 메일 개수를 return 한다.
    * */
    public int deleteMail(int uId, char[] subject) {
        int size = getStringSize(subject);
        String[] subjects = String.valueOf(subject,0,size).split(" ");
        return userInbox[uId].deleteMail(subjects);
    }

    /**
     * uID 유저의 받은 메일함에서 메일 제목에 text[] 단어가 포함된 메일을 찾고,
     * 메일의 개수를 리턴한다.
     * text[]단어가 일치할때만, 검색이 된다.
     * 예를 들어 aaaa bbb ccc 가 제목일 때 aaa는 검색되지 않는다.
    * */
    public int searchMail(int uId, char[] text) {
        int size = getStringSize(text);
        String searchWords = String.valueOf(text,0,size);
        return userInbox[uId].searchMail(searchWords);
    }

    private int getStringSize(char[] text) {
        int size = 0;
        for (int i = 0; i < text.length; i++) {
            if (text[i] == '\0') {
                size = i;
                break;
            }
        }
        return size;
    }

}
