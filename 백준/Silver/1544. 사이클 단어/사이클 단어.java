import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Set<String> wordSet = new HashSet<>();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            boolean first = false;

            for (int j = 0; j < s.length(); j++) {
                if (wordSet.add(s)) {
                    first = true;
                }
                s = s.substring(1) + s.charAt(0);
            }

            if (first) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}