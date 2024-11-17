import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                sb.append(".");
                continue;
            }

            int len = 0;
            while (i < s.length() && s.charAt(i) == 'X') {
                i++;
                len++;
            }
            i--;

            int remain = len % 4;

            if (remain % 2 != 0) {
                System.out.println(-1);
                return;
            }

            sb.append("AAAA".repeat(len / 4));
            sb.append("BB".repeat(remain / 2));
        }

        System.out.print(sb);
    }
}