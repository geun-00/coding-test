import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.stream(arr)
              .sorted((a, b) -> {
                  if (a.length() != b.length()) {
                      return Integer.compare(a.length(), b.length());
                  }
                  int aSum = getSum(a);
                  int bSum = getSum(b);

                  if (aSum != bSum) {
                      return Integer.compare(aSum, bSum);
                  }

                  return a.compareTo(b);
              })
              .forEach(System.out::println);
    }

    private static int getSum(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += (c - '0');
            }
        }

        return sum;
    }
}