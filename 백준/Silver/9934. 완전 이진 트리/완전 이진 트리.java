import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder[] sb;
    static int[] arr;
    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());

        sb = new StringBuilder[k];

        for (int i = 0; i < k; i++) {
            sb[i] = new StringBuilder();
        }

        int n = (1 << k) - 1;

        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, n - 1);

        StringBuilder ans = new StringBuilder();

        for (StringBuilder sb : sb) {
            ans.append(sb).append("\n");
        }

        System.out.println(ans);
    }

    private static void solve(int depth, int s, int e) {

        if (depth >= k) {

            return;
        }

        int mid = (s + e) / 2;
        sb[depth].append(arr[mid]).append(" ");

        solve(depth + 1, s, mid - 1);
        solve(depth + 1, mid + 1, e);
    }
}