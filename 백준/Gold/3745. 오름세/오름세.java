import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while ((input = br.readLine()) != null) {

            int n = Integer.parseInt(input.trim());

            StringTokenizer st = new StringTokenizer(br.readLine());

            ArrayList<Integer> lis = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                int num = Integer.parseInt(st.nextToken());

                int left = 0, right = lis.size() - 1;

                while (left <= right) {

                    int mid = (left + right) / 2;

                    if (num > lis.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (left < lis.size()) {
                    lis.set(left, num);
                } else {
                    lis.add(num);
                }
            }

            sb.append(lis.size()).append("\n");
        }

        System.out.print(sb);
    }
}
