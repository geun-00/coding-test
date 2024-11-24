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

        int max = 0;

        for (int i = 0; i < (1 << (n * m)); i++) {

            int total = 0;

            for (int x = 0; x < n; x++) {
                int sum = 0;

                for (int y = 0; y < m; y++) {

                    int idx = x * m + y;

                    if ((i & (1 << idx)) == 0) {
                        sum = sum * 10 + (arr[x][y] - '0');
                    } else {
                        total += sum;
                        sum = 0;
                    }
                }

                total += sum;
            }

            for (int y = 0; y < m; y++) {
                int sum = 0;

                for (int x = 0; x < n; x++) {

                    int idx = x * m + y;

                    if ((i & (1 << idx)) != 0) {
                        sum = sum * 10 + (arr[x][y] - '0');
                    } else {
                        total += sum;
                        sum = 0;
                    }
                }

                total += sum;
            }

            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}