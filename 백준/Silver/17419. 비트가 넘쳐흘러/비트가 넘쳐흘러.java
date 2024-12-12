import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine(), 2);

        int count = 0;
        while (num != 0) {

            num = num - (num & ((~(num) + 1)));
            count++;
        }

        System.out.println(count);
    }
}
