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
        int b = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];

        int min = 257;
        int max = -1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int ans_t = Integer.MAX_VALUE;
        int ans_h = 0;

        for (int h = min; h <= max; h++) {

            int fill = 0;
            int remove = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    int diff = arr[i][j] - h;

                    if (diff > 0) {
                        remove += diff;
                    } else {
                        fill -= diff;
                    }
                }
            }

            if (b + remove >= fill) {

                int t = remove * 2 + fill;

                if (t <= ans_t) {
                    ans_t = t;
                    ans_h = h;
                }
            }
        }

        System.out.println(ans_t + " " + ans_h);
    }
}