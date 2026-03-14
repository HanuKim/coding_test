import java.util.Scanner;

/* 수지의 수지 맞는 여행 */
public class Solution {
    static int R, C, result;
    static char[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[] alphabet;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            R = sc.nextInt();
            C = sc.nextInt();
            result = Integer.MIN_VALUE;
            map = new char[R][C];
            alphabet = new boolean[26]; // 방문할 수 있는 곳은 알파벳 대문자 26개로 정해져 있음
            for (int i = 0; i < R; i++) {
                map[i] = sc.next().toCharArray();
            }

            int idx = map[0][0] - 'A';
            alphabet[idx] = true;
            dfs(0, 0, 1);
            System.out.println("#" + tc + " " + result);
        }
    }

    private static void dfs(int i, int j, int depth) {
        result = Math.max(depth, result);

        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            int idx = map[nx][ny] - 'A';
            if (alphabet[idx]) continue;
            alphabet[idx] = true;
            dfs(nx, ny , depth+1);
            alphabet[idx] = false;
        }
    }
}
