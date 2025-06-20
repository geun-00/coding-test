import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> result = IntStream.rangeClosed(1, n)
                                        .filter(i -> n % i == 0)
                                        .boxed()
                                        .collect(Collectors.toList());
        if (result.size() < k) {
            System.out.println(0);
            return;
        }

        System.out.println(result.get(k - 1));
    }
}
