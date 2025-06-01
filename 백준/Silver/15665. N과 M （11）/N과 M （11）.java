import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] pick;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .sorted()
                          .toArray();

        pick = new int[m];
        solve(n, m, arr, 0);

        System.out.print(ans);
    }

    private static void solve(int n, int m, int[] arr, int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i : pick) {
                sb.append(arr[i]).append(" ");
            }

            ans.append(sb).append("\n");
            return;
        }

        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (prev != arr[i]) {
                prev = arr[i];
                pick[depth] = i;
                solve(n, m, arr, depth + 1);
            }
        }
    }
}