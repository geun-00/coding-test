import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int p, q, x, y;
    static HashMap<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        System.out.println(solve(n));
    }

    private static long solve(long n) {
        if (n <= 0) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long result = solve(n / p - x) + solve(n / q - y);
        map.put(n, result);

        return map.get(n);
    }
}
