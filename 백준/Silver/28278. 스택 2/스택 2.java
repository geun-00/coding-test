import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());

            if (q == 1) {
                stack.push(Integer.parseInt(st.nextToken()));
            } else if (q == 2) {
                if (!stack.isEmpty()) {
                    sb.append(stack.pop());
                } else {
                    sb.append(-1);
                }
                sb.append("\n");
            } else if (q == 3) {
                sb.append(stack.size()).append("\n");
            } else if (q == 4) {
                if (!stack.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(1);
                }
                sb.append("\n");
            } else {
                if (!stack.isEmpty()) {
                    sb.append(stack.peek());
                } else {
                    sb.append(-1);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}