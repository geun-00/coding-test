import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[] check = new boolean[n + 1];
            int[][] arr = new int[m][2];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                arr[i][0] = a;
                arr[i][1] = b;
            }

            Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));

            int count = 0;

            for (int[] s : arr) {
                int a = s[0];
                int b = s[1];

                for (int i = a; i <= b; i++) {
                    if (check[i]) continue;

                    check[i] = true;
                    count++;
                    break;
                }
            }

            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
