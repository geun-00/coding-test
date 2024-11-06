import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();

                String s = st.nextToken();
                map.put(s, map.getOrDefault(s, 0) + 1);
            }

            int ans = 1;
            for (int num : map.values()) {
                ans *= num + 1;
            }

            sb.append(--ans).append("\n");
        }

        System.out.print(sb);
    }
}
