import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String p = br.readLine();

        System.out.println(KMP(s, p));
    }

    private static int KMP(String parent, String pattern) {
        int[] table = makeTable(pattern);
        int n = parent.length();
        int m = pattern.length();
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    return 1;
                }

                j++;
            }
        }

        return 0;
    }

    static int[] makeTable(String pattern) {
        int length = pattern.length();
        int[] table = new int[length];

        int j = 0;
        for (int i = 1; i < length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }
}