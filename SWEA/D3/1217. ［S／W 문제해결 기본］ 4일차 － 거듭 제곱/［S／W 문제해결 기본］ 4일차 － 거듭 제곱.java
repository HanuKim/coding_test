import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < 10; i++) {
            int tc = sc.nextInt(); // 테스트 케이스 번호
            int N = sc.nextInt();  // 밑 
            int M = sc.nextInt();  // 지수 
            
            int ans = power(N, M);
            System.out.println("#" + tc + " " + ans);
        }
        sc.close();
    }

    private static int power(int n, int m) {
        // 모든 수의 0승은 1 
        if (m == 0) {
            return 1;
        }
        return n * power(n, m - 1);
    }
}