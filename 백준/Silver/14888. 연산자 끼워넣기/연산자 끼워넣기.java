import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int add = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int multiple = Integer.parseInt(st.nextToken());
        int divide = Integer.parseInt(st.nextToken());

        solve(arr[0], 1, add, minus, multiple, divide);

        System.out.println(max);
        System.out.println(min);
    }

    private static void solve(int sum, int depth, int add, int minus, int multiple, int divide) {
        if (depth >= n) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        if (add > 0) {
            solve(sum + arr[depth], depth + 1, add - 1, minus, multiple, divide);
        }

        if (minus > 0) {
            solve(sum - arr[depth], depth + 1, add, minus - 1, multiple, divide);
        }

        if (multiple > 0) {
            solve(sum * arr[depth], depth + 1, add, minus, multiple - 1, divide);
        }

        if (divide > 0) {
            solve(sum / arr[depth], depth + 1, add, minus, multiple, divide - 1);
        }
    }
}