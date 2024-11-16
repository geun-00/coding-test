import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] empty;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        StringBuilder sb = new StringBuilder();

        while ((input = br.readLine()) != null && !input.isEmpty()) {

            int n = Integer.parseInt(input);

            int len = (int) Math.pow(3, n);

            empty = new boolean[len];

            solve(0, len);

            for (int i = 0; i < len; i++) {
                sb.append(empty[i] ? " " : "-");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solve(int s, int e) {
        
        if (e - s == 1) {
            return;
        }

        int mid = (e - s) / 3;

        for (int i = s + mid; i < s + 2 * mid; i++) {
            empty[i] = true;
        }

        solve(s, s + mid);
        solve(s + 2 * mid, e);
    }
}