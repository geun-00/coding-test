import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        solve(n, 0);
        System.out.println(min + " " + max);
    }

    private static void solve(String n, int oddCount) {
        for (char c : n.toCharArray()) {
            if ((c - '0') % 2 == 1) {
                oddCount++;
            }
        }

        if (n.length() == 1) {
            min = Math.min(min, oddCount);
            max = Math.max(max, oddCount);
            return;
        }

        if (n.length() == 2) {
            String next = String.valueOf((n.charAt(0) - '0') + (n.charAt(1) - '0'));
            solve(next, oddCount);
            return;
        }

        for (int i = 1; i < n.length() - 1; i++) {
            for (int j = i + 1; j < n.length(); j++) {
                int a = Integer.parseInt(n.substring(0, i));
                int b = Integer.parseInt(n.substring(i, j));
                int c = Integer.parseInt(n.substring(j));

                String next = String.valueOf(a + b + c);
                solve(next, oddCount);
            }
        }
    }
}