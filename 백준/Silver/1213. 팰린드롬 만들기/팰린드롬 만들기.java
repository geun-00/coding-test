import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[26];

        String s = br.readLine();
        for (char c : s.toCharArray()) {
            arr[c - 'A']++;
        }

        int mid = 0, odd = 0;

        for (int i = 0; i < 26; i++) {
            if (arr[i] % 2 == 1) {
                mid = i;
                odd++;
            }
        }

        if ((s.length() % 2 == 0 && odd != 0) || (s.length() % 2 == 1 && odd > 1)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            sb.append(Character.toString(i + 'A').repeat(arr[i] / 2));
        }

        String front = sb.toString();
        String back = sb.reverse().toString();

        if (s.length() % 2 == 1) {
            String m = Character.toString(mid + 'A');
            System.out.println(front + m + back);
        } else {
            System.out.println(front + back);
        }
    }
}