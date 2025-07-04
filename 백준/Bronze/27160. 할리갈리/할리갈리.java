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

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            map.put(s, map.getOrDefault(s, 0) + x);
        }

        String ans = "NO";
        String[] fruits = {"STRAWBERRY", "BANANA", "LIME", "PLUM"};

        for (String fruit : fruits) {
            if (map.getOrDefault(fruit, 0) == 5) {
                ans = "YES";
                break;
            }
        }

        System.out.println(ans);
    }
}