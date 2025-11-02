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

        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int num = 1;

        for (int i = n - 1; i >= 0; i--) {
            int x = arr[i];

            if (x == 1) {
                deque.offerFirst(num);
            } else if (x == 2) {
                Integer temp = deque.pollFirst();
                deque.offerFirst(num);
                deque.offerFirst(temp);
            } else if (x == 3) {
                deque.offerLast(num);
            }

            num++;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.poll()).append(" ");
        }

        System.out.print(sb);
    }
}