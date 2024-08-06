import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        int maxDay = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[i][0] = d;
            arr[i][1] = w;

            maxDay = Math.max(maxDay, arr[i][0]);
        }

        Arrays.sort(arr, (o1, o2) -> o2[1] - o1[1]);

        int sum = 0;
        int[] tasks = new int[maxDay + 1];

        for (int[] task : arr) {

            int d = task[0];
            int w = task[1];

            for (int i = d; i > 0; i--) {
                if (tasks[i] == 0) {
                    tasks[i] = w;
                    sum += w;
                    break;
                }
            }
        }

        System.out.println(sum);
    }
}
