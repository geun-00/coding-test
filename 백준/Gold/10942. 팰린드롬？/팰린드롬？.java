import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] palindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
            if (i < n - 1) {
                if (arr[i] == arr[i + 1]) {
                    palindrome[i][i + 1] = true;
                }
            }
        }

        for (int s = n - 3; s >= 0; s--) {
            for (int e = s + 2; e < n; e++) {
                if (arr[s] == arr[e] && palindrome[s + 1][e - 1]) {
                    palindrome[s][e] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            sb.append(palindrome[s][e] ? 1 : 0).append("\n");
        }

        System.out.print(sb);
    }
}
