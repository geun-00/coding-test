import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[2001];
        int offset = 1000;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken()) + offset;
            arr[num]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 2000; i++) {
            if (arr[i] > 0) {
                sb.append(i - offset).append(" ");
            }
        }

        System.out.print(sb);
    }
}