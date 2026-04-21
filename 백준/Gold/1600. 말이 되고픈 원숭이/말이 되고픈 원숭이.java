import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] hx = {-1, -2, 1, 2, -2, -1, 1, 2};
    static int[] hy = {-2, -1, -2, -1, 1, 2, 2, 1};

    static class Node {
        int x, y, horse, len;

        Node(int x, int y, int horse, int len) {
            this.x = x;
            this.y = y;
            this.horse = horse;
            this.len = len;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        W = sc.nextInt();
        H = sc.nextInt();

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, K, 0));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == H - 1 && cur.y == W - 1) {
                return cur.len;
            }

            if (cur.horse > 0) {
                for (int h = 0; h < 8; h++) {
                    int nhx = cur.x + hx[h];
                    int nhy = cur.y + hy[h];
                    if (nhx < 0 || nhy < 0 || nhx >= H || nhy >= W) continue;
                    if (map[nhx][nhy] == 1) continue;
                    if (visited[nhx][nhy][cur.horse - 1]) continue;
                    visited[nhx][nhy][cur.horse - 1] = true;
                    q.offer(new Node(nhx, nhy, cur.horse - 1, cur.len + 1));
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny][cur.horse]) continue;
                visited[nx][ny][cur.horse] = true;
                q.offer(new Node(nx, ny, cur.horse, cur.len + 1));
            }
        }

        return -1;
    }
}
