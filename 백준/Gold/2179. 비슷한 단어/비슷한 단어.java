import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int maxLen = Integer.MIN_VALUE;
        String s = "";
        String t = "";

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = getLen(arr[i], arr[j]);
                if (maxLen < len) {
                    maxLen = len;
                    s = arr[i];
                    t = arr[j];
                }
            }
        }

        System.out.println(s);
        System.out.println(t);
    }

    private static int getLen(String a, String b) {

        int count = 0;

        for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if (a.charAt(i) != b.charAt(i)) break;
            count++;
        }

        return count;
    }
}
