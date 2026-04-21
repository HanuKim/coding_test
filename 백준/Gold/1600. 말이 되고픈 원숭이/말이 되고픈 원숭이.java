import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] hx = {-1, -2, 1, 2, -2, -1, 2, 1};
    static int[] hy = {-2, -1, -2, -1, 1, 2, 1, 2};

    static class Node {
        int x, y, k, dist;
        Node (int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        W = sc.nextInt();
        H = sc.nextInt();
        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(dfs());
    }

    static int dfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, K, 0));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == H-1 && cur.y == W-1) {
                return cur.dist;
            }

            if (cur.k > 0) {
                for (int d = 0; d < 8; d++) {
                    int nx = cur.x + hx[d];
                    int ny = cur.y + hy[d];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if (map[nx][ny] == 1 || visited[nx][ny][cur.k - 1]) continue;
                    visited[nx][ny][cur.k - 1] = true;
                    q.offer(new Node(nx, ny, cur.k - 1, cur.dist + 1));
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if (map[nx][ny] == 1 || visited[nx][ny][cur.k]) continue;
                visited[nx][ny][cur.k] = true;
                q.offer(new Node(nx, ny, cur.k, cur.dist + 1));
            }
        }
        return -1;
    }
}
