import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] balls = new int[n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());
            balls[i][0] = i;
            balls[i][1] = color;
            balls[i][2] = size;
        }

        Arrays.sort(balls, Comparator.comparing(ball -> ball[2]));

        int total = 0;
        int[] colorSum = new int[n];
        int[] ans = new int[n];

        for (int i = 0, j = 0; i < n; i++) {
            int num = balls[i][0];
            int color = balls[i][1];
            int size = balls[i][2];

            while (balls[j][2] < size) {
                total += balls[j][2];
                colorSum[balls[j][1]] += balls[j][2];
                j++;
            }

            ans[num] = total - colorSum[color];
        }

        StringBuilder sb = new StringBuilder();
        for (int result : ans) {
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
