import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String doc = br.readLine();
        String word = br.readLine();
        
        if (word.length() > doc.length()) {
            System.out.println(0);
            return;
        }

        int ans = 0;
        for (int i = 0; i < doc.length(); ) {
            String target = doc.substring(i, Math.min(i + word.length(), doc.length()));
            if (word.equals(target)) {
                ans++;
                i += word.length();
            } else {
                i++;
            }
        }
        System.out.println(ans);
    }
}
