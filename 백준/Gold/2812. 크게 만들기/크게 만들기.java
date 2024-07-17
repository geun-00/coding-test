import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();

        String s = br.readLine();

        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';

            while (!stack.isEmpty() && stack.peek() < num && 0 < k) {
                stack.pop();
                k--;
            }

            stack.push(num);
        }

        while (0 < k) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        System.out.println(sb);
    }
}
