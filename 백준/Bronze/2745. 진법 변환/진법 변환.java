import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String N = sc.next(); // 숫자 문자열
        int B = sc.nextInt(); // 진법

        int result = 0;
        int power = 1;

        // 뒤에서부터 계산 (B^0부터 시작)
        for (int i = N.length() - 1; i >= 0; i--) {
            char c = N.charAt(i);
            int value;

            if (c >= '0' && c <= '9') {
                value = c - '0';
            } else {
                value = c - 'A' + 10;
            }

            result += value * power;
            power *= B;
        }

        System.out.println(result);
    }
}