import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr = {1, 5, 10, 50};
    static boolean[] checked = new boolean[1001];
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int a = 0; a <= n; a++) {
            for (int b = 0; b <= n - a; b++) {
                for (int c = 0; c <= n - a - b; c++) {
                    int d = n - a - b - c;
                    int sum = a * arr[0] + b * arr[1] + c * arr[2] + d * arr[3];

                    if (!checked[sum]) {
                        checked[sum] = true;
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}