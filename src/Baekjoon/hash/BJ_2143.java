package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/2143">백준 2143번 - 해시 : 두 배열의 합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-2143%EB%B2%88-%EB%91%90-%EB%B0%B0%EC%97%B4%EC%9D%98-%ED%95%A9">velog</a>
 *
 * @since 2024-08-25
 */
public class BJ_2143 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

/*
        ArrayList<Integer> sum_A = getSub(a);
        ArrayList<Integer> sum_B = getSub(b);

        sum_B.sort(null);

        long count = 0;
        for (int num : sum_A) {

            int target = t - num;

            int lower = getLowerBound(sum_B, target);
            int upper = getUpperBound(sum_B, target);

            count += (upper - lower);
        }

        System.out.println(count);
*/

        HashMap<Integer, Integer> sum_A = getSubArrSum(a);
        HashMap<Integer, Integer> sum_B = getSubArrSum(b);

        long count = 0; //long 사용 주의

        for (int num : sum_A.keySet()) {

            int temp = t - num;

            if (sum_B.containsKey(temp)) {
                count += (long) sum_A.get(num) * sum_B.get(temp);
            }
        }

        System.out.println(count);
    }

    private static HashMap<Integer, Integer> getSubArrSum(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        return map;
    }

    private static int getUpperBound(List<Integer> list, int target) {

        int s = 0;
        int e = list.size();

        while (s < e) {
            int mid = (s + e) / 2;
            if (list.get(mid) > target) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }

    private static int getLowerBound(List<Integer> list, int target) {

        int s = 0;
        int e = list.size();

        while (s < e) {
            int mid = (s + e) / 2;
            if (list.get(mid) >= target) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }

    private static ArrayList<Integer> getSub(int[] arr) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }

        return list;
    }
}