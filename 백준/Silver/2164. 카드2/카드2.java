import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Deque<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            qu.offer(i + 1);
        }

        while (qu.size() > 1) {
            qu.poll();

            if (!qu.isEmpty()) {
                qu.offer(qu.poll());
            }
        }

        System.out.println(qu.poll());
    }
}
