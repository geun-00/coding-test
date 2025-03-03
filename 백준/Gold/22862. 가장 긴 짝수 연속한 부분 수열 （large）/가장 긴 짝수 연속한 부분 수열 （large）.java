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

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int ans = 0;
        int even = 0;

        while (end < n) {            
            if (arr[end] % 2 == 1) {
                if (k > 0) {
                    end++;
                    k--;
                }
                else {
                    if (arr[start] % 2 == 0) even--;
                    else k++;

                    start++;
                }
            } else {
                end++;
                even++;
            }
            
            ans = Math.max(ans, even);
        }

        System.out.println(ans);
    }
}