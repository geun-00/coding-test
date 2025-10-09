import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            k = Math.max(k, arr[i]);
        }

        boolean[] visit = new boolean[k + 1];
        int prev = -1;

        StringBuilder sb = new StringBuilder();
        sb.append(k + 1).append("\n");

        int[] ans = new int[k + 1];

        for (int i = 0; i < n; i++) {
            if (!visit[arr[i]]) {
                visit[arr[i]] = true;
                ans[arr[i]] = prev;
            }

            prev = arr[i];
        }

        for (int num : ans) {
            sb.append(num).append(" ");
        }

        System.out.print(sb);
    }
}