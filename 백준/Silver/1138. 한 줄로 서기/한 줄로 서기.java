import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int count = arr[i];

            for (int j = 0; j < n; j++) {

                if (res[j] == 0) {
                    if (count == 0) {
                        res[j] = i + 1;
                        break;
                    }
                    count--;
                }

            }
        }

        for (int r : res) {
            System.out.print(r + " ");
        }
    }
}