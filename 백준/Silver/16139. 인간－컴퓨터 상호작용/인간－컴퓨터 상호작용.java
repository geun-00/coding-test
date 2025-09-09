import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        int[][] arr = new int[26][n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            arr[c - 'a'][i + 1] = 1;
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] += arr[i][j - 1];
            }
        }

        int q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = st.nextToken().charAt(0) - 'a';
            int l = Integer.parseInt(st.nextToken()) + 1;
            int r = Integer.parseInt(st.nextToken()) + 1;

            sb.append(arr[a][r] - arr[a][l - 1]).append("\n");
        }

        System.out.print(sb);
    }
}