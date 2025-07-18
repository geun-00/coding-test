import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int ans = 1;
        while (true) {
            if (a % 2 == 1 && Math.abs(a - b) == 1) {
                break;
            }
            ans++;
            a = (a + 1) / 2;
            b = (b + 1) / 2;
        }

        System.out.println(ans);
    }
}