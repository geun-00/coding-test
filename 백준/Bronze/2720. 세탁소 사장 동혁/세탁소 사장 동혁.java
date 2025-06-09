import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] money = {25, 10, 5, 1};

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int c = Integer.parseInt(br.readLine());

            int[] ans = new int[4];
            for (int i = 0; i < 4; i++) {
                ans[i] = c / money[i];
                c %= money[i];
            }

            for (int i : ans) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}