import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        int index = 0;

        while (index < n) {
            if (!st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
                continue;
            }
            arr[index++] = Long.parseLong(st.nextToken());
        }

        Arrays.stream(arr)
              .mapToObj(String::valueOf)
              .map(s -> new StringBuilder(s).reverse().toString())
              .mapToLong(Long::parseLong)
              .sorted()
              .forEach(System.out::println);

    }
}