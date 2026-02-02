import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1과 연결된 사람 + 1과 연결된 사람과 연결된 사람
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 사람 수
        int m = sc.nextInt(); // 관계 수

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[n + 1];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{1, 0}); // {사람, 깊이}
        visited[1] = true;

        int result = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int person = cur[0];
            int depth = cur[1];

            if (depth >= 2) continue;

            for (int next : graph[person]) {
                if (!visited[next]) {
                    visited[next] = true;
                    result++;
                    q.add(new int[]{next, depth + 1});
                }
            }
        }

        System.out.println(result);
        sc.close();
    }
}
