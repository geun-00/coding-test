import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        int k = 0;
        long len = 3;

        while (len < n) {
            k++;
            len = len * 2 + (k + 3);
        }

        System.out.println(solve(n, k, len));
    }

    private static char solve(long n, int k, long len) {
        if (k == 0) {
            return "moo".charAt((int) (n - 1));
        }

        long prevLen = (len - (k + 3)) / 2;
        long ms = prevLen + 1;
        long me = prevLen + (k + 3);

        if (n <= prevLen) {
            return solve(n, k - 1, prevLen);
        } else if (ms <= n && n <= me) {
            return (n == ms) ? 'm' : 'o';
        } else {
            return solve(n - me, k - 1, prevLen);
        }
    }
}