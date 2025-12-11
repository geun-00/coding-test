import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] b = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        long diff = 0;
        long ans = 0;
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);

        for (int i = 0; i < n; i++) {
            diff += a[i] - b[i];
            ans += map.getOrDefault(diff, 0L);
            map.put(diff, map.getOrDefault(diff, 0L) + 1);
        }

        System.out.println(ans);
    }
}