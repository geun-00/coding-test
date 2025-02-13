import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int window = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') window++;
        }

        int b = 0;

        for (int i = 0; i < window; i++) {
            if (s.charAt(i) == 'b') b++;
        }
        int ans = b;

        int n = s.length();

        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == 'b') b--;
            if (s.charAt((i + window - 1) % s.length()) == 'b') b++;
            ans = Math.min(ans, b);
        }

        System.out.println(ans);
    }
}