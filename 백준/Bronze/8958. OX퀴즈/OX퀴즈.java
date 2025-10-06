import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (tc-- > 0) {
            String s = br.readLine();

            int sum = 0;

            for (int i = 0; i < s.length(); i++) {
                int count = 0;
                while (i < s.length() && s.charAt(i) == 'O') {
                    i++;
                    count++;
                    sum += count;
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}