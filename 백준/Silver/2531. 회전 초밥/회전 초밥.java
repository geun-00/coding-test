import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] sushi = new int[d + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (sushi[arr[i]] == 0) {
                count++;
            }
            sushi[arr[i]]++;
        }

        int max = count + (sushi[c] == 0 ? 1 : 0);

        for (int i = 1; i < n; i++) {

            int addIdx = (i + k - 1) % n;
            int removeIdx = i - 1;

            sushi[arr[removeIdx]]--;
            if (sushi[arr[removeIdx]] == 0) {
                count--;
            }

            sushi[arr[addIdx]]++;
            if (sushi[arr[addIdx]] == 1) {
                count++;
            }

            max = Math.max(max, count + (sushi[c] == 0 ? 1 : 0));
        }

        System.out.println(max);
    }
}