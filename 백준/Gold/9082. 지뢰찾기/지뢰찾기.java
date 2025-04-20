import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            char[] arr = br.readLine().toCharArray();
            char[] block = br.readLine().toCharArray();

            int[] number = new int[n];
            for (int j = 0; j < n; j++) {
                number[j] = arr[j] - '0';
            }

            for (int j = 0; j < n; j++) {
                if (block[j] == '*') {
                    for (int k = j - 1; k <= j + 1; k++) {
                        if (k >= 0 && k < n) {
                            number[k]--;
                        }
                    }
                }
            }

            int ans = 0;

            for (int j = 0; j < n; j++) {
                if (block[j] == '*') {
                    ans++;
                }
                else {
                    boolean flag = true;
                    for (int k = j - 1; k <= j + 1; k++) {
                        if (k >= 0 && k < n && number[k] <= 0) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        for (int k = j - 1; k <= j + 1; k++) {
                            if (k >= 0 && k < n) {
                                number[k]--;
                            }
                        }
                        block[j] = '*';
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }
    }
}