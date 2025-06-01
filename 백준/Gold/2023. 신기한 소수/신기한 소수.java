import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        solve(2, 1, n);
        solve(3, 1, n);
        solve(5, 1, n);
        solve(7, 1, n);
    }

    private static void solve(int num, int depth, int n) {
        if (!isPrime(num)) {
            return;
        }

        if (depth == n) {
            System.out.println(num);
            return;
        }

        for (int i = 1; i < 10; i++) {
            solve(num * 10 + i, depth + 1, n);
        }
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}