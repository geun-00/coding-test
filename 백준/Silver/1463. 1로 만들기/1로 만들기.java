import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        solve(n, 0);

        System.out.println(min);
    }

    private static void solve(int n, int count) {
        if (count >= min) {
            return;
        }
        if (n == 1) {
            min = count;
            return;
        }

        if (n % 3 == 0) {
            solve(n / 3, count + 1);
        }
        if (n % 2 == 0) {
            solve(n / 2, count + 1);
        }
        if (n - 1 > 0) {
            solve(n - 1, count + 1);
        }
    }
}