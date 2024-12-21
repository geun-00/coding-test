import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] height = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int x1, y1, x2, y2;
        double slope, maxSlope, minSlope;

        for (int i = 0; i < n; i++) {

            int count = 0;

            x1 = i;
            y1 = height[i];

            minSlope = Double.POSITIVE_INFINITY;
            for (int j = i - 1; j >= 0; j--) {
                x2 = j;
                y2 = height[j];

                slope = (double) (y2 - y1) / (x2 - x1);
                if (minSlope > slope) {
                    minSlope = slope;
                    count++;
                }
            }

            maxSlope = Double.NEGATIVE_INFINITY;
            for (int j = i + 1; j < n; j++) {
                x2 = j;
                y2 = height[j];

                slope = (double) (y2 - y1) / (x2 - x1);
                if (maxSlope < slope) {
                    maxSlope = slope;
                    count++;
                }
            }

            ans = Math.max(ans, count);
        }

        System.out.println(ans);
    }
}
