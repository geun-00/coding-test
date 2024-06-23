class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow; //전체 넓이

        for (int height = 3; height <= Math.sqrt(total); height++) { //높이(세로)
            if (total % height == 0) {
                int width = total / height; //너비(가로)

                if ((height - 2) * (width - 2) == yellow) { //노란색 격자의 수 조건 확인
                    return new int[]{width, height};
                }
            }
        }

        return null;
    }
}