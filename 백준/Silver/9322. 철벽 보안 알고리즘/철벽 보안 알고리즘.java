import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] k1 = br.readLine().split(" ");
            String[] k2 = br.readLine().split(" ");
            String[] secret = br.readLine().split(" ");

            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(k2[i], i);
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < n; i++) {
                int index = map.get(k1[i]);
                result.append(secret[index]).append(" ");
            }
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}