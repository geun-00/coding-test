import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            String[] arr = new String[n];

            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            String res = "YES";
            for (int i = 0; i < n - 1; i++) {
                if (arr[i + 1].startsWith(arr[i])) {
                    res = "NO";
                    break;
                }
            }

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }
}
