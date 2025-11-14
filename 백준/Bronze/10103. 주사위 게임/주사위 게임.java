import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int c = 100, s = 100;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int chang = Integer.parseInt(st.nextToken());
            int sang = Integer.parseInt(st.nextToken());

            if (chang > sang) {
                s -= chang;
            } else if (sang > chang) {
                c -= sang;
            }
        }

        System.out.println(c);
        System.out.println(s);
    }
}