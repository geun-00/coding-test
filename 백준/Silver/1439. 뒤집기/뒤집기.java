import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int zero = 0, one = 0;

        for (int i = 0; i < s.length();) {

            if (s.charAt(i) == '0') {
                while (i < s.length() && s.charAt(i) == '0') {

                    i++;
                }
                zero++;
            }
            else if (s.charAt(i) == '1') {
                while (i < s.length() && s.charAt(i) == '1') {
                    i++;
                }
                one++;
            }
        }

        System.out.println(Math.min(zero, one));
    }
}