import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n <= 20) {
            solve(n, 1, 3);
        }

        System.out.println(BigInteger.TWO.pow(n).subtract(BigInteger.ONE));
        System.out.print(sb);
    }

    private static void solve(int n, int from, int to) {
        if (n == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }

        int empty = 6 - from - to;

        solve(n - 1, from, empty);
        solve(1, from, to);
        solve(n - 1, empty, to);
    }
}