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

        int[] arr = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            deque.add(i);
        }

        StringBuilder sb = new StringBuilder();

        int prev = 1;
        while (!deque.isEmpty()) {

            int cur;

            if (prev > 0) {
                cur = deque.pollFirst();
                sb.append(cur + 1).append(" ");
            } else {
                cur = deque.pollLast();
                sb.append(cur + 1).append(" ");
            }

            cur = arr[cur];
            if (!deque.isEmpty()) {
                if (cur > 0) {
                    for (int i = 0; i < cur - 1; i++) {
                        deque.offerLast(deque.pollFirst());
                    }
                } else {
                    for (int i = 0; i < Math.abs(cur) - 1; i++) {
                        deque.offerFirst(deque.pollLast());
                    }
                }
            }
            prev = cur;
        }

        System.out.print(sb);
    }
}