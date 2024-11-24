import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            String s = br.readLine();

            if ("end".equals(s)) {
                break;
            }

            String ans = "is not acceptable.";

            if (checkOne(s) && checkTwo(s) && checkThree(s)) {
                ans = "is acceptable.";
            }

            sb.append("<").append(s).append("> ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean checkOne(String s) {

        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }

        return false;
    }

    private static boolean checkTwo(String s) {

        int v = 0, c = 0;

        for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                v++;
                if (v >= 3) {
                    return false;
                }
                c = 0;
            } else {
                c++;
                if (c >= 3) {
                    return false;
                }
                v = 0;
            }
        }

        return true;
    }

    private static boolean checkThree(String s) {

        for (int i = 0; i < s.length() - 1; i++) {

            if (s.charAt(i) == s.charAt(i + 1) && (s.charAt(i) != 'e' && s.charAt(i) != 'o')) {
                return false;
            }
        }

        return true;
    }
}