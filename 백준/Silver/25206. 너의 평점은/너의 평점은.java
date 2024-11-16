import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static boolean[] empty;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] grade = {"A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F", "P"};
        double[] score = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0, 0.0};

        HashMap<String, Double> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(grade[i], score[i]);
        }

        double sum = 0;
        double majorSum = 0;

        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            st.nextToken();

            double h = Double.parseDouble(st.nextToken());
            sum += h;

            String g = st.nextToken();
            majorSum += h * map.get(g);
            
            if ("P".equals(g)) {
                sum -= h;
            }
        }

        System.out.printf("%.4f", majorSum / sum);
    }
}