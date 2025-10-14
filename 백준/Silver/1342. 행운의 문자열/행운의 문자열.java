import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Character, Integer> freq = new HashMap<>();
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        dfs(0, s, '-');
        System.out.println(ans);
    }

    private static void dfs(int depth, String s, char prev) {
        if (depth == s.length()) {
            ans++;
            return;
        }

        for (Character c : freq.keySet()) {
            int r = freq.get(c);
            if (r == 0 || c == prev) {
                continue;
            }

            freq.put(c, r - 1);
            dfs(depth + 1, s, c);
            freq.put(c, r);
        }
    }
}