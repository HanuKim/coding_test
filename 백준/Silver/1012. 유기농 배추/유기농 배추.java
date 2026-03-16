import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, K, result;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][M];
            visited = new boolean[N][M];
            result = 0;
            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        result++;
                    }
                }
            }

            System.out.println(result);
        }
    }

    private static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] == 0 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}