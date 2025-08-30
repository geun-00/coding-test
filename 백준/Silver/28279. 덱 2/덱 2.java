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
        StringBuilder sb = new StringBuilder();

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int x;

            switch (command) {
                case 1:
                    x = Integer.parseInt(st.nextToken());
                    deque.offerFirst(x);
                    break;
                case 2:
                    x = Integer.parseInt(st.nextToken());
                    deque.offerLast(x);
                    break;
                case 3:
                    sb.append(!deque.isEmpty() ? deque.pollFirst() : -1)
                      .append("\n");
                    break;
                case 4:
                    sb.append(!deque.isEmpty() ? deque.pollLast() : -1)
                      .append("\n");
                    break;
                case 5:
                    sb.append(deque.size()).append("\n");
                    break;
                case 6:
                    sb.append(deque.isEmpty() ? 1 : 0)
                      .append("\n");
                    break;
                case 7:
                    sb.append(!deque.isEmpty() ? deque.peekFirst() : -1)
                      .append("\n");
                    break;
                case 8:
                    sb.append(!deque.isEmpty() ? deque.peekLast() : -1)
                      .append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }
}