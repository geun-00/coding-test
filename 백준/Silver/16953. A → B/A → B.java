import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();

        HashSet<String> visit = new HashSet<>();
        Queue<String> qu = new ArrayDeque<>();
        qu.offer(a);

        int count = 0;

        while (!qu.isEmpty()) {

            count++;

            int size = qu.size();

            for (int i = 0; i < size; i++) {
                String num = qu.poll();

                if (num.length() > b.length()) {
                    continue;
                }

                if (num.equals(b)) {
                    System.out.println(count);
                    return;
                }

                String s = String.valueOf(Long.parseLong(num) * 2);
                if (visit.add(s)) {
                    qu.offer(s);
                }

                s = num + "1";
                if (visit.add(s)) {
                    qu.offer(s);
                }
            }
        }

        System.out.println(-1);
    }
}