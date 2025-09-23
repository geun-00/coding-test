import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(solve(s) ? "AKARAKA" : "IPSELENTI");
    }

    private static boolean solve(String s) {
        if (s.length() == 1) {
            return true;
        }

        if (!isPalindrome(s)) {
            return false;
        }

        int mid = s.length() / 2;
        String head = s.substring(0, mid);
        String tail = s.substring(s.length() - mid);

        return solve(head) && solve(tail);
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
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