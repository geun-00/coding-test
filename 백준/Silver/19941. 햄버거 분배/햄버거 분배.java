import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] != 'P') continue;

            for (int j = i - k; j <= i + k; j++) {

                if (0 <= j && j < n) {

                    if (arr[j] == 'H') {
                        ans++;
                        arr[j] = '-';
                        break;
                    }
                }


            }
        }

        System.out.println(ans);
    }
}