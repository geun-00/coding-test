import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int count = 0;

        loop:
        while (n-- > 0) {

            String s = br.readLine();

            if (s.length() == 1) {
                count++;
                continue;
            }

            for (int i = 0; i < s.length() - 1; i++) {

                int first = s.indexOf(s.charAt(i));
                int last = s.lastIndexOf(s.charAt(i));

                if (first == last || last - first == 1) {
                    i += last - first;
                    continue;
                }

                for (int j = first + 1; j < last; j++) {
                    if (s.charAt(first) != s.charAt(j)) {
                        continue loop;
                    }
                }
            }

            count++;

        }
        System.out.println(count);
    }
}