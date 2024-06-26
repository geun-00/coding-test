import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[100_001];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[n] = 0;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(n);

        while (!qu.isEmpty()) {
            int now = qu.poll();

            if (now == k) {
                System.out.println(arr[now]);
                return;
            }


            if (now + 1 <= 100_000 && arr[now + 1] > arr[now] + 1) {
                arr[now + 1] = arr[now] + 1;
                qu.offer(now + 1);
            }
            if (now - 1 >= 0 && arr[now - 1] > arr[now] + 1) {
                arr[now - 1] = arr[now] + 1;
                qu.offer(now - 1);
            }
                        if (now * 2 <= 100_000 && arr[now * 2] > arr[now]) {
                arr[now * 2] = arr[now];
                qu.offer(now * 2);
            }
        }
    }
}
