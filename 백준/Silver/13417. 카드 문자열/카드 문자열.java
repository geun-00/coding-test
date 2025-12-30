import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);

            Deque<Character> deque = new ArrayDeque<>();
            deque.offer(alpha);

            for (int i = 1; i < n; i++) {
                alpha = st.nextToken().charAt(0);
                char left = deque.peekFirst();

                if (left - 'A' >= alpha - 'A') {
                    deque.offerFirst(alpha);
                } else {
                    deque.offer(alpha);
                }
            }

            while (!deque.isEmpty()) {
                sb.append(deque.poll());
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}