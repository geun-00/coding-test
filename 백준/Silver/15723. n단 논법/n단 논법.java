import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        boolean[][] arr = new boolean[26][26];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            int a = temp[0].charAt(0) - 'a';
            int b = temp[2].charAt(0) - 'a';

            arr[a][b] = true;
        }

        for (int k = 0; k < 26; k++) {
            for (int s = 0; s < 26; s++) {
                for (int e = 0; e < 26; e++) {
                    if (arr[s][k] & arr[k][e]) {
                        arr[s][e] = true;
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int a = temp[0].charAt(0) - 'a';
            int b = temp[2].charAt(0) - 'a';

            System.out.println(arr[a][b] ? "T" : "F");
        }
    }
}