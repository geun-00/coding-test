import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[][] area = new boolean[101][101];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int bottom = Integer.parseInt(st.nextToken());

            for (int j = left; j < left + 10; j++) {
                for (int k = bottom; k < bottom + 10; k++) {
                    if (!area[j][k]) {
                        area[j][k] = true;
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}