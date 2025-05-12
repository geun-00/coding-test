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
        int m = Integer.parseInt(st.nextToken());

        Deque<Integer> qu = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            qu.offer(i);
        }

        int ans = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            int index = 0;
            for (int num : qu) {
                if (num == target) {
                    break;
                }
                index++;
            }

            if (index <= (qu.size() / 2)) {
                while (!qu.isEmpty() && qu.peekFirst() != target) {
                    ans++;
                    qu.offerLast(qu.pollFirst());
                }
            } else {
                while (!qu.isEmpty() && qu.peekFirst() != target) {
                    ans++;
                    qu.offerFirst(qu.pollLast());
                }
            }

            qu.pollFirst();
        }

        System.out.println(ans);
    }
}