import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int prev = 0;
        int time = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            time += (d - prev);
            prev = d;
            if (time % (r + g) < r) {
                time += r - (time % (r + g));
            }
        }

        time += (l - prev);
        System.out.println(time);
    }
}