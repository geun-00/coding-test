import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int ans = 0;
        StringBuilder sb = new StringBuilder();
        String str = "ACGT";

        for (int i = 0; i < m; i++) {
            int[] temp = new int[4];

            for (int j = 0; j < n; j++) {
                char ch = arr[j].charAt(i);
                temp[str.indexOf(ch)]++;
            }

            int max = 0;
            int index = -1;
            char target = ' ';

            for (int j = 0; j < 4; j++) {
                int count = temp[j];

                if (max < count) {
                    max = count;
                    target = str.charAt(j);
                    index = j;
                } else if (max == count && j < index) {
                    target = str.charAt(j);
                }
            }

            ans += n - max;
            sb.append(target);
        }

        sb.append("\n").append(ans);
        System.out.print(sb);
    }
}