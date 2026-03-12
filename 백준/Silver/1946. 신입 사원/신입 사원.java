import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int N = sc.nextInt();
            int[] interview = new int[N + 1];

            for (int i = 0; i < N; i++) {
                int doc = sc.nextInt();
                int inter = sc.nextInt();
                interview[doc] = inter;
            }

            int count = 1;
            int minInterview = interview[1];

            for (int i = 2; i <= N; i++) {
                if (interview[i] < minInterview) {
                    count++;
                    minInterview = interview[i];
                }
            }

            System.out.println(count);
        }
    }
}
