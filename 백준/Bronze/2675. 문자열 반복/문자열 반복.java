import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            String s = st.nextToken();

            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                sb.append((c + "").repeat(r));
            }

            ans.append(sb).append("\n");
        }

        System.out.print(ans);
    }
}
