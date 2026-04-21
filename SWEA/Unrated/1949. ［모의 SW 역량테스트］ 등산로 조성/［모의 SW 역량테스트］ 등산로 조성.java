import java.util.Scanner;

public class Solution {
    static int N, K, maxLen;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 테스트 케이스 수 입력
        int T = sc.hasNextInt() ? sc.nextInt() : 0;

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            visited = new boolean[N][N];
            maxLen = 0;

            int maxHeight = 0;
            // 지형 정보 입력 및 최대 높이 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] > maxHeight) {
                        maxHeight = map[i][j];
                    }
                }
            }

            // 모든 칸을 돌며 최고봉에서만 DFS 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, map[i][j], 1, false);
                        visited[i][j] = false; // 백트래킹 (원상 복구)
                    }
                }
            }

            System.out.println("#" + t + " " + maxLen);
        }
        sc.close();
    }

    /**
     * @param r, c: 현재 위치 좌표
     * @param h: 현재 위치의 높이 (이미 깎였을 경우 깎인 높이)
     * @param dist: 현재까지 등산로의 길이
     * @param used: 지형을 깎는 공사를 이미 수행했는지 여부
     */
    static void dfs(int r, int c, int h, int dist, boolean used) {
        // 최대 길이 갱신
        if (dist > maxLen) {
            maxLen = dist;
        }

        // 4방향 탐색
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 경계 밖이거나 이미 방문한 곳은 제외
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

            // 1. 공사 없이 갈 수 있는 경우 (다음 칸이 더 낮을 때)
            if (map[nr][nc] < h) {
                visited[nr][nc] = true;
                dfs(nr, nc, map[nr][nc], dist + 1, used);
                visited[nr][nc] = false;
            } 
            // 2. 공사가 필요한 경우 (아직 공사 전이고, 깎아서 현재 칸보다 낮아질 수 있을 때)
            else if (!used && map[nr][nc] - K < h) {
                visited[nr][nc] = true;
                // 현재 높이(h)보다 딱 1만큼만 낮게 깎는 것이 다음 경로 확보에 가장 유리함
                dfs(nr, nc, h - 1, dist + 1, true);
                visited[nr][nc] = false;
            }
        }
    }
}