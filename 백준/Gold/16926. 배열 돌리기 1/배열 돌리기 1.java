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
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] temp = new int[3];

        while (r-- > 0) {

            for (int i = 0; i < Math.min(n, m) / 2; i++) {

                int start_x = i;
                int start_y = i;

                int end_x = n - i - 1;
                int end_y = m - i - 1;

                temp[0] = arr[start_x][start_y];
                temp[1] = arr[end_x][start_y];
                temp[2] = arr[end_x][end_y];

                for (int j = start_y; j < end_y; j++) {
                    arr[start_x][j] = arr[start_x][j + 1];
                }

                for (int j = end_x; j > start_x; j--) {
                    arr[j][start_y] = arr[j - 1][start_y];
                }

                for (int j = end_y; j > start_y; j--) {
                    arr[end_x][j] = arr[end_x][j - 1];
                }

                for (int j = start_x; j < end_x; j++) {
                    arr[j][end_y] = arr[j + 1][end_y];
                }

                arr[start_x + 1][start_y] = temp[0];
                arr[end_x][start_y + 1] = temp[1];
                arr[end_x - 1][end_y] = temp[2];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
