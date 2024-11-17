import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[][] arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int w = 0, h = 0;

        for (int i = 0; i < n; i++) {

            int count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '.') {
                    count++;
                } else {
                    if (count >= 2) {
                        w++;
                    }
                    count = 0;
                }
            }
            if (count >= 2) {
                w++;
            }

            count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[j][i] == '.') {
                    count++;
                } else {
                    if (count >= 2) {
                        h++;
                    }
                    count = 0;
                }
            }
            if (count >= 2) {
                h++;
            }
        }

        System.out.println(w + " " + h);
    }
}