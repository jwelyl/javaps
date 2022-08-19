
public class BitMaskingTest {

    static int tv = 1;    
    static int radio = 2; 
    static int mic = 3;

    public static void main(String[] args) {
        byArray();
        byBitMasking();
    }

    static void byArray() {
        boolean[] status = new boolean[9];
        int product = tv; // 1
        // TV 상태 확인
        System.out.printf("제품이 켜져있나? %b%n", status[product]);
        // TV를 켜보자.
        status[tv] = true;
        System.out.printf("제품이 켜졌나? %b%n", status[product]);
        // TV를 꺼보자.
        status[tv] = false;
        System.out.printf("제품이 켜졌나? %b%n", status[product]);
        // TV의 상태를 반전시켜보자.
        status[tv] = !status[tv];
        System.out.printf("제품이 토글되었나? %b%n", status[product]);
        
        // 이제 radio를 관리해볼까?
        product = radio;
    }

    private static void byBitMasking() {
        // TODO: byArray의 내용을 bitMasking으로 변경해보자.
        int status = 0;
        int product = 1<<tv; // tv가 사용할 곳으로 1을 이동시켜준다.
        // TV 상태 확인
        System.out.printf("제품이 켜져있나? %b, %s%n", (status & product) > 0, toBinaryString(status & product));
        // TV를 켜보자.
        //status[tv] = true;
        status|=product;
        System.out.printf("제품이 켜져있나? %b, %s%n", (status & product) > 0, toBinaryString(status & product));
        // TV를 꺼보자.
        //status[tv] = false;
        status&=~product;
        System.out.printf("제품이 켜져있나? %b, %s%n", (status & product) > 0, toBinaryString(status & product));
        // TV의 상태를 반전시켜보자.
        status^=product;
        System.out.printf("제품이 켜져있나? %b, %s%n", (status & product) > 0, toBinaryString(status & product));
        // 이제 radio를 관리해볼까?
        product = 1<<radio;
        // END:
    }
    
    static String toBinaryString(int num) {
        return String.format("%32s", Integer.toBinaryString(num)).replace(" ", "0") + String.format("(%10d)", num);
    }
}
