import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int min = Math.abs(n - 100);

        for (int i = 0; i <= 1_000_000; i++) {

            if (check(i)) {
                min = Math.min(min, Math.abs(n - i) + String.valueOf(i).length());
            }
        }

        System.out.println(min);
    }

    private static boolean check(int num) {

        if (num == 0) {
            return !broken[0];
        }

        while (num > 0) {
            if (broken[num % 10]) {
                return false;
            }

            num /= 10;
        }

        return true;
    }
}