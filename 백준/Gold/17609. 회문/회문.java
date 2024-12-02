import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            String s = br.readLine();

            sb.append(isPalindrome(s)).append("\n");
        }

        System.out.print(sb);
    }

    private static int isPalindrome(String s) {

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)) {
                    return 1;
                } else {
                    return 2;
                }
            }
            left++;
            right--;
        }

        return 0;
    }

    private static boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
