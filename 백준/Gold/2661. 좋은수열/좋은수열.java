import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        solve("", n);
    }

    private static boolean solve(String num, int n) {
        if (!check(num)) {
            return false;
        }

        if (num.length() == n) {
            System.out.println(num);
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            if (solve(num + i, n)) {
                return true;
            }
        }

        return false;
    }

    private static boolean check(String num) {
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {

            for (int j = 0; j <= len - 2 * i; j++) {
                String left = num.substring(j, j + i);
                String right = num.substring(j + i);
                if (left.equals(right)) {
                    return false;
                }
            }
        }

        return true;
    }
}