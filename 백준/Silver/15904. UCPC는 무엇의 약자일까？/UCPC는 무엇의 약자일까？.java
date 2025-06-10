import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = "UCPC".toCharArray();
        int index = 0;

        for (char c : br.readLine().toCharArray()) {
            if (c == arr[index]) {
                index++;
            }

            if (index == 4) {
                System.out.println("I love UCPC");
                return;
            }
        }

        System.out.println("I hate UCPC");
    }
}
