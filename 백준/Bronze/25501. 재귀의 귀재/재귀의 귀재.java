import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int callCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            callCount = 0;
            String s = br.readLine();

            int ans = isPalindrome(s, 0, s.length() - 1);
            System.out.println(ans + " " + callCount);
        }
    }

    private static int isPalindrome(String s, int l, int r) {
        callCount++;

        if (l >= r) return 1;
        else if (s.charAt(l) != s.charAt(r)) return 0;
        else return isPalindrome(s, l + 1, r - 1);
    }
}