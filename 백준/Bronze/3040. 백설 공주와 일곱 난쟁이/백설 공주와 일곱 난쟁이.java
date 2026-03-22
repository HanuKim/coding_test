import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] arr = new int[9];
        int sum = 0;
        
        // 입력
        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        
        // 2명 제거 찾기
        outer:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    
                    // 정답 출력
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        System.out.println(arr[k]);
                    }
                    break outer;
                }
            }
        }
    }
}