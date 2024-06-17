import java.util.Arrays;

class Solution{
    public int solution(int []A, int []B){
        int result = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            int temp = A[i] * B[B.length - 1 - i];
            result += temp;
        }

        return result;
    }
}