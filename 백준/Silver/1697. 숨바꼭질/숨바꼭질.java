import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 숨바꼭질 */
public class Main {
    // 각 정점에서 뻗어 나갈 수 있는 갈래는 x-1, x+1, x*2
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()); // 현재 위치
        int k = Integer.parseInt(st.nextToken()); // 도달해야 하는 위치

        System.out.println(bfs(n, k));
    }

    private static int bfs(int current, int target) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        queue.add(new int[]{current, 0});
        visited[current] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int pos = curr[0];
            int times = curr[1];

            if (pos == target) return times; // 목적지 도착
            int[] nextSteps = {pos + 1, pos - 1, pos * 2};
            for (int next : nextSteps) {
                if (next >= 0 && next <= visited.length - 1 && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, times + 1});
                }
            }
        }
        return -1;
    }
}
