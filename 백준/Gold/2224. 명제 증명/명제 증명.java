import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int num = 52;
        boolean[][] arr = new boolean[num][num];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");

            char p = temp[0].charAt(0);
            char q = temp[2].charAt(0);

            int a, b;

            if ('a' <= p && p <= 'z') {
                a = p - 'a' + 26;
            } else {
                a = p - 'A';
            }

            if ('a' <= q && q <= 'z') {
                b = q - 'a' + 26;
            } else {
                b = q - 'A';
            }

            arr[a][b] = true;
        }

        for (int k = 0; k < num; k++) {
            for (int s = 0; s < num; s++) {
                for (int e = 0; e < num; e++) {
                    if (arr[s][k] & arr[k][e]) {
                        arr[s][e] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i != j && arr[i][j]) {
                    count++;

                    char a = (char) ((i < 26) ? i + 'A' : i + 'a' - 26);
                    char b = (char) ((j < 26) ? j + 'A' : j + 'a' - 26);

                    sb.append(a).append(" => ").append(b).append("\n");
                }
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }
}