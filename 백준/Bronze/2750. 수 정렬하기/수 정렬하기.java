import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] counting = new int[2001];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine()) + 1000;
            counting[num]++;
        }

        for (int i = 0; i <= 2000; i++) {
            while (counting[i] > 0) {
                System.out.println(i - 1000);
                counting[i]--;
            }
        }
    }
}