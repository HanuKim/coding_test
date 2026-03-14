import java.util.Arrays;
import java.util.Scanner;

/* 하나로-프림 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] x = new int[N];
            int[] y = new int[N];
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                y[i] = sc.nextInt();
            }

            double E = sc.nextDouble();
            boolean[] visited = new boolean[N];
            long[] minEdge = new long[N];

            Arrays.fill(minEdge, Long.MAX_VALUE);
            minEdge[0] = 0;
            long result = 0;

            for (int i = 0; i < N; i++) {
                long min = Long.MAX_VALUE;
                int minVertex = -1;
                for (int j = 0; j < N; j++) {
                    if (!visited[j] && minEdge[j] < min) {
                        min = minEdge[j];
                        minVertex = j;
                    }
                }

                visited[minVertex] = true;
                result += min;
                for (int j = 0; j < N; j++) {
                    if (!visited[j]) {
                        long dx = x[minVertex] - x[j];
                        long dy = y[minVertex] - y[j];

                        long cost = dx*dx + dy*dy;
                        if (minEdge[j] > cost) {
                            minEdge[j] = cost;
                        }
                    }
                }
            }
            long answer = Math.round(result * E);
            System.out.println("#" + tc + " " + answer);
        }
    }
}
