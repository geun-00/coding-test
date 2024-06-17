import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static  HashMap<Long, Long> map = new HashMap<>();
    static int p, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map.put(0L, 1L);

        System.out.println(solve(n));

    }

    private static long solve(long n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        long result = solve(n / p) + solve(n / q);
        map.put(n, result);

        return result;
    }
}
