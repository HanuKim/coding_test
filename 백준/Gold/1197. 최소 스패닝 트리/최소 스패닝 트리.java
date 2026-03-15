import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int V, E;
    static int[] parents;
    static int[][] edges;

    // 정점 초기화
    private static void makeSet() {
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    // 부모 정점 찾기
    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    // 트리 합치기
    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;
        parents[pb] = pa;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        parents = new int[V];
        edges = new int[E][3];
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int weight = sc.nextInt();
            edges[i] = new int[]{a - 1 , b - 1, weight};
        }
        makeSet();
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int count = 0;
        int result = 0;
        for (int i = 0; i < E; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int weight = edges[i][2];
            if (union(a,b)) {
                result += weight;
                count++;
            }
            if (count == V - 1) break;
        }
        System.out.println(result);
    }
}
