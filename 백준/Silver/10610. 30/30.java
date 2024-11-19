import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        int sum = 0;
        boolean zero = false;

        for (char c : arr) {

            sum += c - '0';
            if (c == '0') {
                zero = true;
            }
        }

        if (!zero || sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder(new String(arr));

        System.out.println(sb.reverse());
    }
}
