import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] ans = new int[26];
        String s = br.readLine();

        for (char c = 'a'; c <= 'z'; c++) {
            ans[c - 'a'] = getIndex(c, s);
        }

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static int getIndex(char target, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                return i;
            }
        }
        return -1;
    }
}
