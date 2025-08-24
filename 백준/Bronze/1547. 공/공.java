import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }

        for (int i = 1; i <= 3; i++) {
            if (arr[i] == 1) {
                System.out.println(i);
                return;
            }
        }
    }
}