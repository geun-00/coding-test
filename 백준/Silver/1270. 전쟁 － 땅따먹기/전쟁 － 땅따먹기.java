import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());

            HashMap<Long, Integer> map = new HashMap<>();
            for (int j = 0; j < t; j++) {
                long num = Long.parseLong(st.nextToken());
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            boolean found = false;

            for (Long key : map.keySet()) {
                if (map.get(key) > (t / 2)) {
                    sb.append(key);
                    found = true;
                    break;
                }
            }

            if (!found) {
                sb.append("SYJKGW");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}