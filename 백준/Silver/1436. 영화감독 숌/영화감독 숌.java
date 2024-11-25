import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int num = 666;

        while (true) {

            if (String.valueOf(num).contains("666")) {
                n--;

                if (n == 0) {
                    System.out.println(num);
                    break;
                }
            }

            num++;
        }
    }
}