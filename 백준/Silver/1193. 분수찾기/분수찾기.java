import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        int n = 1;

        while (x > (n * (n + 1)) / 2) {
            n++;
        }

        int start = n * (n - 1) / 2 + 1;
        int pos = x - start + 1;

        int p; //분모
        int q; //분자

        if (n % 2 == 0) {
            q = pos;
            p = n + 1 - q;
        } else {
            p = pos;
            q = n + 1 - p;
        }

        System.out.println(q + "/" + p);
    }
}