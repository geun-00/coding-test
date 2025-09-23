import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        System.out.println(solve(s.toCharArray(), 0, s.length() - 1) ? "AKARAKA" : "IPSELENTI");
    }

    private static boolean solve(char[] arr, int left, int right) {
        int length = right - left + 1;

        if (length == 1) {
            return true;
        }

        if (!isPalindrome(arr, left, right)) {
            return false;
        }

        int half = length / 2;

        return solve(arr, left, left + half - 1) && solve(arr, right - half + 1, right);
    }

    private static boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}