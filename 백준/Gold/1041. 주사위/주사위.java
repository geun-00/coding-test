import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        if (n == 1) {
            System.out.println(Arrays.stream(dice).sum() - Arrays.stream(dice).max().getAsInt());
            return;
        }

        int[] min = new int[3];
        min[0] = Math.min(dice[0], dice[5]);
        min[1] = Math.min(dice[1], dice[4]);
        min[2] = Math.min(dice[2], dice[3]);

        Arrays.sort(min);

        System.out.println(
                ((min[0] + min[1] + min[2]) * 4L) +
                ((min[0] + min[1]) * (8L * n - 12)) +
                (min[0] * (5L * n * n - 16L * n + 12))
        );

    }
}
