import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int x = 1;

        while (k-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            int[] score = new int[n];

            int max = -1;
            int min = 101;

            for (int i = 0; i < n; i++) {
                score[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, score[i]);
                min = Math.min(min, score[i]);
            }

            int[] gap = new int[n - 1];

            Arrays.sort(score);

            int max_gap = -1;

            for (int i = 0; i < n - 1; i++) {
                gap[i] = score[i + 1] - score[i];

                max_gap = Math.max(max_gap, gap[i]);
            }

            sb.append("Class ").append(x++).append("\n");
            sb
                    .append("Max ")
                    .append(max)
                    .append(", ")
                    .append("Min ")
                    .append(min)
                    .append(", ")
                    .append("Largest gap ")
                    .append(max_gap)
                    .append("\n");
        }

        System.out.print(sb);
    }
}
