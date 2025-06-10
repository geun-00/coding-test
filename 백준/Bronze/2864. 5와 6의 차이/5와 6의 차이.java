import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] num = br.readLine().split(" ");

        int A, B;
        int min, max;

        A = Integer.parseInt(num[0].replace('6', '5'));
        B = Integer.parseInt(num[1].replace('6', '5'));
        min = A + B;

        A = Integer.parseInt(num[0].replace('5', '6'));
        B = Integer.parseInt(num[1].replace('5', '6'));
        max = A + B;

        System.out.println(min + " " + max);
    }
}