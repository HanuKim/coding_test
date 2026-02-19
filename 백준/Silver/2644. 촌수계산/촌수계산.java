import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/* 촌수 계산 */
public class Main {
    // 촌수는 정점을 연결하는 간선의 수와 같음!!
    // 조상 -> 자손, 자손 -> 조상 방향으로 모두 이동할 수 있어야 함 => 양방향 그래프를 사용하자
    private static int N;
    private static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정점의 개수

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine()); // 관계의 개수
        
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken()); // 조상
            int c = Integer.parseInt(st.nextToken()); // 자손
            // 양방향으로 연결하자
            adj[p].add(c);
            adj[c].add(p);
        }

        System.out.println(bfs(x, y));
    }

    private static int bfs(int start, int target) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0]; // 현재 정점 위치
            int dist = curr[1]; // 현재 간선 거리

            if (pos == target) return dist;

            // 연결된 정점을 큐에 담으면서 거슬러 올라가보자..
            for (int next : adj[pos]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, dist + 1});
                }
            }
        }
        return -1; // 연결할 수 없는 경우
    }
}