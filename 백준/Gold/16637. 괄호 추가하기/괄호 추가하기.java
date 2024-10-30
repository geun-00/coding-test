import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[] arr;
    static int n;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = br.readLine().toCharArray();

        solve(arr[0] - '0', 1);

        System.out.println(max);
    }

    private static void solve(int sum, int op) {
        if (op >= n) {
            max = Math.max(max, sum);
            return;
        }

        int next = calc(sum, arr[op], arr[op + 1] - '0');
        solve(next, op + 2);

        if (op + 2 < n) {
            int result = calc(arr[op + 1] - '0', arr[op + 2], arr[op + 3] - '0');
            next = calc(sum, arr[op], result);
            solve(next, op + 4);
        }
    }

    private static int calc(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}
