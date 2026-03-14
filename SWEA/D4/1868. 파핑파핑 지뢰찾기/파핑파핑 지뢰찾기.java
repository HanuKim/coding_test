import java.util.Scanner;

/* 파핑파핑 지뢰찾기 */
public class Solution {
    static int N, count;
    static char[][] map;
    static boolean[][] clicked;
    // 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하
    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) {
        // 8방향에 지뢰가 없는 영역 + 아닌 영역
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new char[N][N];
            clicked = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = sc.next().toCharArray();
            }

            count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !clicked[i][j] && isClear(i, j)) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !clicked[i][j]) {
                        count++;
                    }
                }
            }

            System.out.println("#" + tc + " " + count);
        }
    }

    private static void dfs(int x, int y) {
        clicked[x][y] = true;

        if (!isClear(x, y)) return;
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (clicked[nx][ny]) continue;
            if (map[nx][ny] == '*') continue;
            dfs(nx, ny);
        }
    }

    private static boolean isClear(int x, int y) {
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (map[nx][ny] == '*') return false;
        }

        return true;
    }
}