import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;
    static int ans = Integer.MIN_VALUE;
    static int[] order;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        order = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0, 0);

        System.out.println(ans);
    }

    private static void backTrack(int depth, int visit) {
        if (depth == n) {

            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs((arr[order[i]] - arr[order[i + 1]]));
            }
            ans = Math.max(ans, sum);

            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) != 0) {
                continue;
            }

            order[depth] = i;
            backTrack(depth + 1, visit | (1 << i));
        }
    }
}