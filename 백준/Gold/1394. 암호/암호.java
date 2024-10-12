import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String pw = br.readLine();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i + 1);
        }

        long result = 0;
        int mod = 900528;
        int len = s.length();
        long temp = 1;

        for (int i = 0; i < pw.length(); i++) {

            char c = pw.charAt(i);
            result = (result * len + map.get(c)) % mod;
        }

        System.out.println(result);

    }
}
