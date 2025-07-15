import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int len = s.length();
        String ans = "z".repeat(len);

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                String first = reverse(s.substring(0, i + 1));
                String second = reverse(s.substring(i + 1, j + 1));
                String third = reverse(s.substring(j + 1));

                String result = first + second + third;

                if (ans.compareTo(result) > 0) {
                    ans = result;
                }
            }
        }

        System.out.println(ans);
    }

    private static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}