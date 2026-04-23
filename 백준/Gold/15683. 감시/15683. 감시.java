import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 감시 */
public class Main {
    static int N, M, invisibleArea = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static List<int[]> list;

    private static int[][] copyMap(int[][] src) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            newMap[i] = src[i].clone();
        }
        return newMap;
    }

    public static void main(String[] args) {
        // 사각지대 최소 크기 구하기!
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0 && map[i][j] != 6)
                    list.add(new int[] { map[i][j], i, j });
            }
        }

        dfs(0);
        System.out.println(invisibleArea);
    }

    private static void dfs(int cnt) {
        if (cnt == list.size()) {
            check();
            return;
        }

        int[] cur = list.get(cnt);
        if (cur[0] == 1) {
            for (int i = 0; i < 4; i++) {
                int[][] tempMap = copyMap(map);
                int nx;
                int ny;
                if (i == 2 || i == 3) {
                    nx = cur[1] + dx[i];
                    ny = cur[2];
                } else {
                    nx = cur[1];
                    ny = cur[2] + dy[i];
                }

                while (true) {
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                        break;
                    if (map[nx][ny] == 6)
                        break;
                    map[nx][ny] = 7;
                    nx += dx[i];
                    ny += dy[i];
                }

                dfs(cnt + 1);
                map = tempMap;
            }
        }

        if (cur[0] == 2) {
            // 좌우 DFS
            int[][] tempMap = copyMap(map);
            int nx = cur[1] + dx[3];
            int ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }
            dfs(cnt + 1);
            map = tempMap;

            // 상하 DFS
            tempMap = copyMap(map);
            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }
            dfs(cnt + 1);
            map = tempMap;
        }

        if (cur[0] == 3) {
            // 상 우
            int[][] tempMap = copyMap(map);
            int nx = cur[1] + dx[3];
            int ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }
            dfs(cnt + 1);
            map = tempMap;

            // 우 하
            tempMap = copyMap(map);
            nx = cur[1] + dx[3];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }
            dfs(cnt + 1);
            map = tempMap;

            // 좌 하
            tempMap = copyMap(map);
            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }
            dfs(cnt + 1);
            map = tempMap;

            // 좌 상
            tempMap = copyMap(map);
            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }
            dfs(cnt + 1);
            map = tempMap;
        }

        if (cur[0] == 4) {
            // 상 우 좌
            int[][] tempMap = copyMap(map);
            int nx = cur[1] + dx[3];
            int ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }

            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            dfs(cnt + 1);
            map = tempMap;

            // 우 하 상
            tempMap = copyMap(map);
            nx = cur[1] + dx[3];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }

            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }

            dfs(cnt + 1);
            map = tempMap;

            // 좌 하 우
            tempMap = copyMap(map);
            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }

            nx = cur[1] + dx[3];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }

            dfs(cnt + 1);
            map = tempMap;

            // 좌 상 하
            tempMap = copyMap(map);
            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }

            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }

            dfs(cnt + 1);
            map = tempMap;
        }

        if (cur[0] == 5) {
            int[][] tempMap = copyMap(map);
            // 우
            int nx = cur[1] + dx[3];
            int ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[3];
            }
            // 좌
            nx = cur[1] + dx[2];
            ny = cur[2];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                nx += dx[2];
            }

            // 상
            nx = cur[1];
            ny = cur[2] + dy[0];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[0];
            }

            // 하
            nx = cur[1];
            ny = cur[2] + dy[1];
            while (true) {
                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    break;
                if (map[nx][ny] == 6)
                    break;
                map[nx][ny] = 7;
                ny += dy[1];
            }
            dfs(cnt + 1);
            map = tempMap;
        }
    }

    private static void check() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    total++;
            }
        }
        if (total < invisibleArea)
            invisibleArea = total;
    }
}
