import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;
    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        backTrack(0, 0);

        System.out.print(sb);
    }

    private static void backTrack(int depth, int visit) {
        if (depth == n) {

            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) != 0) {
                continue;
            }
            arr[depth] = i + 1;

            backTrack(depth + 1, visit | (1 << i));
        }
    }
}
