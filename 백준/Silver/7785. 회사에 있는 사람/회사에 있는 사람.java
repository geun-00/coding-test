import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String s = st.nextToken();

            if ("enter".equals(s)) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String s : set) {
            sb.append(s).append("\n");
        }

        System.out.print(sb);
    }
}
