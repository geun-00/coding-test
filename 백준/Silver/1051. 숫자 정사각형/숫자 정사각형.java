import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int max = Math.min(n, m);

        for (int i = max; i >= 2; i--) {

            for (int x = 0; x <= n - i; x++) {
                for (int y = 0; y <= m - i; y++) {
                    if (arr[x][y] == arr[x][y + i - 1] && arr[x][y] == arr[x + i - 1][y] && arr[x][y] == arr[x + i - 1][y + i - 1]) {
                        System.out.println(i * i);
                        return;
                    }
                }
            }
        }

        System.out.println(1);
    }
}