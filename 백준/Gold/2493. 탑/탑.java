import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] result = new int[N];

        Deque<Integer> stack = new ArrayDeque<>();
        int[] heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {

            while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek() + 1;
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int x : result) {
            sb.append(x).append(" ");
        }

        System.out.println(sb);
    }
}