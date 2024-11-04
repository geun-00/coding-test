import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] col;
    static int n;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        col = new int[n];

        solve(0);

        System.out.println(count);
    }

    private static void solve(int idx) {

        if (idx == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            col[idx] = i;
            if (check(idx)) {
                solve(idx + 1);
            }
        }
    }

    private static boolean check(int idx) {

        for (int i = 0; i < idx; i++) {
            if (col[idx] == col[i] || Math.abs(col[idx] - col[i]) == Math.abs(idx - i)) {
                return false;
            }
        }

        return true;
    }
}