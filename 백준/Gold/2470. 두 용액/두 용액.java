import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];

        // 입력 값 배열에 담기
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n-1;
        int minSum = Integer.MAX_VALUE; // 0에 가까운 합
        int ans1 = 0; // 첫번째 수
        int ans2 = 0; // 두번째 수

        while (left < right) {
            int sum = arr[left] + arr[right];

            // 두 수의 합이 minSum 보다 작으면 저장하고 배열의 요소도 저장
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                ans1 = arr[left];
                ans2 = arr[right];
            }

            if (sum == 0) break;

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}