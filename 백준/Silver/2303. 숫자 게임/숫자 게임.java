import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int num = 0;
        int value = 0;

        for (int i = 1; i <= n; i++) {
            int[] cards = new int[5];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cards[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < 5 - 2; j++) {
                for (int k = j + 1; k < 5 - 1; k++) {
                    for (int l = k + 1; l < 5; l++) {
                        int sum = cards[j] + cards[k] + cards[l];
                        int result = sum % 10;

                        if (result >= value) {
                            value = result;
                            num = i;
                        }
                    }
                }
            }
        }

        System.out.println(num);
    }
}