package programmers.kit.graph;

public class test {
    public static class SwapTest {
        int value;

        public SwapTest(int value) {
            this.value = value;
        }

        /// 얕은 복사가 이루어지는 거구나.
        public static void swap(SwapTest a, SwapTest b) {
            System.out.println("~ing A : " + a);
            System.out.println("~ing B : " + b);
            int temp = a.value;
            a.value = b.value;
            b.value = temp;
        }
    }

    // call by Ref 지만 새로운 ref를 만들어 호출한다?
    public static void swapt(Integer a, Integer b) {
        System.out.println("~ing AI : " + a + a.getClass() + a.hashCode());
        System.out.println("~ing BI : " + b + b.getClass() + b.hashCode());
        Integer tmp = a;
        a = b;
        b = tmp;
    }

    public static void main(String[] args) {
        SwapTest a = new SwapTest(1);
        SwapTest b = new SwapTest(2);
        System.out.println(a.value + " " + b.value);
        System.out.println("before A : " + a);
        System.out.println("before B : " + b);
        SwapTest.swap(a, b);
        System.out.println("after A : " + a);
        System.out.println("after B : " + b);
        System.out.println(a.value + " " + b.value);

        Integer aI = 1;
        Integer bI = 2;
        System.out.println(aI + " " + bI);
        System.out.println("~before AI : " + aI.hashCode());
        System.out.println("~before BI : " + b);
        swapt(aI, bI);
        Integer tmp;
        System.out.println("~after AI : " + aI.hashCode());
        System.out.println("~after BI : " + b);
        System.out.println(aI + " " + bI);
    }
}
