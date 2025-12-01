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
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stars = new int[k][2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int tx = stars[i][0];
                int ty = stars[j][1];

                int count = 0;
                for (int s = 0; s < k; s++) {
                    int curX = stars[s][0];
                    int curY = stars[s][1];

                    if (tx <= curX && curX <= tx + l && ty <= curY && curY <= ty + l) {
                        count++;
                    }
                }

                max = Math.max(max, count);
            }
        }

        System.out.println(k - max);
    }
}