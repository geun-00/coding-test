import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        boolean[] rowCloud = new boolean[h];

        char[][] arr = new char[h][w];
        int[][] ans = new int[h][w];

        int iter = 101;

        for (int i = 0; i < h; i++) {
            char[] chars = br.readLine().toCharArray();
            Arrays.fill(ans[i], -1);

            for (int j = 0; j < w; j++) {

                arr[i][j] = chars[j];

                if (arr[i][j] == 'c') {
                    ans[i][j] = 0;
                    rowCloud[i] = true;
                }
            }
        }

        for (int i = 0; i < h; i++) {

            for (int j = 0; j < w; j++) {
                if (arr[i][j] == '.') continue;

                while (j < w && arr[i][j] == 'c') {
                    j++;
                }

                int num = 1;
                while (j < w && arr[i][j] == '.') {
                    ans[i][j++] = num++;
                }
                j--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }
}