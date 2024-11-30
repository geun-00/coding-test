import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, temp;
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        temp = new int[m];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solve(0, 0);

        System.out.print(sb);
    }

    private static void solve(int depth, int s) {
        if (depth == m) {
            for (int i : temp) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = s; i < n; i++) {
            temp[depth] = arr[i];
            solve(depth + 1, i);
        }
    }
}