import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int n = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                if (solve(j, s, input)) {
                    ans++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean solve(int start, String s, String input) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != input.charAt((start + i) % input.length())) {
                return false;
            }
        }

        return true;
    }
}