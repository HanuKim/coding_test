import java.util.Scanner;

public class Main{
    static int N, S, result;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        dfs(0, 0);
        if (S == 0) result--;
        System.out.println(result);
    }

    private static void dfs(int cnt, int sum) {
        if (cnt == N) {
            if (sum == S) result++;
            return;
        }

        dfs(cnt+1, sum);
        dfs(cnt+1, sum+arr[cnt]);
    }
}