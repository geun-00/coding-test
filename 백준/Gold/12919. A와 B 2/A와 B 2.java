import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solve(s, t) ? 1 : 0);
    }

    private static boolean solve(String s, String t) {
        if (s.length() == t.length()) {
            return s.equals(t);
        }

        if (t.endsWith("A")) {
            if (solve(s, t.substring(0, t.length() - 1))) {
                return true;
            }
        }

        if (t.startsWith("B")) {
            if (solve(s, new StringBuilder(t.substring(1)).reverse().toString())) {
                return true;
            }
        }

        return false;
    }
}