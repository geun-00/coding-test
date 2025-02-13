import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            solve(1, 1, 0, 1, "1", n);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int index, int num, int sum, int operator, String expr, int n) {
        if (index == n) {
            sum += (num * operator);
            if (sum == 0) {
                sb.append(expr).append("\n");
            }
            return;
        }

        solve(index + 1, num * 10 + (index + 1), sum, operator, expr + " " + (index + 1), n);
        solve(index + 1, index + 1, sum + (num * operator), 1, expr + "+" + (index + 1), n);
        solve(index + 1, index + 1, sum + (num * operator), -1, expr + "-" + (index + 1), n);
    }
}