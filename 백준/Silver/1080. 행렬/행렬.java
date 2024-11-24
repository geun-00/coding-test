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

        char[][] a = new char[n][m];
        char[][] b = new char[n][m];

        for (int i = 0; i < n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            b[i] = br.readLine().toCharArray();
        }

        if (n < 3 || m < 3) {
            System.out.println(isSame(a, b) ? 0 : -1);
            return;
        }

        int count = 0;

        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {

                if (a[i][j] != b[i][j]) {

                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            a[i + k][j + l] = (a[i + k][j + l] == '0') ? '1' : '0';
                        }
                    }

                    count++;
                }
            }
        }

        System.out.println(isSame(a, b) ? count : -1);
    }

    private static boolean isSame(char[][] a, char[][] b) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}