import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreElements()) {
            int n = Integer.parseInt(st.nextToken());
            if (set.add(n)) {
                sb.append(n).append(" ");
            }
        }

        System.out.print(sb);
    }
}