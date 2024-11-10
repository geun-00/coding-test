import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < (1 << n); i++) {
            int s = 1;
            int b = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    s *= arr[j][0];
                    b += arr[j][1];
                }
            }

            min = Math.min(min, Math.abs(s - b));
        }

        System.out.println(min);
    }   
}