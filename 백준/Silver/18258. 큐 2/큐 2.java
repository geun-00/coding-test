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

        Deque<Integer> qu = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String query = st.nextToken();

            switch (query) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    qu.offer(x);
                    break;
                case "pop":
                    sb.append(qu.isEmpty() ? -1 : qu.poll())
                      .append("\n");
                    break;
                case "size":
                    sb.append(qu.size())
                      .append("\n");
                    break;
                case "empty":
                    sb.append(qu.isEmpty() ? 1 : 0)
                      .append("\n");
                    break;
                case "front":
                    sb.append(qu.isEmpty() ? -1 : qu.peek())
                      .append("\n");
                    break;
                case "back":
                    sb.append(qu.isEmpty() ? -1 : qu.peekLast())
                      .append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }
}
