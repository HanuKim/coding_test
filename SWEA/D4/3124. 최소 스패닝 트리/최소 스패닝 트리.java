import java.util.*;

/* 최소 스패닝 트리 - 프림 */
public class Solution {
    private static class Node implements Comparable<Node> {
        int v;
        int weight;
        Node (int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static int V, E;
    static List<Node>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            V = sc.nextInt();
            E = sc.nextInt();
            graph = new ArrayList[V];
            visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                int w = sc.nextInt();

                graph[a].add(new Node(b, w));
                graph[b].add(new Node(a, w));
            }

            long result = prim();
            System.out.println("#" + tc + " " + result);
        }
    }

    private static long prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        long total = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.v]) continue;

            visited[cur.v] = true;
            total += cur.weight;
            count++;

            if (count == V) break;

            for (Node next : graph[cur.v]) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }
        return total;
    }
}
