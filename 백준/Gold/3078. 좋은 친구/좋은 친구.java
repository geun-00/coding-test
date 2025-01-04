import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> window = new ArrayDeque<>();
        int[] count = new int[21];

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int length = br.readLine().length();

            if (!window.isEmpty() && window.size() > k) {
                count[window.poll()]--;
            }

            ans += count[length];

            window.offer(length);
            count[length]++;
        }

        System.out.println(ans);
    }
}