import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] adj;
    static int[] visited; // 0: 미방문, 1: 방문 중(Stack에 있음), 2: 방문 완료
    static Stack<Integer> stack = new Stack<>();
    static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        visited = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            if (count == 0) continue;

            int u = Integer.parseInt(st.nextToken());
            for (int j = 1; j < count; j++) {
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                u = v;
            }
        }

        // 모든 노드에 대해 DFS 수행
        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        // 사이클이 발견되었다면 0 출력, 아니면 스택 내용 출력
        if (hasCycle) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append("\n");
            }
            System.out.print(sb);
        }
    }

    static void dfs(int curr) {
        if (hasCycle) return;

        visited[curr] = 1; // 현재 노드를 '방문 중' 상태로 표시

        for (int next : adj[curr]) {
            if (visited[next] == 1) { // 탐색 경로 상의 노드를 다시 만남 = 사이클
                hasCycle = true;
                return;
            }
            if (visited[next] == 0) {
                dfs(next);
            }
        }

        visited[curr] = 2; // 탐색 완료
        stack.push(curr);  // 역순 출력을 위해 스택에 삽입
    }
}